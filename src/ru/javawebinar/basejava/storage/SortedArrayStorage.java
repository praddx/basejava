
package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;


/**
 * Sorted array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    protected Integer checkIndex(String uuid) {
        Resume serchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, serchKey);
    }

    @Override
    protected void insertResume(Object index, Resume r) {
        int newEntryIndex = -(Integer) index - 1;
        int amountElementsToMove = size - (newEntryIndex);
        System.arraycopy(storage, newEntryIndex, storage, newEntryIndex + 1, amountElementsToMove);
        storage[newEntryIndex] = r;
    }

    @Override
    protected void deleteResume(Object index) {
        int amountElementsToMove = size - (Integer) index;
        System.arraycopy(storage, (Integer) index + 1, storage, (Integer) index, amountElementsToMove);
    }
}






















