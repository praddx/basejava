
package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


/**
 * Sorted array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    protected Integer getSearchKey(String uuid) {
        Resume serchKey = new Resume(uuid, "");
        return Arrays.binarySearch(storage, 0, size, serchKey, Comparator.comparing(Resume::getUuid));
    }

    @Override
    protected void doSave(Integer index, Resume r) {
        int newEntryIndex = -index - 1;
        int amountElementsToMove = size - (newEntryIndex);
        System.arraycopy(storage, newEntryIndex, storage, newEntryIndex + 1, amountElementsToMove);
        storage[newEntryIndex] = r;
    }

    @Override
    protected void doDelete(Integer index) {
        int amountElementsToMove = size - index;
        System.arraycopy(storage, index + 1, storage, index, amountElementsToMove);
    }
}






















