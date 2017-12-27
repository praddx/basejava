package ru.javawebinar.basejava.exception;

/**
 * Created by Pradd on 25.12.2017.
 */
public class ExistStorageException extends StorageException {

    public ExistStorageException (String uuid) {
        super("Resume " + uuid + " already saved in the database!", uuid);
    }
}
