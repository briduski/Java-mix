package org.example.ud_javamultithread.E_ThreadPools;


/**
 * ThreadPool ("number of workers in a factory")
 * <br><br>
 * Codes with minor comments are from
 * <a href="http://www.caveofprogramming.com/youtube/">
 * <em>http://www.caveofprogramming.com/youtube/</em>
 * </a>
 * <br>
 * also freely available at
 * <a href="https://www.udemy.com/java-multithreading/?couponCode=FREE">
 *     <em>https://www.udemy.com/java-multithreading/?couponCode=FREE</em>
 * </a>
 *
 * @author Z.B. Celik <celik.berkay@gmail.com>
 */
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable {
    private int id;
    public Processor(int id) {
        this.id = id;
    }
    public void run() {
      //  System.out.println("["+Thread.currentThread().getId() + "] "+"Starting: " + id);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
        System.out.println("["+Thread.currentThread().getId() + "] "+"Completed: " + id);

    }
}

public class App {
    public static void main(String[] args) {
        /**
         * Created 2 threads, and assign tasks (Processor(i).run) to the threads
         */
        ExecutorService executor = Executors.newFixedThreadPool(2);//2 Threads
        System.out.println("Starting main app: " + Instant.ofEpochMilli(System.currentTimeMillis()) );
        for (int i = 0; i < 6; i++) { // call the (Processor(i).run) 2 times with 2 threads
            executor.submit(new Processor(i));
        }
        executor.shutdown();
        System.out.println("All tasks submitted.");
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException ignored) {
        }
        System.out.println("All tasks completed."+ Instant.ofEpochMilli(System.currentTimeMillis()) );
    }
}