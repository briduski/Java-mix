package org.example.ud_javamultithread.I_LowLevelProducerConsumer;


/**
 * How to implement the Producer-Consumer pattern using "low level" techniques;
 * namely, wait, notify and synchronized. This isn't the best way to implement a
 * Producer-Consumer pattern in Java
 * (see tutorial 7 use of {@link java.util.concurrent.BlockingQueue} for
 * the best way); but this tutorial will help you to understand how to use wait
 * and notify.
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
public class App {

    public static void main(String[] args) throws InterruptedException {
        final Processor processor = new Processor();

        Thread t1 = new Thread( () ->{
                try {
                    processor.produce();
                } catch (InterruptedException ignored) {}});

        Thread t2 = new Thread( ()-> {
                try {
                    processor.consume();
                } catch (InterruptedException ignored) {}});


        t2.start();
        t1.start();
//        t1.join();
//        t2.join();

        // Pause for 30 seconds and force quitting the app (because we're
        // looping infinitely)
        Thread.sleep(10000);
        System.exit(0);
    }
}