package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public List<Resume> getAllSorted() {
        Arrays.sort(storage, 0, size, Comparator.comparing(Resume::getFullName));
        return Arrays.asList(storage).subList(0, size);
    }

    @Override
    protected void doSave(Object index, Resume r) {
        storage[size] = r;
    }

    @Override
    protected void doDelete(Object index) {
        storage[(Integer) index] = storage[size - 1];
    }
}





















