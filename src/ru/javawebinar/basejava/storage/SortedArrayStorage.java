
package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;


/**
 * Sorted array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {



    //method finds resume with the given uuid in the database and returns its index
    //if there is no resume with the given uuid in the database returns -1
    protected int getIndex(String uuid) {
        Resume serchKey = new Resume(uuid);
        serchKey.setUuid(uuid);
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
    protected void deleteResume(int index) {
        int amountElementsToMove = size - index;
        System.arraycopy(storage, index + 1, storage, index, amountElementsToMove);
    }
}






















