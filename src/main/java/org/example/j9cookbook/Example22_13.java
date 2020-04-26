package org.example.j9cookbook;

import java.io.IOException;
import java.util.LinkedList;

public class Example22_13 {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Start producers and consumers
        int numProducers = 2;
        int numConsumers = 9;
        ProdCons2 pc = new ProdCons2(numProducers, numConsumers);

        // Let it run for, say, 10 seconds
        Thread.sleep(10*500);

        // End of simulation - shut down gracefully
        synchronized(pc.list) {
            System.out.println("1-Shutdown !!!");
                pc.done = true;
            pc.list.notifyAll();
            System.out.println("2-Shutdown !!!");
        }
    }
}
 class ProdCons2 {

    /**
     * Throughout the code, this is the object we synchronize on so this * is also the object we wait() and notifyAll() on.
     */
    protected LinkedList<Object> list = new LinkedList<>();
    protected int MAX = 10;
    protected Boolean done = false; // Also protected by lock on list.

    /**
     * Inner class representing the Producer side
     */
    class Producer extends Thread {
        public void run() {
            while (true) {
                Object justProduced = getRequestFromNetwork(); // Get request from the network - outside the synch section.

                // We're simulating this actually reading from a client, and it
                // might have to wait for hours if the client is having coffee.
                synchronized (list) {
                    while (list.size() == MAX) { // queue "full"
                        try {
                            System.out.println("Producer WAITING, done: "+done + ", size: "+list.size());
                            list.wait(); // Limit the size
                        } catch (InterruptedException ex) {
                            System.out.println("Producer INTERRUPTED");
                        }
                    }
                    list.addFirst(justProduced);
                    list.notifyAll(); // must own the lock
                  //  System.out.println("Produced 1; List size now " + list.size());
                    if (done){
                        System.out.println("Producer done!");
                        break; // yield(); // Useful for green threads & demo programs.
                    }
                }
            }
        }

        Object getRequestFromNetwork() {
            // Simulation of reading from client
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Producer Read INTERRUPTED");
            }
            return (new Object());
        }

    }

     /** Inner class representing the Consumer side */
     class Consumer extends Thread {
         public void run() {
             while (true) {
                 Object obj = null;
                 synchronized(list) {
                     while (list.size() == 0) {
                         try {
                             System.out.println("CONSUMER WAITING, done: "+done+ ", size: "+list.size());
                             list.wait(); // must own the lock
                         } catch (InterruptedException ex) {
                             System.out.println("CONSUMER INTERRUPTED");
                         }
                     }
                     obj = list.removeLast();
                     list.notifyAll();
                     int len = list.size();
                    // System.out.println("List size now " + len);
                     if (done){
                         System.out.println("Consumer done!");
                         break;
                     }
                 }
                 process(obj);// Outside synch section (could take time)
                 //yield(); DITTO
            }
         }

         void process(Object obj) {
             try {
                 Thread.sleep(1000); // Simulate time passing
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
            // System.out.println("Consuming object " + obj);
         }

     }

     ProdCons2(int nP, int nC) {
         for (int i=0; i<nP; i++) new Producer().start();
         for (int i=0; i<nC; i++) new Consumer().start();
     }



 }