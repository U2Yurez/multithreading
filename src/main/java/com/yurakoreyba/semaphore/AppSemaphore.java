package com.yurakoreyba.semaphore;

import java.util.concurrent.Semaphore;

public class AppSemaphore {
    public static void main(String[] args) {
        //https://metanit.com/java/tutorial/8.6.php
        Semaphore sem = new Semaphore(1, true); // 1 разрешение
        CommonResource res = new CommonResource();
        new Thread(new CountThread(res, sem, "CountThread 1")).start();
        new Thread(new CountThread(res, sem, "CountThread 2")).start();
        new Thread(new CountThread(res, sem, "CountThread 3")).start();
        new Thread(new CountThread(res, sem, "CountThread 4")).start();
        new Thread(new CountThread(res, sem, "CountThread 5")).start();
    }
}

class CommonResource{
    int x=0;
}
class CountThread implements Runnable{

    CommonResource res;
    Semaphore sem;
    String name;
    CountThread(CommonResource res, Semaphore sem, String name){
        this.res=res;
        this.sem=sem;
        this.name=name;
    }

    public void run(){

        try{
            System.out.println(name + " is waiting for a permit");
            sem.acquire();
            res.x=1;
            for (int i = 1; i < 5; i++){
                System.out.println(this.name + ": " + res.x);
                res.x++;
                Thread.sleep(1000);
            }
        }
        catch(InterruptedException e){System.out.println(e.getMessage());}
        System.out.println(name + " release the permit");
        sem.release();
    }
}