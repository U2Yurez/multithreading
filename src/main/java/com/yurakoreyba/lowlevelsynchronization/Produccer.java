package com.yurakoreyba.lowlevelsynchronization;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Produccer {
    LinkedList<Integer> list = new LinkedList<>();
    final static Integer LIMIT = 10;
    Object lock = new Object();

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (lock) {
                while (list.size() == 10) {
                    lock.wait();
                }
                list.add(value++);
            }
        }
    }

    public void consume() throws InterruptedException {
        Random random = new Random();
        while (true) {

            synchronized (lock) {
                while (list.size() == 0) {
                    lock.wait();
                }
                System.out.print("List size is" + list.size());
                int value = list.removeFirst();
                System.out.println("; value is" + value);
                lock.notify();
            }
            Thread.sleep(random.nextInt(1000));
        }

    }
}
