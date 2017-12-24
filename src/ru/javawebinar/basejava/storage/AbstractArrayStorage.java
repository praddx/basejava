package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    //method clears database
    public void clear() {

        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    //method saves new Resume in the database (template method)
    public void save(Resume r) {

        //checking if the database is full
        if (size == storage.length) {
            System.out.println("Database is full! Delete at least one resume to save new one.");
            return;
        }

        //checking if database already contains this Resume
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            System.out.println("This resume already saved in the database!");
            //if not inserting new Resume
        } else {
            //invariant part of the template method
            //inserts new Resume into database
            insertResume(index, r);
            size++;
        }
    }

    public void update(Resume r) {

        //checking that resume with given uuid exists in the database
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
            System.out.println("Resume " + r.toString() + " has was updated!");
        } else {
            System.out.println("There is no such resume in the database!");
        }
    }

    //method deletes Resume with given uuid from the database (template method)
    public void delete(String uuid) {

        //checking if Resume with given uuid exists in the database
        int index = getIndex(uuid);
        if (index >= 0) {

            //invariant part of the template method
            //deletes Resume from the database
            deleteResume(index);

            storage[size] = null;
            size--;

        } else {

            System.out.println("There is no such resume in the database!");
        }
    }

    //method finds and returns Resume with the given uuid
    public Resume get(String uuid) {

        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " does not exist in database");
            return null;
        }
        return storage[index];

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {

        return Arrays.copyOfRange(storage, 0, size);
    }

    //returns amount of resumes in the database
    public int size() {

        return size;
    }

    //method finds and returns index of the Resume with the given uuid in the database
    protected abstract int getIndex(String uuid);

    //method inserts given Resume into database
    protected abstract void insertResume(int index, Resume r);

    //method deletes Resume saved in the given index of the database
    protected abstract void deleteResume(int index);

}





















