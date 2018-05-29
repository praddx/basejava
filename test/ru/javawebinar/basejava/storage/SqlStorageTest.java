package ru.javawebinar.basejava.storage;

import java.io.File;

import static ru.javawebinar.basejava.storage.AbstractStorageTest.config;

/**
 * Created by Yana on 5/29/2018.
 */
public class SqlStorageTest extends AbstractStorageTest {

    public SqlStorageTest() {
        super(new SqlStorage(config.getDbUrl(), config. getDbUser(), config.getDbPassword()));
    }
}
