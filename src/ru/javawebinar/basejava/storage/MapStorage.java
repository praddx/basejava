package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {

    Map<String, Resume> storage = new HashMap<>();

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
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean isStorageContainsResume(Object resume) {
        return resume != null;
    }

    @Override
    protected void doSave(Object resume, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void doUpdate(Object resume, Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Object resume) {
        storage.remove(((Resume) resume).getUuid());
    }

    @Override
    protected Resume getResume(Object resume) {
        return (Resume) resume;
    }
}
