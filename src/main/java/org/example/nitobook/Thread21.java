package org.example.nitobook;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Thread21 {
    public static void main(String[] args) {
        ExecutorService executorService =  Executors.newFixedThreadPool(3);
        final ReentrantLock lock = new ReentrantLock();
        executorService.execute(new Worker21("A", lock));
        executorService.execute(new Worker21("B", lock));
        executorService.execute(new Worker21("C", lock));

        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdownNow();
    }
}
class Worker21 implements Runnable{
    private final Lock lock;
    private final String name;

    public Worker21(String name, Lock lock) {
        this.lock = lock;
        this.name = name;
    }
    @Override
    public void run() {
        System.out.println(name + " started");
        lock.lock();
        try {
            System.out.println("Critical region "+name);
            System.out.println("Work "+name);
            Thread.sleep(2000);
            System.out.println("Terminates "+name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}