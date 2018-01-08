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
        Object index = checkIndex(r.getUuid());
        checkResumeExists(index, r.getUuid());
        insertResume(index, r);
    }

    public void update(Resume r) {
        Object index = checkIndex(r.getUuid());
        checkResumeNotExists(index, r.getUuid());
        updateResume(index, r);

    }

    public Resume get(String uuid) {
        Object index = checkIndex(uuid);
        checkResumeNotExists(index, uuid);
        return getResume(index);
    }

    public void delete(String uuid) {
        Object index = checkIndex(uuid);
        checkResumeNotExists(index, uuid);
        deleteResume(index);
    }


    public abstract Resume[] getAll();

    public abstract int size();

    protected void checkResumeExists(Object index, String uuid) {
        if (isStorageContainsResume(index)) {
            throw new ExistStorageException(uuid);
        }
    }

    protected void checkResumeNotExists(Object index, String uuid) {
        if (!isStorageContainsResume(index)) {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract Object checkIndex(String uuid);

    protected abstract boolean isStorageContainsResume(Object index);

    protected abstract void insertResume(Object index, Resume r);

    protected abstract void updateResume(Object index, Resume r);

    protected abstract void deleteResume(Object index);

    protected abstract Resume getResume(Object index);
}