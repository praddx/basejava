package ru.javawebinar.basejava.exception;

import java.io.NotSerializableException;

/**
 * Created by Pradd on 25.12.2017.
 */
public class NotExistStorageException extends StorageException {

    public NotExistStorageException(String uuid) {
        super("Resume " + uuid + " does not exist in the database!", uuid);
    }
}
