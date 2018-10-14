package com.yurakoreyba.lowlevelsynchronization;

public class AppLLS {
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
