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
    protected int getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertResume(int index, Resume r) {
        storage.add(r);
    }

    @Override
    protected void deleteResume(int index, String uuid) {
        storage.remove(index);
    }

    @Override
    protected void updateResume(int index, Resume r) {
        storage.set(index, r);
    }

    @Override
    protected Resume getResume(int index, String uuid) {
        return storage.get(index);
    }
}
