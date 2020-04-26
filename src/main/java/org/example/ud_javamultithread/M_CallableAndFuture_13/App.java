package org.example.ud_javamultithread.M_CallableAndFuture_13;


import java.util.Random;
import java.util.concurrent.*;

/**
 * {@link java.util.concurrent.Callable} and
 * {@link java.util.concurrent.Future}
 * in Java to get results from your threads and to allow
 * your threads to throw exceptions. Plus, Future allows you to control your
 * threads, checking to see if they’re running or not, waiting for results and
 * even interrupting them or de-scheduling them.
 * <p>
 * {@link java.lang.Runnable}
 * is the default abstraction for creating a task in Java. It has a single
 * method {@link Runnable#run()}
 * that accepts no arguments and returns no value, nor it can throw
 * any checked exception. To overcome these limitations, Java 5 introduced a new
 * task abstraction through {@link java.util.concurrent.Callable} interface.
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
        ExecutorService executor = Executors.newCachedThreadPool();

        //anonymous call of Callable
        Future<Integer> future = executor.submit(new Callable<Integer>() {

            @Override
            //return value is Integer
            public Integer call() throws TimeoutException {
                Random random = new Random();
                int duration = random.nextInt(4000);
                if (duration > 2000) {
                    throw new TimeoutException ("(1)Sleeping for too long.");
                }

                System.out.println("(1)Starting ...");
                try {
                    Thread.sleep(duration);
                } catch (InterruptedException ignored) {}
                System.out.println("(1)RFinished.");
                return duration;
            }
        });

        Callable<?> callableObj =()->{
            //new Callable(Void) , when you don't want to return a value
            Random random = new Random();
            int duration = random.nextInt(4000);
            if (duration > 2000) {
                throw new TimeoutException ("!(2)!Sleeping for too long.");
            }

            System.out.println("(2)Starting ...");
            try {
                Thread.sleep(duration);
            } catch (InterruptedException ignored) {}
            System.out.println("(2)Finished.");
            return null;
        };

        Runnable r1 = ()->{
            //Runnable. How to get a result from this run? Not possible (instance variable).
            System.out.println("Starting running ");
            Random rand = new Random();
            int duration = rand.nextInt(100);
            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("End running ");
        };

     Future<?> cally=   executor.submit(callableObj);
        Future runny = executor.submit(r1);

        executor.shutdown();
//        executor.awaitTermination(1, TimeUnit.DAYS);
        try {
            //get returned value from call()
            System.out.println("Result is: " + future.get());
            System.out.println("!!!! "+cally.get());
            System.out.println("!!!! "+runny.get());

        } catch (InterruptedException ignored) {
        } catch (ExecutionException e) {
            TimeoutException ex = (TimeoutException) e.getCause();
            System.out.println("catching ExecutionException," +
                    " casting to TimeoutException ... "+ex.getMessage());
        }
    }

}