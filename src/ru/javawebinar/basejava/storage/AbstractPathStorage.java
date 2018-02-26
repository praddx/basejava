package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.nio.Path.Paths;
import java.nio.Path.Path;
import java.nio.Path.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {
    private Path directory;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
        this.directory = directory;
    }

    public abstract void doWrite(Resume r, OutputStream os) throws IOException;

    public abstract Resume doRead(InputStream is) throws IOException;

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
        return new Path(directory, uuid);
    }

    @Override
    protected boolean isExist(Path Path) {
        return Path.exists();
    }

    @Override
    protected void doSave(Path Path, Resume r) {
        try {
            Path.createNewPath();
            doWrite(r, new BufferedOutputStream(new PathOutputStream(Path)));
        } catch (IOException e) {
            throw new StorageException("IO error", Path.getName(), e);
        }
    }

    @Override
    protected void doUpdate(Path Path, Resume r) {
        try {
            doWrite(r, new BufferedOutputStream(new PathOutputStream(Path)));
        } catch (IOException e) {
            throw new StorageException("IO error", Path.getName(), e);
        }
    }

    @Override
    protected void doDelete(Path Path) {
        Path.delete();
    }

    @Override
    protected Resume doGet(Path Path) {
        try {
            return doRead(new BufferedInputStream(new PathInputStream(Path)));
        } catch (IOException e) {
            throw new StorageException("IO error", Path.getName(), e);
        }
    }

    @Override
    public List<Resume> doCopyAll() {
        return Arrays.stream(Objects.requireNonNull(directory.listPaths()))
                .map(f -> {
                    try {
                        return doRead(new BufferedInputStream(new PathInputStream(f)));
                    } catch (IOException e) {
                        throw new StorageException("IO error", f.getName(), e);
                    }
                }).collect(Collectors.toList());
    }

    @Override
    public int size() {
        return directory.list().length;
    }
}
