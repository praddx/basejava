package ru.javawebinar.basejava;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class MainFileNameRecursivePrint {

    public static void main(String[] args) {
        File dir = new File("./src/ru/javawebinar/basejava");
        printFileNameWithTabs(dir, 0);
    }

    public static void printFileNameWithTabs(File path, int numberOfTabs) {
        if (path.isFile()) {
            for (int i = 0; i < numberOfTabs; i++) {
                System.out.print("\t");
            }
            System.out.println("File: " + path.getName());
        } else {
            for (int i = 0; i < numberOfTabs; i++) {
                System.out.print("\t");
            }
            System.out.println("Directory: " + path.getName());
            Arrays.stream(Objects.requireNonNull(path.listFiles()))
                    .forEach(f -> printFileNameWithTabs(f, numberOfTabs + 1));
        }
    }
}
