package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Pradd on 29.12.2017.
 */
public class ListStorage extends AbstractStorage {

    protected List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        storage.sort(Comparator.comparing(Resume::getFullName));
        return storage;
    }

//    @Override
//    public Resume[] getAll() {
//        return storage.toArray(new Resume[storage.size()]);
//    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isStorageContainsResume(Object index) {
        return index != null;
    }

    @Override
    protected void doSave(Object index, Resume r) {
        storage.add(r);
    }

    @Override
    protected void doDelete(Object index) {
        storage.remove(((Integer) index).intValue());
    }

    @Override
    protected void doUpdate(Object index, Resume r) {
        storage.set((Integer) index, r);
    }

    @Override
    protected Resume getResume(Object index) {
        return storage.get((Integer) index);
    }
}
