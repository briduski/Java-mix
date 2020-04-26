package org.example.ud_javamultithread.N_InterruptingThreads14;

import java.util.Random;
import java.util.concurrent.*;

/**
 * <b>How to interrupt running threads in Java using the built-in thread
 * interruption mechanism.</b>
 * <br><br>
 * Source:
 * <a href="http://www.javamex.com/tutorials/threads/thread_interruption.shtml">
 * http://www.javamex.com/tutorials/threads/thread_interruption.shtml</a>
 * <p>
 * Incidentally, it is important NOT to confuse thread interruption with either
 * software interrupts (where the CPU automatically interrupts the current
 * instruction flow in order to call a registered piece of code periodically— as
 * in fact happens to drive the thread scheduler) and hardware interrupts (where
 * the CPU automatically performs a similar task in response to some hardware
 * signal).
 * <br><br>
 * Codes with minor comments are from
 * <a href="http://www.caveofprogramming.com/youtube/">
 * <em>http://www.caveofprogramming.com/youtube/</em>
 * </a>
 * <br>
 * also freely available at
 * <a href="https://www.udemy.com/java-multithreading/?couponCode=FREE">
 * <em>https://www.udemy.com/java-multithreading/?couponCode=FREE</em>
 * </a>
 *
 * @author Z.B. Celik <celik.berkay@gmail.com>
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
      p3();
    }
    public static void p3()throws InterruptedException {
        System.out.println("Starting.");
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<?> fu = executor.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                for (int i = 0; i < 1E8; i++) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.printf("Interrupted at %d !!!", i);
                        break;
                    }
                }
                return null;
            }
        });

        executor.shutdown();
        Thread.sleep(1);

        /*
        in this example, there are different ways you can interrupt a thread
        execution.
         */

        //JavaDoc: http://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Future.html#cancel-boolean-
        fu.cancel(true);

        //JavaDoc: http://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html#shutdownNow--
        executor.shutdownNow();

        executor.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("Finished.");
    }
    public static void p2() throws InterruptedException {
        Thread t1 = new Thread(()-> {
            Random rand = new Random();
            for (int i =0; i<1E8;i++){
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted d d ");
                    break;
                }
                Math.sin(rand.nextDouble());
            }
        });
        t1.start();
        Thread.sleep(500);
        t1.interrupt(); // it does not really stop the thread, it sets a flag, tellig the thread
        // that is being interrupted
        try {
            //join waiting to finish ...
            t1.join();
            System.out.println("Finish joiinnn");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public static void p1() throws InterruptedException {
        Thread t1 = new Thread(()-> {
            Random rand = new Random();
            for (int i =0; i<1E8;i++){
                // you have to ask if the thread has been interrupted
                if (Thread.currentThread().isInterrupted()) {
                    System.out.printf("Interrupted at %d !!!", i);
                    break;
                }
                Math.sin(rand.nextDouble());
            }
        });
        t1.start();
        Thread.sleep(500);
        t1.interrupt(); // it does not really stop the thread, it sets a flag, tellig the thread
        // that is being interrupted
        try {
            //join waiting to finish ...
            t1.join();
            System.out.println("Finish joiinnn");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}