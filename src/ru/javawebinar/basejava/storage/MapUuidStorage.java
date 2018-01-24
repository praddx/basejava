package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

/**
 * Created by Pradd on 29.12.2017.
 */
public class MapUuidStorage extends AbstractStorage<String> {

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
    protected boolean isExist(String serchKey) {
        return (storage.containsKey((String) serchKey));
    }

    @Override
    protected void doSave(String serchKey, Resume r) {
        storage.put((String) serchKey, r);
    }

    @Override
    protected void doUpdate(String serchKey, Resume r) {
        storage.put((String) serchKey, r);
    }

    @Override
    protected void doDelete(String serchKey) {
        storage.remove(serchKey);
    }

    @Override
    protected Resume doGet(String serchKey) {
        return storage.get(serchKey);
    }
}
