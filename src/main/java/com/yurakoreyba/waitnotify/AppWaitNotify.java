package com.yurakoreyba.waitnotify;


import java.util.Scanner;

public class AppWaitNotify {
    public static void main(String args[]) throws InterruptedException {
        final Produccer produccer = new Produccer();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    produccer.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    produccer.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}

class Produccer {

    public void produce() throws InterruptedException {
        synchronized (this){
            System.out.println("Starting produce thread");
            wait();
            System.out.println("Resumed");
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(2000);
        Scanner scanner = new Scanner(System.in);
        synchronized (this){
            System.out.println("Starting consume thread");
            System.out.println("Waiting for press a key");
            scanner.nextLine();
            System.out.println("The key is pressed");
            notify();
            Thread.sleep(5000);
        }
    }
}
