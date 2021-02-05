package org.example.webs.jenkov.concurrency;

public class Ex1 {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        System.out.println("After start 1!, Thread: "+Thread.currentThread().getId());

        Thread thread2 = new Thread(){
            public void run(){
                System.out.println("Thread Running, Thread: "+Thread.currentThread().getId());
            }
        };
        thread2.start();
        System.out.println("After start 2!, Thread: "+Thread.currentThread().getId());

        Thread thread3 = new Thread(() -> System.out.println("Thread Running, Thread: "+Thread.currentThread().getId()));
        thread3.start();
        System.out.println("After start 3!, Thread: "+Thread.currentThread().getId());


        Runnable runnable1 = new MyRunnable();
        runnable1.run(); //first run, after continue with main
        System.out.println("After runnable 1!, Thread: "+Thread.currentThread().getId());

        Runnable runnable2 =
                () -> { System.out.println("Lambda Runnable running, Thread: "+Thread.currentThread().getId()); };
        runnable2.run();//first run, after continue with main
        System.out.println("After runnable 2!, Thread: "+Thread.currentThread().getId());

        Runnable runnable3 =   () -> { System.out.println("(2)Lambda Runnable running, Thread: "+Thread.currentThread().getId()); };
        Thread thread4 = new Thread(runnable3);
        thread4.start(); // continue with main, after inside runnable3
        System.out.println("After runnable 3!, Thread: "+Thread.currentThread().getId());

        Runnable runnable4 =   () -> { System.out.println("(3)Lambda Runnable running, Thread: "+Thread.currentThread().getId()); };
        Thread thread5 = new Thread(runnable4);
        thread5.run(); // common pitfall, // first run, after continue with main
        System.out.println("After runnable 4!, Thread: "+Thread.currentThread().getId());

    }
    static class MyThread extends Thread {
        public void run(){
            System.out.println("MyThread running, Thread: "+Thread.currentThread().getId());
        }
    }
    static class MyRunnable implements Runnable {
        public void run(){
            System.out.println("MyRunnable running, Thread: "+Thread.currentThread().getId());
            System.out.println("MyRunnable running, Thread: "+Thread.currentThread().getId());
        }
        public static void main(String[] args){
            System.out.println(Thread.currentThread().getName());
            for(int i=0; i<10; i++){
                new Thread("" + i){
                    public void run(){
                        System.out.println("Thread: " + getName() + " running, "+getId());
                    }
                }.start();
            }
        }
    }
    static public class MyStoppableRunnable implements Runnable {

        private boolean doStop = false;

        public synchronized void doStop() {
            this.doStop = true;
        }

        private synchronized boolean keepRunning() {
            return this.doStop == false;
        }

        @Override
        public void run() {
            while(keepRunning()) {
                // keep doing what this thread should do.
                System.out.println("Running, "+Thread.currentThread().getId());
                try {
                    Thread.sleep(3L * 1000L);
                    System.out.println("keeping running ? "+keepRunning());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
        public static void main(String[] args) {
            MyStoppableRunnable  myRunnable = new MyStoppableRunnable();
            Thread thread = new Thread(myRunnable);
          //  thread.setDaemon(true); // A
            thread.start();
            try {
                Thread.sleep(10L * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Stopping on main, "+Thread.currentThread().getId());
         //   myRunnable.doStop(); // B

            try {
                thread.join(); // C, hehe, in this case MyStoppableRunnable never stops
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
