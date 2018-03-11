package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.serialization.SerializationStrategy;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PathStorage extends AbstractStorage<Path> {
    private Path directory;
    private SerializationStrategy serializer;

    protected PathStorage(String dir, SerializationStrategy serializer) {
        Objects.requireNonNull(dir, "directory must not be null");
        Path directory = Paths.get(dir);
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
        Objects.requireNonNull(serializer, "serializationStrategy not set");
        this.directory = directory;
        this.serializer = serializer;
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path) && !Files.isDirectory(path);
    }

    @Override
    protected void doSave(Path path, Resume r) {
        try {
            Files.createFile(path);
            serializer.doWrite(r, Files.newOutputStream(path));
        } catch (IOException e) {
            throw new StorageException("IO error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected void doUpdate(Path path, Resume r) {
        try {
            serializer.doWrite(r, Files.newOutputStream(path));
        } catch (IOException e) {
            throw new StorageException("IO error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("IO error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return serializer.doRead(Files.newInputStream(path));
        } catch (IOException e) {
            throw new StorageException("IO error", path.getFileName().toString(), e);
        }
    }

    @Override
    public List<Resume> doCopyAll() {
        try {
            return Files.list(directory)
                    .map(this::doGet).collect(Collectors.toList());
        } catch (IOException e) {
            throw new StorageException("IO error", directory.getFileName().toString(), e);
        }
    }

    @Override
    public int size() {
        int size = 0;
        try {
            size = (int) Files.list(directory).count();
        } catch (IOException e) {
            throw new StorageException("IO error", directory.getFileName().toString(), e);
        }
        return size;
    }

    public SerializationStrategy getSerializer() {
        return serializer;
    }

    public void setSerializer(SerializationStrategy serializer) {
        this.serializer = serializer;
    }
}
