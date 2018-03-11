package ru.javawebinar.basejava.storage;

/**
 * Created by Pradd on 30.12.2017.
 */
public class ObjectStreamPathStorageTest extends AbstractStorageTest {

    public ObjectStreamPathStorageTest() {
        super(new PathStorage(PAHT_STORAGE_DIR, new ObjectStreamPathStorage()));
    }
}
