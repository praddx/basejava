package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ObjectStreamStorage extends AbstractFileStorage {

    protected ObjectStreamStorage(File directory) {
        super(directory);
    }

    @Override
    public void doWrite(Resume r, OutputStream file) throws IOException {

    }

    @Override
    public Resume doRead(InputStream file) throws IOException {
        return null;
    }
}
