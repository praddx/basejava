package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Pradd on 01.01.2018.
 */
public abstract class AbstractStorageTest {

    protected Storage storage;

    //protected static final String PATH_STORAGE_DIR = "/home/pradd/Java/basejava/path_storage";
    protected static Config config = Config.getInstance();
    protected static final File STORAGE_DIR = config.getStorageDir();
    //protected static final String XML_STORAGE_DIR = "/home/pradd/Java/basejava/xml_storage";
    //protected static final String JASON_STORAGE_DIR = "/home/pradd/Java/basejava/json_storage";
    //protected static final String DATA_STORAGE_DIR = "/home/pradd/Java/basejava/data_storage";

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String fullName_1 = "Bob Marley";
    private static final String fullName_2 = "Marylin Manson";
    private static final String fullName_3 = "Elvis Presley";
    private static final String fullName_4 = "John Lennon";

    private static final Resume r1 = TestResume.getResume(UUID_1, fullName_1);
    private static final Resume r2 = TestResume.getResume(UUID_2, fullName_2);
    private static final Resume r3 = TestResume.getResume(UUID_3, fullName_3);
    private static final Resume r4 = TestResume.getResume(UUID_4, fullName_4);

    public AbstractStorageTest(Storage storage) {
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
        assertEquals(0, storage.size());
    }

    @Test
    public void save() throws Exception {
        storage.save(r4);
        assertEquals(r4, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(r3);
    }

    @Test
    public void update() throws Exception {
        Resume newUuid3 = new Resume(UUID_3, fullName_3);
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
        assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(UUID_4);
    }

    @Test
    public void get() throws Exception {
        assertGet(r1);
        assertGet(r2);
        assertGet(r3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("xxx");
    }

    @Test
    public void getAll() throws Exception {
        Resume[] allResumes = {r1, r2, r3};
        Resume[] resumesFromStorage = {storage.get(UUID_1), storage.get(UUID_2), storage.get(UUID_3)};
        Assert.assertArrayEquals(allResumes, resumesFromStorage);
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> list = storage.getAllSorted();
        assertEquals(list, Arrays.asList(r1, r3, r2));
    }

    @Test
    public void size() throws Exception {
        assertEquals(3, storage.size());
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }

}