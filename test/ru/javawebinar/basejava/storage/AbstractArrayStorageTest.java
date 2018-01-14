package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Test;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import static ru.javawebinar.basejava.storage.AbstractArrayStorage.STORAGE_LIMIT;

/**
 * Created by Pradd on 25.12.2017.
 */
public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test (expected = StorageException.class)
    public void saveOverflow() throws Exception {
        try {
                for (int i = 3; i < STORAGE_LIMIT; i++) {
                super.storage.save(new Resume("uuid" + i + 1));
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume("boom"));
    }
}