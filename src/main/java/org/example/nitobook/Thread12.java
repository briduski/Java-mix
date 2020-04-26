package org.example.nitobook;

import java.time.Instant;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Thread12 {
    // Timer = encapsulation of a thread
    // TimerTask    defines a task that can be scheduled to run 1+ times.
    //      cancel, run, scheduledExecutionTime (Returns the scheduled execution time of the most recent actual execution of this task)
    // Time ____ start, terminate
    public static void main(String[] args) {
        TimerTask task1 = new MyTask1();
        TimerTask task2 = new MyTask2();

        // v1(task);
        // v2(task);
        v3(task2);
    }
    public static void v3 (TimerTask task){
        Timer timer = new Timer(true); // daemon thread, finish when main thread finishes
        timer.schedule(task,2000, 500);
        System.out.println("Now it is " + new Date() + ", " + Instant.ofEpochMilli(task.scheduledExecutionTime())+ " fetching execution time ");
        for (int i=0; i<40;i++ ){
            System.out.println("- Hello world " + i);
            delay(300);
        }
        System.out.println("Bye!");
    }
    public static void v2 (TimerTask task){
        Timer timer = new Timer();
        timer.schedule(task,2000, 1000);             // for v2()
        System.out.println("Now it is " + new Date() + ", " + Instant.ofEpochMilli(task.scheduledExecutionTime())+ " fetching execution time ");
        for (int i=0; i<100;i++ ){
            System.out.println("- Hello world " + i);
            delay(50);
        }
        timer.cancel();
    }

    public static void v1(TimerTask task){
        Timer tmer = new Timer();
        tmer.schedule(task,2000);                   // for v1()
        System.out.println("Now it is " + new Date() + ", " + Instant.ofEpochMilli(task.scheduledExecutionTime())+ " fetching execution time ");
        for (; ; ){
            System.out.println("Hello world");
            delay(500);
        }
    }
    private static void delay(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class MyTask1 extends TimerTask {
    @Override
    public void run() {
        System.out.println("Alarm: the matchine boils ... " + new Date());
        System.exit(0);    // uncomment this if used in v1()
    }
}
class MyTask2 extends TimerTask {
    @Override
    public void run() {
        System.out.println("Alarm: the matchine boils ... " + new Date());
    }
}