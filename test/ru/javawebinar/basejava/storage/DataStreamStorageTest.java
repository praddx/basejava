package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serialization.DataStreamSerializer;

public class DataStreamStorageTest extends AbstractStorageTest {
    public DataStreamStorageTest() {
        super(new PathStorage(DATA_STORAGE_DIR, new DataStreamSerializer()));
    }
}
