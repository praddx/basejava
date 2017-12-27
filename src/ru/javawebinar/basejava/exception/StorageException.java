package ru.javawebinar.basejava.exception;

/**
 * Created by Pradd on 25.12.2017.
 */
public class StorageException extends RuntimeException {
    private final String uuid;

    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}
