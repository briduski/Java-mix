package org.example.webs.geeksforgeeks.killthreads;

public class KillByVolatile {
// Java program to illustrate volatile boolean flag


    // static used here because
    // a non-static variable cannot be referenced
    // from a static context

    // exit variable to stop both
    // the main and inside threads
    static volatile boolean exit = false;
    public static void main(String[] args) {

        System.out.println("started main thread..");

        // a thread inside main thread
        new Thread() {
            public void run() {

                // changes made to exit
                // in main thread are visible here
                System.out.println("started inside thread..");

                // will not run infinitely
                while (!exit) {
                }

                // this will be printed
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
        System.out.println("exiting main thread..");
    }
}
