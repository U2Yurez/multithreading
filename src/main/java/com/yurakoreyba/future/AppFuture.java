package com.yurakoreyba.future;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class AppFuture {
    public static void main(String args[]) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<Integer> result = executor.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Random random = new Random();
                    System.out.println("Starting " + Thread.currentThread().getName());
                    int duration = random.nextInt(4000);
                    Thread.sleep(duration);
                    System.out.println("Finished " + Thread.currentThread().getName());

                    return duration;
                }
            });
            resultList.add(result.get());

        }
        executor.shutdown();
        int total = 0;
        for (Integer i : resultList) {
            total += i;

        }
        System.out.println("Total Duration is " + total);
    }
}