package org.example.nitobook;
import java.util.*;
public class Thread1 {
    public static void main(String[] args) {
        System.out.println(" * Main ["+ Thread.currentThread().getId()+"]");
        Worker2 t2 = new Worker2();
       // t2.run();
        Thread t1= new Thread(new Worker1());
        t1.start();
        Thread t3 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        ToDo.work("t3.Todo.work", 2,4);
                    }
                }
        );
        t3.start();
        System.out.println(" * Main-4 ["+ Thread.currentThread().getId()+"]");
        Thread t4 = new Thread(
                ()-> ToDo.work("t4.Todo.work", 2,4)
        );
        t4.start();
        System.out.println(" * Main-5 ["+ Thread.currentThread().getId()+"]");
        ToDo.work("Todo.work", 5,6);
    }
}

class Worker1 implements Runnable{
    @Override
    public void run() {
        ToDo.work("Run-Worker1", 2,4);
    }
}

class Worker2 extends Thread {
    public Worker2() {
    }
    @Override
    public void run() {
        ToDo.work("Run-Worker2", 2,4);
    }
}

class ToDo {
private static Random rand = new Random();
public static void work(String name, int a, int b){
    print("started " + name + " - ");
    for (int i=0, n= rand.nextInt(b-a) +a ; i< n;++i){
         print(" working ... i="+i+",n="+n);
        work();
    }
    System.out.println("terminated "+name + " - ");
}
public static void work(){
    double y;
    for (int i =0; i<1000000L; ++i) y = Math.cos(Math.sqrt(rand.nextDouble()));

}

private static void print(String text){
    long id = Thread.currentThread().getId();
    System.out.println("["+id+"] "+ text);
}
}