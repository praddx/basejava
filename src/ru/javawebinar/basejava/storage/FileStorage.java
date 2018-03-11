package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.serialization.SerializationStrategy;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FileStorage extends AbstractStorage<File> {
    private File directory;
    private SerializationStrategy serializer;

    protected FileStorage(File directory, SerializationStrategy serializer) {
        Objects.requireNonNull(directory, "directory must not be null");
        Objects.requireNonNull(serializer, "serializationStrategy not set");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not a directory.");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable.");
        }
        this.directory = directory;
        this.serializer = serializer;
    }

    @Override
    public void clear() {
        Arrays.stream(Objects.requireNonNull(directory.listFiles())).forEach(File::delete);
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void doSave(File file, Resume r) {
        try {
            file.createNewFile();
            serializer.doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void doUpdate(File file, Resume r) {
        try {
            serializer.doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void doDelete(File file) {
        file.delete();
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return serializer.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    public List<Resume> doCopyAll() {
        return Arrays.stream(Objects.requireNonNull(directory.listFiles()))
                .map(this::doGet).collect(Collectors.toList());
    }

    @Override
    public int size() {
        return directory.list().length;
    }

    public SerializationStrategy getSerializer() {
        return serializer;
    }

    public void setSerializer(SerializationStrategy serializer) {
        this.serializer = serializer;
    }
}
