package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serialization.ObjectStreamSerializer;

/**
 * Created by Pradd on 30.12.2017.
 */
public class ObjectStreamStorageTest extends AbstractStorageTest {

    public ObjectStreamStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamSerializer()));
    }
}
