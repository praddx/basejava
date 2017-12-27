package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.*;

/**
 * Created by Pradd on 27.12.2017.
 */
public class ArrayStorageTest {
    private ArrayStorage storage = new ArrayStorage();
    private Resume r1 = new Resume("uuid1");
    private Resume r2 = new Resume("uuid2");
    private Resume r7 = new Resume("uuid7");
    private Resume r5 = new Resume("uuid5");
    private Resume r3 = new Resume("uuid3");
    private Resume r6 = new Resume("uuid6");

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(r1);
        storage.save(r2);
        storage.save(r7);
        storage.save(r5);
        storage.save(r3);
    }
    @Test
    public void getIndex() throws Exception {
        Assert.assertEquals(2, storage.getIndex("uuid7"));
    }

    @Test
    public void insertResume() throws Exception {
        storage.insertResume(5, r6);
        storage.size++;
        Assert.assertEquals(r6, storage.getAll()[5]);
    }

    @Test
    public void deleteResume() throws Exception {
        storage.deleteResume(1);
        storage.size--;
        Assert.assertEquals(r3, storage.getAll()[1]);
    }

}