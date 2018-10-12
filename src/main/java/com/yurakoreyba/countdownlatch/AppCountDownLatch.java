package com.yurakoreyba.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppCountDownLatch {
    public static void main(String args[]) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10) ;
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            executor.submit(new Processor(countDownLatch));
        }
        countDownLatch.await();
        System.out.println("Completed");
        executor.shutdown();
    }
}

class Processor implements Runnable {
    CountDownLatch countDownLatch;

    Processor(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println("Started with thread number " + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();

    }
}