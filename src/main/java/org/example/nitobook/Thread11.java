package org.example.nitobook;

public class Thread11 {
    // wait: current thread is blocket until another thread performs a notify/notifyAll
    // notify: 'wakes'a waiting thread
    // notifyAll: awakes all threads waiting for the current object


    // There are two types of thread synchronization: mutual exclusive and inter-thread communication.
    // -- Mutual Exclusive: Synchronized method, Synchronized block, static synchronization.

    // // -- Synchronized method is used to lock an object for any shared resource.
    // // -- When a thread invokes a synchronized method, it automatically acquires the lock for that object and releases it when the thread completes its task.

    // // ++ Synchronized block can be used to perform synchronization on any specific resource of the method.
    // // ++ Suppose you have 50 lines of code in your method, but you want to synchronize only 5 lines, you can use synchronized block.
    // // ++ If you put all the codes of the method in the synchronized block, it will work same as the synchronized method.
    // // ++ ++ Synchronized block is used to lock an object for any shared resource.
    // // ++ ++ Scope of synchronized block is smaller than the method.

    // // ** If you make any static method as synchronized, the lock will be on the class not on object.

    // Cooperation (Inter-thread communication in java)

    public static void main(String[] args) {
        Shared shared = new Shared();
        Producer prod = new Producer(shared);
        Consumer cons = new Consumer(shared);
        prod.start();
        cons.start();

    }
}
class Shared {
    private char value;
    private volatile boolean writeable = true;

    public synchronized void setValue(char value){
        while (!writeable) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("------ Writing enabled, setValue, writeable: "+writeable);
        this.value = value;
        writeable = false;
        notify();
    }
    public synchronized char getValue(){
        while (writeable){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(">>>> Reading enabled, getValue, writeable: "+writeable);
        writeable = true;
        notify();
        return value;
    }
}
class Consumer extends Thread {
    private final Shared shared;
    public Consumer(Shared shared){
        this.shared = shared;
    }
    @Override
    public void run() {
        char ch;
        System.out.println("Consumer starts running ! ");
        do {
            ch = shared.getValue();
            System.out.println(ch);
        } while (ch !='Z');
        System.out.println(" ... ending Consumer run ... ");
    }
}
class Producer extends Thread {
    private final Shared shared;
    public Producer(Shared shared){
        this.shared = shared;
    }
    @Override
    public void run() {
        System.out.println("Producer starts running ! ");
        for (char ch = 'A';ch<='Z';++ch){
            shared.setValue(ch);
        }
        System.out.println(" \n ... ending Producer run ... ");
    }
}