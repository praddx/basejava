package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.util.sql.ConnectionFactory;
import ru.javawebinar.basejava.util.sql.ExceptionUtil;
import ru.javawebinar.basejava.util.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yana on 5/28/2018.
 */
public class SqlStorage implements Storage {

    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public void save(Resume r) {
        sqlHelper.<Void>execute("INSERT INTO resume (uuid, full_name) VALUES (?, ?)",
                ps -> {
                            ps.setString(1, r.getUuid());
                            ps.setString(2, r.getFullName());
                            ps.execute();
                            return null;
                       });
    }

    @Override
    public void update(Resume r) {
        sqlHelper.execute("UPDATE resume SET full_name = ? WHERE uuid = ?",
                ps -> {
                            ps.setString(1, r.getFullName());
                            ps.setString(2, r.getUuid());
                            if (ps.executeUpdate() == 0) {
                                throw new NotExistStorageException(r.getUuid());
                            }
                            return null;
                });

    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.<Resume>execute("SELECT * FROM resume r WHERE r.uuid = ?",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException("");
                    }
                    return new Resume(rs.getString("uuid"), rs.getString("full_name"));
                });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume r WHERE r.uuid = ?",
                ps -> {
                            ps.setString(1, uuid);
                    if (ps.executeUpdate() == 0) {
                        throw new NotExistStorageException(uuid);
                    }
                    return null;
                });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute("SELECT * FROM resume ORDER BY full_name,uuid",
                ps -> {
                        ResultSet rs = ps.executeQuery();
                    List<Resume> resumes = new ArrayList<>();
                    while(rs.next()) {
                        resumes.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
                    }
                    return resumes;
                });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT COUNT(uuid) FROM resume",
                ps -> {
                            ResultSet rs = ps.executeQuery();
                    return rs.next() ? rs.getInt(1) : 0;
                });
    }
}
