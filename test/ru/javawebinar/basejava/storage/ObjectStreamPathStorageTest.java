package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serialization.ObjectStreamSerializer;

/**
 * Created by Pradd on 30.12.2017.
 */
public class ObjectStreamPathStorageTest extends AbstractStorageTest {

    public ObjectStreamPathStorageTest() {
        super(new PathStorage(PATH_STORAGE_DIR, new ObjectStreamSerializer()));
    }
}
