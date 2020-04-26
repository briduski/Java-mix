package org.example.webs.geeksforgeeks;



import java.util.concurrent.CompletableFuture;

//https://www.geeksforgeeks.org/joining-threads-in-java/
public class ThreadJoining extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            try {
                Thread.sleep(500);
                System.out.println("Current Thread: "
                        + Thread.currentThread().getName()+", #"+i);
            }catch(Exception ex) {
                System.out.println("Exception has" +
                        " been caught" + ex);
            }
        }
    }
}
 class ThreadRunTime implements Runnable {
    @Override
    public void run() {

            try {
                Thread.sleep(500);
                System.out.println("ThreadRunTime- Current Thread: "
                        + Thread.currentThread().getName()+", #");

                throw new RuntimeException("This    is a test !!! ");
            }catch(Exception ex) {
                System.out.println("Exception has" +
                        " been caught" + ex);
                throw new RuntimeException("This is an error anyway !!! ");
            }
    }
}
class GFG {
    public static void main (String[] args) {
        // creating two threads
        ThreadJoining t1 = new ThreadJoining();
        ThreadJoining t2 = new ThreadJoining();
        ThreadJoining t3 = new ThreadJoining();
        ThreadRunTime t4 = new ThreadRunTime();
        // thread t1 starts
        t1.start();
        System.out.println("Starts t1");
        // starts second thread after when
        // first thread t1 has died.
        try {
            System.out.println("Current Thread: "
                    + Thread.currentThread().getName()+ ", NOT join ... ");
          //  t1.join();
        } catch(Exception ex) {
            System.out.println("Exception has " +
                    "been caught" + ex);
        }
        // t2 starts
        t2.start();
        System.out.println("Starts t2");
        // starts t3 after when thread t2 has died.
        try {
            System.out.println("Current Thread: "
                    + Thread.currentThread().getName()+ ", join ... ");
            t2.join();
        } catch(Exception ex) {
            System.out.println("Exception has been" +
                    " caught" + ex);
        }

        t3.start();
        System.out.println("Starts t3"  );
        CompletableFuture.supplyAsync(() -> {
            t4.run();
            return null;
        }).exceptionally(throwable -> {
            System.out.println("Number one");
            return throwable;
        }).thenApply(o -> {
            System.out.println("Bye Bye ");
            return null;
        });

    }
}