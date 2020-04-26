package org.example.nitobook;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Thread16 {
    // CountDownLatch , is a synch object, allowd 1+ threads to wait at a "gate" until another thread opens the gate,
    // after which the waiting threads can proceed
    // is a primary count and has op., where a thread wait until the counter is counting down to the 0

    // await
    // if the current count is zero then this method returns immediately.
    // If the current count is greater than zero then the current
    // thread becomes disabled for thread scheduling purposes and lies dormant until one of two things happen:

    private final static int N = 3;
    public static void main(String[] args) {
        final CountDownLatch startLatch = new CountDownLatch(1);
        final CountDownLatch doneLatch = new CountDownLatch(N);
        Runnable worker = new Worker(startLatch, doneLatch);
        ExecutorService executor = Executors.newFixedThreadPool(N);
        for (int i = 0; i<N;i++){
            executor.execute(worker);
        }
        System.out.println("Main working hard, now it comes a sleep ... ");
        try {
            Thread.sleep(1000);
            startLatch.countDown();
            System.out.println(" !!!! Semaphore on, startlatch count 0 / Waiting for all  threads to finish ");
            doneLatch.await();
            System.out.println(" !!!! Main Donelatch=0, all threads finished ");
            executor.shutdownNow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
class Worker implements Runnable {
    private static final Random rand = new Random();
    private final CountDownLatch start;
    private final CountDownLatch done;
    public  Worker (CountDownLatch start, CountDownLatch done ){
        this.start = start;
        this.done = done;
    }

    @Override
    public void run() {
        try {
            print(" - -  Worker, start await, start.getCount(): "+start.getCount());
            start.await(); // We dont start until the sleep has been completed, and the countdown is 0, then this wait will continue flow
            print(" * *  Worker, after await, done.getCount(): "+done.getCount());
            Thread.sleep(rand.nextInt(1000));
            done.countDown();
            print(" < < finished Worker, done.getCount(): "+done.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public static void print (String text){
        System.out.println("[" + Thread.currentThread().getId() + "] "+text);
    }
}
