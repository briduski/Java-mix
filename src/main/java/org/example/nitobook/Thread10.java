package org.example.nitobook;

import java.util.ArrayList;

public class Thread10 {
    public static void main(String[] args) {
        ArrayList<AThread> threads = new ArrayList<>();
        for(int i =0; i<2; i++)threads.add(new AThread());
        for(AThread th: threads){
            th.start();
        }
        delay(1);
        for(AThread th: threads){
            th.stopThread();
        }
    }
    private static void delay (long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class AThread extends Thread{
    private volatile boolean stopped = false; // no reference copy of the var in cache
    @Override
    public void run() {
        while   (!stopped){
            System.out.println(
                    "["+Thread.currentThread().getId() + "] is running "
            );
        }
        System.out.println(
                "["+Thread.currentThread().getId() + "] downnnnn "
        );
    }
    public void stopThread(){
        stopped = true;
    }
}