package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pradd on 29.12.2017.
 */
public class MapStorage extends AbstractStorage {

    Map<String, Resume> storage = new HashMap<>();

    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[storage.size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected String checkIndex(String uuid) {
        if (storage.containsKey(uuid)) {
            return uuid;
        }
        return null;
    }

    @Override
    protected boolean isStorageContainsResume(Object index) {
        return index != null && storage.containsKey(index);
    }

    @Override
    protected void doSave(Object index, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void doUpdate(Object index, Resume r) {
        storage.replace(r.getUuid(), r);
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
