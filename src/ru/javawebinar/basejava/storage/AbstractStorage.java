package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Pradd on 29.12.2017.
 */
public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    public abstract void clear();

    protected abstract SK getSearchKey(String uuid);

    protected abstract boolean isExist(SK searchKey);

    protected abstract void doSave(SK searchKey, Resume r);

    protected abstract void doUpdate(SK searchKey, Resume r);

    protected abstract void doDelete(SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    public abstract List<Resume> doCopyAll();

    public abstract int size();

    public void save(Resume r) {
        LOG.info("Update " + r);
        SK searchKey = getNotExistedSearchKey(r.getUuid());
        doSave(searchKey, r);
    }

    public void update(Resume r) {
        LOG.info("Save " + r);
        SK searchKey = getExistedSearchKey(r.getUuid());
        doUpdate(searchKey, r);
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK searchKey = getExistedSearchKey(uuid);
        return doGet(searchKey);
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK searchKey = getExistedSearchKey(uuid);
        doDelete(searchKey);
    }

    public List<Resume> getAllSorted() {
        LOG.info("getAllSorted");
        List<Resume> copyAll = doCopyAll();
        Collections.sort(copyAll);
        return copyAll;
    }

    private SK getExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.info("Rsume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.info("Resume " + uuid + " already exists");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}
