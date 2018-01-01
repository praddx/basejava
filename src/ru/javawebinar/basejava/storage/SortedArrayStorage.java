
package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;


/**
 * Sorted array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    protected int getIndex(String uuid) {
        Resume serchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, serchKey);
    }

    @Override
    protected void insertResume(int index, Resume r) {
        int newEntryIndex = -index - 1;
        int amountElementsToMove = size - (newEntryIndex);
        System.arraycopy(storage, newEntryIndex, storage, newEntryIndex + 1, amountElementsToMove);
        storage[newEntryIndex] = r;
    }

    @Override
    protected void deleteResume(int index, String uuid) {
        int amountElementsToMove = size - index;
        System.arraycopy(storage, index + 1, storage, index, amountElementsToMove);
    }
}






















