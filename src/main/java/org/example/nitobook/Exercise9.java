package org.example.nitobook;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.example.nitobook.Exercise9.NUMBER_ITERATION;

public class Exercise9 {
    public static final int NUMBER_ITERATION = 30;

    public static void main(String[] args) {
        SharedEx9 shared2 = new SharedEx9();
        ProducerEx9 producer1 = new ProducerEx9(shared2);
        ConsumerEx9 consumer2 = new ConsumerEx9(shared2);

        producer1.start();
        try{
            Thread.sleep(10000);
        } catch (Exception e){}
        consumer2.start();
    }
}

class ProducerEx9 extends Thread{
    SharedEx9 shared2;
    public  ProducerEx9(SharedEx9 shared2){
        this.shared2 = shared2;
    }
    @Override
    public void run() {
        long test=0;
        for (long i =1; i<=NUMBER_ITERATION; i++){
            try {
               // System.out.println("Producer iteration #"+i);
                test+=i;
                shared2.insert(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //System.out.println("[P] counter "+shared2.getCounter());
        System.out.println("[P] test-sum "+test);
    }
}
class ConsumerEx9 extends Thread{
    SharedEx9 sharedEx9;

    public ConsumerEx9(SharedEx9 sharedEx9){
        this.sharedEx9 = sharedEx9;
    }
    @Override
    public void run() {
        while (sharedEx9.getCounter()>0) {
            try {
                sharedEx9.add();
                //System.out.println("-----Consumer Sleeping some time ... ");
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //System.out.println("[C] ++Counter "+sharedEx9.getCounter());
        System.out.println("[C] ---- Sum "+sharedEx9.getSum());

    }
}

class SharedEx9 {
   // private static Buffer<Long> buffer = new Buffer<>(Exercise5.NUMBER_ITERATION);
    private static Buffer<Long> buffer = new Buffer<>(5);
    private long counter=0;
    private long sum=0;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private volatile boolean ok = false;

    public Lock getLock(){
        return lock;
    }
    public long getCounter() {
        return counter;
    }

    public long getSum() {
        return sum;
    }

    public void insert(Long value) throws Exception {
        // buffer is not full counts the counter up by one, and inserts the value in the buffer
        lock.lock();
        try {
            /*while (ok){
                condition.await();
            }*/
            if (!buffer.full()){
                counter++;
                buffer.insert(value);
            }else {
                System.out.println("Buffer full ... ");
            }
            ok = true;
        } finally {
            condition.signal();
            lock.unlock();
        }
    }

    public  void add() throws Exception {
        // that in the case where the buffer is not empty,
        // remove a number from the buffer and adds that number to the variable sum
        lock.lock();
        try {
        /*    while (!ok){
                condition.await();
            }*/
            if (!buffer.empty()){
                Long removedValue = buffer.remove();
                sum+=removedValue;
                counter--;
            }
            ok = false;
        } finally {
            condition.signal();
           lock.unlock();
        }

    }
}