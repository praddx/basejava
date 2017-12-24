package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Test for com.javawebinar.basejava.storage.ArrayStorage
 */
public class MainTestSortedArrayStorage {
    static final SortedArrayStorage SORTED_ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume();
        r1.setUuid("uuid1");
        Resume r2 = new Resume();
        r2.setUuid("uuid2");
        Resume r3 = new Resume();
        r3.setUuid("uuid3");
        Resume r7 = new Resume();
        r7.setUuid("uuid7");
        Resume r8 = new Resume();
        r8.setUuid("uuid8");
        Resume r5 = new Resume();
        r5.setUuid("uuid5");
        Resume r5New = new Resume();
        r5New.setUuid("uuid5");

        SORTED_ARRAY_STORAGE.save(r1);
        SORTED_ARRAY_STORAGE.save(r2);
        SORTED_ARRAY_STORAGE.save(r3);
        SORTED_ARRAY_STORAGE.save(r3);
        SORTED_ARRAY_STORAGE.save(r8);
        SORTED_ARRAY_STORAGE.save(r7);
        SORTED_ARRAY_STORAGE.save(r5);

        System.out.println("Get r1: " + SORTED_ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + SORTED_ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + SORTED_ARRAY_STORAGE.get("dummy"));

        printAll();
        SORTED_ARRAY_STORAGE.delete(r3.getUuid());
        printAll();

        System.out.println();
        SORTED_ARRAY_STORAGE.update(r5New);

        SORTED_ARRAY_STORAGE.clear();
        printAll();
        System.out.println("Size: " + SORTED_ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : SORTED_ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
