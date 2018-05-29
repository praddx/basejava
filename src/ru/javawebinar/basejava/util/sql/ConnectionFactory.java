package ru.javawebinar.basejava.util.sql;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Yana on 5/28/2018.
 */
public interface ConnectionFactory {
    public Connection getConnection() throws SQLException;
}
