package ru.javawebinar.basejava.util.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Yana on 5/28/2018.
 */
public interface SqlExecutor<T> {

    T execute (PreparedStatement ps) throws SQLException;
}
