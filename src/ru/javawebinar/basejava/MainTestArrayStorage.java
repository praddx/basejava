package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.ArrayStorage;

/**
 * Test for com.javawebinar.basejava.storage.ArrayStorage
 */
public class MainTestArrayStorage {
    static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume("uuid1");
        r1.setUuid("uuid1");
        Resume r2 = new Resume("uuid1");
        r2.setUuid("uuid2");
        Resume r3 = new Resume("uuid1");
        r3.setUuid("uuid3");
        Resume r7 = new Resume("uuid1");
        r7.setUuid("uuid7");
        Resume r8 = new Resume("uuid1");
        r8.setUuid("uuid8");
        Resume r5 = new Resume("uuid1");
        r5.setUuid("uuid5");
        Resume r5New = new Resume("uuid1");
        r5New.setUuid("uuid5");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r8);
        ARRAY_STORAGE.save(r7);
        ARRAY_STORAGE.save(r5);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();

        ARRAY_STORAGE.delete(r3.getUuid());
        printAll();
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println();
        ARRAY_STORAGE.update(r5New);


        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
