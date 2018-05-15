package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10000;
    private static int counter;

    private static final Object lock = new Object();

    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
                new IllegalStateException();
            }
        };
        thread0.start();

        new Thread(() -> System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState())).start();
        System.out.println(thread0.getState());
        final MainConcurrency mainConcurrency = new MainConcurrency();
        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {

                    mainConcurrency.inc();

                }
            });
            thread.start();
            threads.add(thread);
        }

        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(counter);

        Thread threadA = new Thread(() -> mainConcurrency.aLock());
        Thread threadB = new Thread(() -> mainConcurrency.bLock());

        threadA.start();
        threadB.start();



    }

    private void aLock() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + " in lock A");
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + " in lock B");
            }
        }
    }

    private void bLock() {
        synchronized (lockB) {
            System.out.println(Thread.currentThread().getName() + " in lock B");
            synchronized (lockA) {
                System.out.println(Thread.currentThread().getName() + " in lock A");
            }
        }
    }

    private synchronized void inc() {
        //double a = Math.sin(13);

            counter++;


    }
}
