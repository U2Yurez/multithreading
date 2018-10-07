package com.yurakoreyba.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class App {
    public static void main(String args[]){
        Processor p1 = new Processor();
        p1.doWork();

    }
}

class Processor {
    private AtomicInteger count = new AtomicInteger(0);

    public void doWork(){
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for(int i =0; i < 10000; i++){
                    count.getAndIncrement();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                for(int i =0; i < 10000; i++){
                    count.getAndIncrement();
                }
            }
        });
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count);
    }

}