package ru.javawebinar.basejava.storage;

import org.junit.Ignore;
import org.junit.Test;
import ru.javawebinar.basejava.exception.StorageException;

/**
 * Created by Pradd on 30.12.2017.
 */
public class MapStorageTest extends AbstractArrayStorageTest {

    public MapStorageTest() {
        super(new MapStorage());
    }

    @Override
    @Ignore
    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
    }
}
