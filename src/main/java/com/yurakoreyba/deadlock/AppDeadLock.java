package com.yurakoreyba.deadlock;


import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AppDeadLock {
    public static void main(String args[]) throws InterruptedException {
        final Produccer produccer = new Produccer();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                produccer.doFirstThread();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                produccer.doSecondThread();
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
    Account acc1 = new Account();
    Account acc2 = new Account();

    Lock lock1 = new ReentrantLock();
    Lock lock2 = new ReentrantLock();

    public void doFirstThread() {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            lock1.lock();
            lock2.lock();
            try {
                Account.transfer(acc1, acc2, random.nextInt(100));

            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void doSecondThread() {
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            lock2.lock();
            lock1.lock();
            try {
                Account.transfer(acc2, acc1, random.nextInt(100));

            } finally {
                lock2.unlock();
                lock1.unlock();
            }
        }
    }

    public void finish() {
        System.out.println("Account 1 balance is " + acc1.getBalance());
        System.out.println("Account 2 balance is " + acc2.getBalance());
        System.out.println("Total amount = " + (acc1.getBalance() + acc2.getBalance()));
    }
}