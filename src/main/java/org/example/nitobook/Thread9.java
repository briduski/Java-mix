package org.example.nitobook;

public class Thread9 {
    private Object lock1 = new Object();
    private Object lock2 = new Object();
    private static int count = 0;

    public static void main(String[] args) {
        final Thread9 instance = new Thread9();
        Thread t1 = new Thread(
                () ->{
                    while (true){
                        instance.work1();
                        instance.delay(50);
                    }
                }
        );
        t1.start();
        Thread t2 = new Thread(
                () ->{
                    while (true){
                        instance.work2();
                        instance.delay(50);
                    }
                }
        );
        t2.start();
    }
    public void work1(){
        synchronized (lock1){
            System.out.println(" -- 1 ");
            synchronized (lock2){
                System.out.println(" -- 2");
                System.out.println(
                        "["+Thread.currentThread().getId() + "] "
                                + (++count)
                );
            }
        }
    }
    public void work2(){
        synchronized (lock2){
            System.out.println(" -- 3");
            synchronized (lock1){
                System.out.println(" -- 4");
                System.out.println(
                    "["+Thread.currentThread().getId() + "] "
                    + (++count)
                );
            }
        }
    }
    private void delay (long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
