package org.example.webs.geeksforgeeks.killthreads;

// Java program to illustrate
// stopping a thread using boolean flag

public class KillByFlag implements Runnable {
    public static void main(String args[]) {
        // creating two objects t1 & t2 of MyThread
        KillByFlag t1 = new KillByFlag("First thread");
        KillByFlag t2 = new KillByFlag("Second thread");
        try {
            Thread.sleep(500);
            t1.stop(); // stopping thread t1
            t2.stop(); // stopping thread t2
            Thread.sleep(500);
        }
        catch (InterruptedException e) {
            System.out.println("Caught:" + e);
        }
        System.out.println("Exiting the main Thread");
    }
    // to stop the thread
    private boolean exit;
    private String name;
    Thread t;
    KillByFlag(String threadname) {
        name = threadname;
        t = new Thread(this, name);
        System.out.println("New thread: " + t);
        exit = false;
        t.start(); // Starting the thread
    }

    // execution of thread starts from run() method
    public void run() {
        int i = 0;
        while (!exit) {
            System.out.println(name + ": " + i);
            i++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Caught:" + e);
            }
        }
        System.out.println(name + " Stopped.");
    }

    // for stopping the thread
    public void stop() {
        exit = true;
    }
}
