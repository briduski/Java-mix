package org.example.j9cookbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Example22_13vRAR {
    public static void main(String[] args) throws InterruptedException, IOException {
        // Start producers and consumers
        int numProducers = 2;
        int numConsumers = 5;
        int MAX= 4;
        ProdCons22_13 pc = new ProdCons22_13(numProducers, numConsumers, MAX);

        // Let it run for, say, 10 seconds
        Thread.sleep(10*200);

        // End of simulation - shut down gracefully
        synchronized(pc.list) {
            pc.shutdown();
            //pc.list.notifyAll();
        }
    }
}
class ProdCons22_13 {
    protected LinkedList<Object> list = new LinkedList<>();
    private List<Producer22_13> prodList = new ArrayList<>();
    private List<Consumer22_13> consList = new ArrayList<>();
    protected final int MAX;
    protected boolean done = false; // Also protected by lock on list.
    private int nC;
    private int nP;
    ProdCons22_13(int nP, int nC, final int MAXY) {
        this.MAX = MAXY;
        this.nC = nC;
        this.nP = nP;
         for (int i=0; i<nP; i++) {
            Producer22_13 p =  new Producer22_13(list, MAX);
            prodList.add(p);
            p.start();
        };
        for (int i=0; i<nC; i++) {
            Consumer22_13 c =  new Consumer22_13(list, MAX);
            consList.add(c);
            c.start();
        }
    }
    protected void shutdown(){
        //prodList.forEach(p-> p.shutdown());
        System.out.println(" * * * * * * ");
        System.out.println(" * * * * * * ");
        System.out.println(" * * * * * * ");
        System.out.println(" * * * * * * ");
        prodList.forEach(Producer22_13::shutdown);
        consList.forEach(c-> {
            synchronized (list){
            c.shutdown();
            }
        });
        System.out.println(" * * * * * * ");
        System.out.println(" * * * * * * ");
        System.out.println(" * * * * * * ");
        System.out.println(" * * * * * * ");

    }
}
class Producer22_13 extends Thread {
    protected LinkedList<Object> list;
    protected boolean done = false; // Also protected by lock on list.
    protected final int MAX ;

    public Producer22_13(LinkedList<Object> list, int maxListSize) {
        this.list = list;
        this.MAX = maxListSize;
    }

    public void run(){
        while(true){
            Object justProducer = getRequestFromNetwork();
            synchronized (list){
                while (done == false && (list.size() == MAX)){
                    try {
                        list.wait();
                    } catch (InterruptedException e){
                     //   System.out.println("Producer INTERRUPTED, ["+Thread.currentThread().getId()+"]");
                    }
                }
                list.addFirst(justProducer);
                list.notifyAll();
                System.out.println("____(3) Producer List size now "+list.size() + ", ["+Thread.currentThread().getId()+"]");
                if (done){
                  //  System.out.println(" (P) bye!");
                    break;
                }
            }
        }
    }
    Object getRequestFromNetwork() {
        // Simulation of reading from client
       try {
         Thread.sleep(300);
        // simulate time passing during read
         } catch (InterruptedException ex) {
         System.out.println("Producer Read INTERRUPTED");
        }
        return(new Object());
    }
    protected void shutdown (){
       // System.out.println("closing producer, ["+Thread.currentThread().getId()+"]");
        synchronized (list){
            done = true;
        }
    }
}
class Consumer22_13 extends Thread {
    protected LinkedList<Object> list;
    protected boolean done=false; // Also protected by lock on list.

    public Consumer22_13(LinkedList<Object> list, int maxListSize) {
        this.list = list;
    }

    public void run() {
      //  System.out.println("-+-+-+(1)Starting running Consumer , ["+Thread.currentThread().getId()+"]");
        while (true) {
            Object obj = null;
            synchronized(list) {
                while (done == false && (list.size() == 0)){
                    try {
                        list.wait(); // must own the lock
                    } catch (InterruptedException ex) {
                     //   System.out.println("-+-+-+(3) CONSUMER INTERRUPTED, ["+Thread.currentThread().getId()+"]");
                    }
                }
               if (list.size()>0){
                    obj = list.removeLast();
                    System.out.println("(C) consuming object ["+Thread.currentThread().getId()+"], size now "+list.size());
                    list.notifyAll();
                  //  done = false;
                }
                if (done && (list.size() == 0 )){
                  //  System.out.println("bye!");
                    break;
                }
            }
            process(obj); //yield(); DITTO
        }
    }
    void process(Object obj) {
        try {
            Thread.sleep(500); // Simulate time passing
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
     //   System.out.println("(C) consuming object ["+Thread.currentThread().getId()+"], size now "+list.size());
    }
    protected void shutdown (){
        //System.out.println("-+-+-+ closing consumer, ["+Thread.currentThread().getId()+"]");
        synchronized (list){
            done = true;
        }
    }

}