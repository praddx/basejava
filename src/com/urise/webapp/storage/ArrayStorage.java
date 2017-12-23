package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    private Resume[] storage = new Resume[10000];
    private int size = 0;

    //method clears database
    public void clear() {
        storage = new Resume[storage.length];
        size = 0;
    }

    //method saves new resume in the database
    public void save(Resume r) {
        //checking if the database is not full
        if (size == storage.length) {
            System.out.println("Database is full! Delete at least one resume to save new one.");
            return;
        }

        //checking that this resume haven't been saved in the database already
        if (findResume(r.getUuid()) >= 0) {
            System.out.println("This resume already saved in the database!");
            //saving new resume
        } else {
            storage[size] = r;
            size++;
        }
    }

    //method updates existing resume
    public void update(Resume r) {
        //checking that resume with given uuid exists in the database
        int index = findResume(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
        } else {
            System.out.println("There is no such resume in the database!");
        }
    }

    //method returns resume with the given uuid from the database
    public Resume get(String uuid) {
        int index = findResume(uuid);

        if (index >= 0) {
            return storage[index];
        }

        return null;
    }

    //method deletes resume with the given uuid from the database
    public void delete(String uuid) {

        int index = findResume(uuid);
        if (index >= 0) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("There is no such resume in the database!");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {

        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    //method finds resume with the given uuid in the database and returns its index
    //if there is no resume with the given uuid in the database returns -1
    private int findResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}





















