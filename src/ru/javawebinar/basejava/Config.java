package ru.javawebinar.basejava;

import ru.javawebinar.basejava.exception.StorageException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Yana on 5/24/2018.
 */
public class Config {

    private static Config instance;

    private final String STORAGE_DIR;
    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;

    private Config() {
        try (InputStream is = new FileInputStream(new File(".\\config\\config.properties"))) {
            Properties projectProperties = new Properties();
            projectProperties.load(is);
            this.STORAGE_DIR = projectProperties.getProperty("storage.dir");
            this.dbUrl = projectProperties.getProperty("db.url");
            this.dbUser = projectProperties.getProperty("db.user");
            this.dbPassword = projectProperties.getProperty("db.password");
        } catch (IOException e) {
            throw new StorageException("Failed to initialize properties!", "");
        }

    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getSTORAGE_DIR() {
        return STORAGE_DIR;
    }
}
