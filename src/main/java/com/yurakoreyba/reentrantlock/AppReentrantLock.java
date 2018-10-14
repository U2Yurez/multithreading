package com.yurakoreyba.reentrantlock;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AppReentrantLock {
    public static void main(String args[]) throws InterruptedException {
        final Produccer produccer = new Produccer();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    produccer.doFirstThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    produccer.doSecondThread();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        produccer.finish();
    }
}

class Produccer {

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    int count = 0;
    void increment(){
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }
    public void doFirstThread() throws InterruptedException {
        lock.lock();
        System.out.println("Waiting...");
        condition.await();

        System.out.println("Woken up!!!");

        increment();
        lock.unlock();
    }

    public void doSecondThread() throws InterruptedException {
        Thread.sleep(1000);
        lock.lock();

        System.out.println("Press the return key");
        new Scanner(System.in).nextLine();
        System.out.println("Got return key");

        condition.signal();
        increment();
        lock.unlock();
    }

    public void finish() {
        System.out.println("Count is " + count);
    }
}
