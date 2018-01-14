package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.List;

/**
 * Array based storage for Resumes
 */
public interface Storage {

    //method clears database
    void clear();

    //method saves new resume in the database
    void save(Resume r);

    //method updates existing resume
    void update(Resume r);

    //method returns resume with the given uuid from the database
    Resume get(String uuid);

    //method deletes resume with the given uuid from the database
    void delete(String uuid);

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    List<Resume> getAllSorted();

    int size();

}





















