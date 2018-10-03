package com.yurakoreyba.demo1;

public class App {

    public static void main(String args[]){
//        new Runner().start();
//        new Runner().start();
//        new Thread(new Runner2()).start();
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i<10; i ++) {
                    System.out.println("Hello " + i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

class Runner extends Thread {
    public void run(){
        for (int i = 0; i<10; i ++) {
            System.out.println("Hello " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class Runner2 implements Runnable {
    public void run(){
        for (int i = 0; i<10; i ++) {
            System.out.println("Hello " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}