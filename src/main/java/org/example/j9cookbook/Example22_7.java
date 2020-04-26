package org.example.j9cookbook;

public class Example22_7 {
    public static void main(String[] args) throws InterruptedException {
        StopBoolean stopBoolean1 = new StopBoolean();
        stopBoolean1.start();
        StopBoolean stopBoolean2 = new StopBoolean();
        stopBoolean2.start();

        Thread.sleep(3000);

        stopBoolean1.shutdown();
        stopBoolean2.shutdown();
    }
}
class StopBoolean extends Thread {
    // Volatile ensures changes visible to other threads
    protected volatile boolean done = false;

    public void run() {
        while (!done){
            System.out.println( "StopBoolean app running! ["+Thread.currentThread().getId()+"]");
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println( "StopBoolean app Stopped! ["+Thread.currentThread().getId()+"]");

    }
    public void shutdown(){
        done = true;
    }
}