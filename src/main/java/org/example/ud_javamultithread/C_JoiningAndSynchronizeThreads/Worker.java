package org.example.ud_javamultithread.C_JoiningAndSynchronizeThreads;

public class Worker {
    private static int M =500;
    private int count = 0;
    private volatile int countVol = 0; // does not work, the problem is not caching
    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.doWork();
    }

    /**
     * Run code again by removing the synchronized and join keywords By removing
     * synchronized keyword count variable will vary that is it is not atomic in
     * this case due to the reason that count is shared between the threads or
     * without join keyword count will output wrong.
     */
    // every object in java has a transit lock = mutex,
    // if you a synch method into an object(worker),
    // you have to adquire the in transit lock before you can call it
    // only 1 thread at a time can adquire the int ransit lock
    // a second has to wait until the 1 thread release it.

    public  synchronized   void increment(String threadName) throws InterruptedException {
        count++;
        countVol++;
       // Thread.sleep(1);
       // System.out.println("Thread in Progress: " + threadName + " and count is: " + count);
    }

    public void doWork() {
        Thread thread1 = new Thread( () -> working());
        thread1.start();
        Thread thread2 = new Thread( () -> working());
        thread2.start();

        /**
         * Join Threads: Finish until thread finishes execution, then progress
         * the code Reminder: your method is also a thread so without joining
         * threads System.out.println("Count is: " + count); will work
         * immediately. Join does not terminate Thread 2, just progress of the
         * code stops until Threads terminate.
         */
        try {
            System.out.println("Waiting join 1");
            thread1.join();
            System.out.println("Waiting join 2");
            thread2.join();
        } catch (InterruptedException ignored) {}
        System.out.println("Count is: " + count+ ", volatile: "+countVol);
    }
    public  void working(){
        for (int i = 0; i < M; i++) {
            try {
                increment(""+Thread.currentThread().getId());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}