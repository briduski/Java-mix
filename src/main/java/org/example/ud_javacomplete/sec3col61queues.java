package org.example.ud_javacomplete;

import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Stream;

public class sec3col61queues {
    public static void main(String[] args) {
        LinkedBlockingQueue<Integer> q1 = new LinkedBlockingQueue<Integer>(2);
        Thread t1 = new Thread( () -> {
            //reader
            int count =0;
            while (count<4){
                try {
                    System.out.println("Read: " + q1.take() + ", count:"+count);
                    count++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(" - - - - - Finished reading");
        });
        Thread t2 = new Thread( () -> {
            //writer
            Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
            integerStream.forEach(x-> {
                    System.out.println("Inserting "+x);
                    try {
                       // q1.put(x);// blocking
                        q1.offer(x);// non-blocking
                        System.out.println("is it really inserted? " + q1.peek());
                       Thread.sleep(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

            });
            System.out.println(" + + + + + Finished writing");
        });
        t2.start();
        t1.start();
    }
}
 class App {

    public static void main(String[] args) {
        //  (head) <- oooooooooooooooooooooooo <- (tail) FIFO (first in, first out)

        Queue<Integer> q1 = new ArrayBlockingQueue<Integer>(3);

        // Throws NoSuchElement exception --- no items in queue yet
        // System.out.println("Head of queue is: " + q1.element());

        q1.add(10);
        q1.add(20);
        q1.add(30);

        System.out.println("Head of queue is: " + q1.element());

        try {
            q1.add(40);
        } catch (IllegalStateException e) {
            System.out.println("Tried to add too many items to the queue.");
        }

        for(Integer value: q1) {
            System.out.println("Queue value: " + value);
        }

        System.out.println("Removed from queue: " + q1.remove());
        System.out.println("Removed from queue: " + q1.remove());
        System.out.println("Removed from queue: " + q1.remove());

        try {
            System.out.println("Removed from queue: " + q1.remove());
        } catch (NoSuchElementException e) {
            System.out.println("Tried to remove too many items from queue");
        }

        ////////////////////////////////////////////////////////////////////

        Queue<Integer> q2 = new ArrayBlockingQueue<Integer>(2);

        System.out.println("Queue 2 peek: " + q2.peek());

        q2.offer(10);
        q2.offer(20);

        System.out.println("Queue 2 peek: " + q2.peek());

        if(q2.offer(30) == false) {
            System.out.println("Offer failed to add third item.");
        }

        for(Integer value: q2) {
            System.out.println("Queue 2 value: " + value);
        }

        System.out.println("Queue 2 poll: " + q2.poll());
        System.out.println("Queue 2 poll: " + q2.poll());
        System.out.println("Queue 2 poll: " + q2.poll());
    }

}