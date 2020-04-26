package org.example.nitobook;

import java.util.Random;
import java.util.concurrent.Phaser;

public class Thread20 {
    // Phaser similar to CyclicBarrier

    public static void main(String[] args) {
        // 5 threads  +  main ; threads synchronized by a Phaser with 4 phases
        Phaser phaser = new Phaser(1);
        Thread t1 = new Thread(new Worker20(phaser), "Thread-1");
        Thread t2 = new Thread(new Worker20(phaser), "Thread-2");
        Thread t3 = new Thread(new Worker20(phaser), "Thread-3");
        Thread t4 = new Thread(new Worker20(phaser), "Thread-4");
        Thread t5 = new Thread(new Worker20(phaser), "Thread-5");
        System.out.println(" \n ------ Start Phaser (A - start threads) ----- ");
        t1.start(); t2.start();t3.start();        t4.start();        t5.start();
        System.out.println(" \n ------ Start Phaser (B - work phaser) ----- ");
        work(phaser);
        work(phaser);
        work(phaser);
        work(phaser);
        phaser.arriveAndDeregister();
        System.out.println("Everybody has finished ... ");
        if (phaser.isTerminated()) System.out.println(" \n * * * * The Phaser object terminated * * * * ");
    }
    private static void work(Phaser phaser){
        int phase = phaser.getPhase();
        System.out.println(" < < < Phaser arrive And wait advance ... Phase: "+phase);
        phaser.arriveAndAwaitAdvance();
        System.out.print(" > > >  Phase - "+ phase + " is terminated > > >  ");
        System.out.print(" - ");
        System.out.println();
    }
}

class Worker20 implements Runnable {
    private static final Random rand = new Random();
    private Phaser  phaser;

    private double value = 0;
    public Worker20(Phaser phaser){
        this.phaser = phaser;
        this.phaser.register();
        System.out.println("New Thread registered: "+ Thread.currentThread().getName() + ", id: "+ Thread.currentThread().getName());
    }
    @Override
    public void run(){
        System.out.println(" ["+ Thread.currentThread().getName()+"] Starting run ...");
        todo(2);
      //  System.out.println(" ["+ Thread.currentThread().getName()+"] Finished todo 2");
        todo(3);
      //  System.out.println(" ["+ Thread.currentThread().getName()+"] Finished todo 3");
        todo(5);
      // System.out.println(" ["+ Thread.currentThread().getName()+"] Finished todo 5");
        result();
      //  System.out.println(" ["+ Thread.currentThread().getName()+"] Result() ");
        System.out.println("["+Thread.currentThread().getName()+"] phaser.arrive and de-register ... ");
        phaser.arriveAndDeregister();
    }
    private void todo (int t){

        System.out.println(Thread.currentThread().getName() + " - has reached the barrier and works in phase "+
                phaser.getPhase() + ", Value = "+value + ", todo(t="+t+"), phaser.getArrivedParties()" + phaser.getArrivedParties());
        phaser.arriveAndAwaitAdvance();
        System.out.println(Thread.currentThread().getName() + " - After arrive and wait in advance => phase "+
                phaser.getPhase() + ", Value = "+value + ", todo(t="+t+"), phaser.getArrivedParties()" + phaser.getArrivedParties());
        work(rand.nextInt(Integer.MAX_VALUE), t);
    }
    private void work(long n, int t){
        for (int i=0;i<n;++i){
            value = Math.sqrt(t);
        }
    }
    private void delay(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void result(){
        System.out.println(Thread.currentThread().getName() + " - has reached the barrier and works in phase "+
                phaser.getPhase() + ", Value = "+value + ", phaser.getArrivedParties()" + phaser.getArrivedParties());
        phaser.arriveAndAwaitAdvance();
    }
}