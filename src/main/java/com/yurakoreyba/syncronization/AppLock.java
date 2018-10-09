package com.yurakoreyba.syncronization;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class LockExample {
    Random random = new Random();
    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();

    Object lock1 = new Object();
    Object lock2 = new Object();

    public void processOne() throws InterruptedException {
        synchronized (lock1) {
            Thread.sleep(1);
        }
        list1.add(random.nextInt(100));
    }

    public synchronized void processTwo() throws InterruptedException {
        synchronized (lock2) {
            Thread.sleep(1);
        }
        list2.add(random.nextInt(100));
    }

    public void process() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            processOne();
            processTwo();
        }
    }

    public void startExecution() throws InterruptedException {
        System.out.println("Starting .....");
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            try {
                process();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                process();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        long end = System.currentTimeMillis();
        System.out.println("Time take  " + (end - start));
        System.out.println("List1 size " + list1.size());
        System.out.println("List2 size " + list2.size());
    }


}


public class AppLock {
    public static void main(String args[]) throws InterruptedException {
        new LockExample().startExecution();
    }
}