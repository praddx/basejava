package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serialization.JsonStreamSerializer;

public class JsonPathStorageTest extends AbstractStorageTest{
    public JsonPathStorageTest() {
        super(new PathStorage(JASON_STORAGE_DIR, new JsonStreamSerializer()));
    }
}
