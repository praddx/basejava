package ru.javawebinar.basejava.storage;

/**
 * Created by Pradd on 30.12.2017.
 */
public class ObjectStreamStorageTest extends AbstractStorageTest {

    public ObjectStreamStorageTest() {
        super(new ObjectStreamStorage(STORAGE_DIR));
    }
}
