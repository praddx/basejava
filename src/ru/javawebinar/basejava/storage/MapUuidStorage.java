package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

/**
 * Created by Pradd on 29.12.2017.
 */
public class MapUuidStorage extends AbstractStorage {

    Map<String, Resume> storage = new HashMap<>();

    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumes = new ArrayList<>(storage.values());
        resumes.sort(Comparator.comparing(Resume::getFullName));
        return resumes;
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
        return (index != null && storage.containsKey(index));
    }

    @Override
    protected void doSave(Object index, Resume r) {
        storage.put((String) index, r);
    }

    @Override
    protected void doUpdate(Object index, Resume r) {
        storage.replace((String) index, r);
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
