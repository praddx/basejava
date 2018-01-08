package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
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
    public Resume[] getAll() {
        return storage.toArray(new Resume[storage.size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Integer checkIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isStorageContainsResume(Object index) {
        if (index != null && (Integer) index >= 0) {
            return true;
        }
        return false;
    }

    @Override
    protected void insertResume(Object index, Resume r) {
        storage.add(r);
    }

    @Override
    protected void deleteResume(Object index) {
        storage.remove(((Integer) index).intValue());
    }

    @Override
    protected void updateResume(Object index, Resume r) {
        storage.set((Integer) index, r);
    }

    @Override
    protected Resume getResume(Object index) {
        return storage.get((Integer) index);
    }
}