package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import static ru.javawebinar.basejava.storage.AbstractArrayStorage.STORAGE_LIMIT;

/**
 * Created by Pradd on 25.12.2017.
 */
public abstract class AbstractArrayStorageTest {

    private Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final Resume r1 = new Resume(UUID_1);
    private static final Resume r2 = new Resume(UUID_2);
    private static final Resume r3 = new Resume(UUID_3);
    private static final Resume r4 = new Resume(UUID_4);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void save() throws Exception {
        storage.save(r4);
        Assert.assertEquals(r4, storage.get(UUID_4));
    }

    @Test (expected = StorageException.class)
    public void saveOverflow() throws Exception {
        for (int i = 3; i < STORAGE_LIMIT; i++) {
            storage.save(new Resume("uuid" + i + 1));
        }
        Assert.assertEquals(STORAGE_LIMIT, storage.size());
        storage.save(new Resume("boom"));
    }

    @Test (expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(r3);
    }

    @Test
    public void update() throws Exception {
        Resume newUuid3 = new Resume(UUID_3);
        storage.update(newUuid3);
        Assert.assertNotSame(r3, storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(r4);

    }

    @Test
    public void delete() throws Exception {
        storage.delete(UUID_3);
        Assert.assertEquals(2, storage.size());
    }

    @Test (expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(UUID_4);
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(r1, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void getAll() throws Exception {
        Resume[] allResumes = {r1, r2, r3};
        Resume[] resumesFromStorage = {storage.get(UUID_1), storage.get(UUID_2), storage.get(UUID_3)};
        Assert.assertArrayEquals(allResumes, resumesFromStorage);
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

}