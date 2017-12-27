package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.*;
import static ru.javawebinar.basejava.storage.AbstractArrayStorage.STORAGE_LIMIT;

/**
 * Created by Pradd on 25.12.2017.
 */
public class AbstractArrayStorageTest {
    private Storage storage = new ArrayStorage();
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }


    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void save() throws Exception {
        Resume resume4 = new Resume("uuid4");
        storage.save(resume4);
        Assert.assertEquals(resume4, storage.get("uuid4"));
    }

    @Test (expected = StorageException.class)
    public void saveOverflow() throws Exception {
        for (int i = 3; i < STORAGE_LIMIT; i++) {
            storage.save(new Resume("uuid" + i + 1));
        }
        storage.save(new Resume("boom"));
    }

    @Test (expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        Resume resume4 = new Resume("uuid4");
        storage.save(resume4);
        storage.save(resume4);
    }

    @Test
    public void update() throws Exception {
        Resume oldUuid3 = storage.get("uuid3");
        Resume newUuid3 = new Resume("uuid3");
        storage.update(newUuid3);
        Assert.assertNotSame(oldUuid3, storage.get("uuid3"));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(new Resume("uuid4"));

    }

    @Test (expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete("uuid3");
        storage.get("uuid3");
    }

    @Test (expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("uuid4");
    }

    @Test
    public void get() throws Exception {
        Resume r1 = new Resume("uuid1");
        storage.update(r1);
        Assert.assertEquals(r1, storage.get("uuid1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void getAll() throws Exception {
        Resume r1 = new Resume("uuid1");
        Resume r2 = new Resume("uuid2");
        Resume r3 = new Resume("uuid3");
        Resume[] allResumes = {r1, r2, r3};
        storage.update(r1);
        storage.update(r2);
        storage.update(r3);
        Resume[] resumesFromStorage = {storage.get("uuid1"), storage.get("uuid2"), storage.get("uuid3")};
        Assert.assertArrayEquals(allResumes, resumesFromStorage);
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

}