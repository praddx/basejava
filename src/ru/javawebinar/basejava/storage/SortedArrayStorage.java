
package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


/**
 * Sorted array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public List<Resume> getAllSorted() {
        Arrays.sort(storage, 0, size, Comparator.comparing(Resume::getFullName));
        return Arrays.asList(storage).subList(0, size);
    }

    protected Integer getSearchKey(String uuid) {
        Resume serchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, serchKey, Comparator.comparing(Resume::getUuid));
    }

    @Override
    protected void doSave(Object index, Resume r) {
        int newEntryIndex = -(Integer) index - 1;
        int amountElementsToMove = size - (newEntryIndex);
        System.arraycopy(storage, newEntryIndex, storage, newEntryIndex + 1, amountElementsToMove);
        storage[newEntryIndex] = r;
    }

    @Override
    protected void doDelete(Object index) {
        int amountElementsToMove = size - (Integer) index;
        System.arraycopy(storage, (Integer) index + 1, storage, (Integer) index, amountElementsToMove);
    }
}






















