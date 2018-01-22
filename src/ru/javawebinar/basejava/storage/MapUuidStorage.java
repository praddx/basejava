package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

/**
 * Created by Pradd on 29.12.2017.
 */
public class MapUuidStorage extends AbstractStorage {

    private Map<String, Resume> storage = new HashMap<>();

    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> doCopyAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isStorageContainsResume(Object index) {
        return (storage.containsKey((String) index));
    }

    @Override
    protected void doSave(Object index, Resume r) {
        storage.put((String) index, r);
    }

    @Override
    protected void doUpdate(Object index, Resume r) {
        storage.put((String) index, r);
    }

    @Override
    protected void doDelete(Object index) {
        storage.remove(index);
    }

    @Override
    protected Resume getResume(Object index) {
        return storage.get(index);
    }
}