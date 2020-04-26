package org.example.j9cookbook;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Example22_11 {
    public static void main(String[] args) {
        new ReadersWriterDemo().demo();
    }
}
class ReadersWriterDemo {
    private static final int NUM_READER_THREADS = 3;
    public static void main(String[] args) {

    }

    /** Set this to true to end the program */ private volatile boolean done = false;
    /** The data being protected. */ private  List<String> theData;
    /** The read lock / write lock combination */ private ReadWriteLock lock = new ReentrantReadWriteLock();
    /** * Constructor: set up some quasi-random initial data */
    public ReadersWriterDemo() {
         theData = new ArrayList<>();
        theData.add("Disagree");
        theData.add("No opinion");

    }
        public void demo() {
            // Start two reader threads
            for (int i = 0; i < NUM_READER_THREADS; i++) {
                new Thread(()-> {
                        while (!done) {
                            lock.readLock().lock();
                            System.out.println("++[" +Thread.currentThread()+"] Taking read lock");
                            try {
                                System.out.println(" * * * * * * * * * * * * * *");
                                theData.forEach(p -> System.out.println("[" +Thread.currentThread()+"] -Read: "+ p));
                            } finally { // Unlock in "finally" to be sure it gets done.
                                  lock.readLock().unlock();
                            }
                        try { Thread.sleep(350);
                        } catch (InterruptedException ex) {}

                    }
            } ).start();

            }
            // Start one writer thread to simulate occasional voting
            new Thread(()-> {
                char a = 'a';
                    while (!done) {
                        lock.writeLock().lock();
                        try {
                           // System.out.println(" ---- Inserted sth "+a);
                            theData.add(String.valueOf(a++));
                         } finally {
                           // System.out.println(" ---- Released write lock");
                            lock.writeLock().unlock();
                        }
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException ex) { // nothing to do
                    }
                }
                } ).start();
        // In the main thread, wait a while then terminate the run.
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {}
            finally {
            done = true;
            }
    }

}