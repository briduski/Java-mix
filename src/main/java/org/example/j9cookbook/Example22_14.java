package org.example.j9cookbook;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Example22_14 {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Start producers and consumers
        int numProducers =4;
        int numConsumers= 3;
        ProdCons15 pc = new ProdCons15(numProducers, numConsumers);
        // Let the simulation run for, say, 10 seconds
        System.out.println("Sleeping ... ");
        Thread.sleep(10*300);
        // End of simulation - shut down gracefully
        System.out.println("done = true ");
        pc.done = true;
    }
}

class ProdCons15 {
    protected volatile boolean done = false;

    public ProdCons15(int nP, int nC) {
        BlockingQueue<Object> myQueue = new LinkedBlockingQueue<>();
        for (int i=0; i<nP; i++){
           Thread t =  new Thread(new Producer15(myQueue));
           t.start();
        }
        for (int i=0; i<nC; i++){
            Thread t = new Thread(new Consumer15(myQueue));
            t.start();
        }
    }

    class Producer15 implements Runnable {
            protected BlockingQueue<Object> queue;

        public Producer15(BlockingQueue<Object> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while(true){
                    Object justProducer = getRequestFromNetwork();
                    queue.put(justProducer);
                    //System.out.println("Produced 1 object; List size now "+queue.size());
                    if(done){
                        System.out.println("Produced DONE; List size now "+queue.size());
                        return;
                    }
                }
            } catch (Exception e){
                System.out.println("Producer Interrupted");
            }
            System.out.println("(P) END");
        }
        Object getRequestFromNetwork (){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new Object();
        }
    }

    class Consumer15 implements Runnable {
        protected BlockingQueue<Object> queue;

        public Consumer15(BlockingQueue<Object> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true){
                    Object obj = queue.take();
                    process(obj);
                    if(done){
                      //  System.out.println("Consumer DONE; List size now "+queue.size()
                       //         +", "+Thread.currentThread().getId()) ;
                        return;
                    }
                }
            } catch (Exception e){
                System.out.println("Consumer Interrupted");
            } finally {
                System.out.println("(C) END "+Thread.currentThread().getId());
            }
        }
        void process (Object obj){
           /* try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        //    System.out.println("Consumer object "+ obj+ ", "+Thread.currentThread().getId());
        }

    }
}