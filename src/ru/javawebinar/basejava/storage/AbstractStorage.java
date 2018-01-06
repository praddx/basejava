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
        Object index = getIndex(r.getUuid());
        if (index == null || (index instanceof Integer && (Integer) index < 0)) {
            insertResume(index, r);
        } else {
            throw new ExistStorageException(r.getUuid());
        }
    }

    public void update(Resume r) {
        Object index = getIndex(r.getUuid());
        if (index == null || (index instanceof Integer && (Integer) index < 0)) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            updateResume(index, r);
        }
    }

    public Resume get(String uuid) {
        Object index = getIndex(uuid);
        if (index == null || (index instanceof Integer && (Integer) index < 0)) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(index);
    }

    public void delete(String uuid) {
        Object index = getIndex(uuid);
        if (index == null || (index instanceof Integer && (Integer) index < 0)) {
            throw new NotExistStorageException(uuid);
        }
        deleteResume(index);
    }

    public abstract Resume[] getAll();

    public abstract int size();

    protected abstract Object getIndex(String uuid);

    protected abstract void insertResume(Object index, Resume r);

    protected abstract void updateResume(Object index, Resume r);

    protected abstract void deleteResume(Object index);

    protected abstract Resume getResume(Object index);
}
