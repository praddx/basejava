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
    protected Object getIndex(String uuid) {
        if (storage.containsKey(uuid)) {
            return uuid;
        }
        return null;
    }

    @Override
    protected void insertResume(Object index, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void updateResume(Object index, Resume r) {
        storage.replace(r.getUuid(), r);
    }

    @Override
    protected void deleteResume(Object index) {
        storage.remove(index);
    }

    @Override
    protected Resume getResume(Object index) {
        return storage.get(index);
    }
}
