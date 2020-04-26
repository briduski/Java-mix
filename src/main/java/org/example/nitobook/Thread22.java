package org.example.nitobook;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Thread22 {
    public static void main(String[] args) {
        Shared22 shared= new Shared22();

        new Producer22(shared).start();
        new Consumer22(shared).start();

    }
}

class Shared22 {
    private char value;
    private volatile boolean ok = false;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    public Lock getLock(){
        return lock;
    }
    public  char getValue() throws InterruptedException {
        lock.lock();
        try {
            while (!ok){
                condition.await();
            }
            System.out.println(">>>> getValue: "+ value);
            ok = false;
            return value;
        } finally {
          condition.signal();
          lock.unlock();
        }
    }
    public  void setValue(char value) throws InterruptedException {
        lock.lock();
        try {
            while (ok){
                condition.await();
            }
            this.value = value;
            System.out.println(">>>> value set: "+value);
            ok = true;
        } finally {
            condition.signal();
            lock.unlock();
        }
    }

}
class Consumer22 extends Thread {
    private final Shared22 shared;
    private final Lock lock;
    public Consumer22(Shared22 shared){
        this.shared = shared;
        lock = shared.getLock();
    }
    @Override
    public void run() {
        char ch = ' ';
        System.out.println("Consumer starts running ! ");
        do {
            try {
             //   lock.lock();
                ch = shared.getValue();
                System.out.println(ch);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //lock.unlock();
            }
        } while (ch !='Z');
        System.out.println(" ... ending Consumer run ... ");
    }
}
class Producer22 extends Thread {
    private final Shared22 shared;
    public Producer22(Shared22 shared){
        this.shared = shared;
    }
    @Override
    public void run() {
        System.out.println("Producer starts running ! ");
        for (char ch = 'A';ch<='Z';++ch){
            try {
                shared.setValue(ch);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(" \n ... ending Producer run ... ");
    }
}