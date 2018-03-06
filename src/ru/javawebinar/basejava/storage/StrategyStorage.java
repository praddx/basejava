package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.List;

public class StrategyStorage implements Storage {

    private Storage storage;

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void clear() {
        this.storage.clear();
    }

    @Override
    public void save(Resume r) {
        this.storage.save(r);
    }

    @Override
    public void update(Resume r) {
        this.storage.update(r);
    }

    @Override
    public Resume get(String uuid) {
        return this.storage.get(uuid);
    }

    @Override
    public void delete(String uuid) {
        this.storage.delete(uuid);
    }

    @Override
    public List<Resume> getAllSorted() {
        return this.storage.getAllSorted();
    }

    @Override
    public int size() {
        return this.storage.size();
    }
}
