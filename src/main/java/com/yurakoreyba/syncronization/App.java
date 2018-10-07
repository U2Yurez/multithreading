package com.yurakoreyba.syncronization;

public class App {
    public static void main(String args[]){
        Processor p1 = new Processor();
        p1.doWork();

    }
}

class Processor {
    private int count = 0;
    private synchronized  void increment(){
        count++;
    }

    public void doWork(){
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for(int i =0; i < 10000; i++){
                    increment();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                for(int i =0; i < 10000; i++){
                    increment();
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