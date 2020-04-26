package org.example.webs.geeksforgeeks.killthreads;

public class KillByNonVolatile {
// Java program to illustrate non-volatile boolean flag

    // static used here
    // because a non-static variable
    // cannot be referenced
    // from a static context

    // exit variable to stop both
    // the main and inside threads
    static boolean exit = false;

    public static void main(String[] args){
        System.out.println("started main thread..");

        // a thread inside main thread

        new Thread() {
            public void run() {
                System.out.println("started inside thread..");

                // inside thread caches the value of exit,
                // so changes made to exit are not visible here
                while (!exit) // will run infinitely
                {
                  //  System.out.println("exit: "+exit);
                }

                // this will not be printed.
                System.out.println("exiting inside thread..");
            }
        }.start();

        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e) {
            System.out.println("Caught :" + e);
        }

        // so that we can stop the threads
        exit = true;
        System.out.println("setting exit: "+exit);
        System.out.println("exiting main thread..");
    }
}
