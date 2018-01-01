package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

/**
 * Created by Pradd on 29.12.2017.
 */
public abstract class AbstractStorage implements Storage {

    protected Storage storage;

    public abstract void clear();

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else {
            insertResume(index, r);
        }
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            updateResume(index, r);
        } else {
            throw new NotExistStorageException(r.getUuid());
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(index, uuid);
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            deleteResume(index, uuid);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public abstract Resume[] getAll();

    public abstract int size();

    protected abstract int getIndex(String uuid);

    protected abstract void insertResume(int index, Resume r);

    protected abstract void updateResume(int index, Resume r);

    protected abstract void deleteResume(int index, String uuid);

    protected abstract Resume getResume(int index, String uuid);
}
