package org.example.nitobook;

import java.util.concurrent.*;

public class Thread25 {
    public static void main(String[] args) {
        BlockingQueue<Character> queue = new ArrayBlockingQueue<>(29);
        CountDownLatch latch = new CountDownLatch(2);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new Producer25(queue, latch));

        executorService.execute(new Consumer25(queue, latch));

        try {
            Thread.sleep(1000);
           /* latch.wait();
            *  Causes the current thread to wait until another thread invokes the
            * {@link java.lang.Object#notify()} method or the
            * {@link java.lang.Object#notifyAll()} method for this object.
            * */
            latch.await();
            /*
            *  Causes the current thread to wait until the latch has counted down to
             * zero, unless the thread is {@linkplain Thread#interrupt interrupted}.
             * */
        } catch (Exception e) {

        }
        executorService.shutdownNow();
    }
}

class Consumer25 implements Runnable {
    private BlockingQueue<Character> queue;
    private CountDownLatch latch;

    public Consumer25(BlockingQueue<Character> queue, CountDownLatch latch) {
        this.queue = queue;
        this.latch = latch;
    }

    @Override
    public void run() {
        char ch='\0';
        System.out.println(" - Consumer - ");
        int i=0;
        while  (ch < 'Z') {
            i++;
            try {
                ch = queue.take();
                System.out.print(  ch);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.print(ch);
        }
        System.out.println();
        latch.countDown();
    }
}

class Producer25 implements Runnable {
    private BlockingQueue<Character> queue;
    private CountDownLatch latch;

    public Producer25(BlockingQueue<Character> queue, CountDownLatch latch) {
        this.queue = queue;
        this.latch = latch;
    }

    @Override
    public void run() {
    //
        //    System.out.println("Producer .. ");
        for (char ch = 'A'; ch <= 'Z'; ++ch) {
            try {
                queue.put(ch);
                //System.out.print(ch);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
      //  System.out.println(" .end :) ");
        latch.countDown();
    }
}