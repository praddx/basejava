package ru.javawebinar.basejava;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class MainFileNameRecursivePrint {

    public static void main(String[] args) {
        File dir = new File("./src/ru/javawebinar/basejava");
        printFileName(dir);
    }

    public static void printFileName(File path) {
        if (path.isFile()) {
            System.out.println(path.getName());
        } else {
            Arrays.stream(Objects.requireNonNull(path.list()))
                    .forEach(f -> {
                        try {
                            printFileName(new File(path.getCanonicalPath() + "/" + f));
                        } catch (IOException e) {
                            throw new RuntimeException("error!", e);
                        }
                    });
        }

    }
}
