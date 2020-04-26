package org.example.ud_javamultithread.D_LockObjects;

/**
 * Multiple locks to speed up complex multi-threaded code. Define shared
 * objects: list1 and list2 then synchronize these objects. Mainly discussing
 * making the method synchronized or making an object inside the method
 * synchronized, By defining two different locks we say that one thread may
 * execute the stageOne while other executes stageTwo.
 * @author Z.B. Celik <celik.berkay@gmail.com>
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Worker {

    private Random random = new Random();

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    public void stageOne() {
        synchronized (lock1) {
            sleepy(1);
            list1.add(random.nextInt(100));
        }
    }

    public void stageTwo() {
        synchronized (lock2) {
            sleepy(1);
            list2.add(random.nextInt(100));
        }
    }

    public void process() {
        for (int i = 0; i < 1000; i++) {
            stageOne();
            stageTwo();
        }
    }

    public void main() {
        System.out.println("Starting ...");
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(()-> process());
        Thread t2 = new Thread(()-> process());

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("Time taken: " + (end - start));
        System.out.println("List1: " + list1.size() + "; List2: " + list2.size());
    }

    private static void sleepy(long value){
        try {
            Thread.sleep(value);
        } catch (InterruptedException e) {
            //do your work here
            e.printStackTrace();
        }
    }
}