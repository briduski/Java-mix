package org.example.webs.geeksforgeeks;

import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;

// https://www.geeksforgeeks.org/java-util-timertask-class-java/

public class Helper extends TimerTask {
    public static int i = 0;
    public void run() {
        System.out.println(Instant.ofEpochMilli(System.currentTimeMillis())+ " Timer ran " + ++i);
        if(i == 4) {
            synchronized(Test.obj) {
                System.out.println( " ... Finishing timerTask ... ");
                   Test.obj.notify();
            }
        }
    }
}
class Test {
    public static Test obj;
    public static void main(String[] args) throws InterruptedException {
        obj = new Test();

        // creating an instance of timer class
        Timer timer = new Timer();

        // creating an instance of task to be scheduled
        TimerTask task = new Helper();

        // scheduling the timer instance

        System.out.println(Instant.ofEpochMilli(System.currentTimeMillis())+ " Scheduling ");
        timer.schedule(task,10000, 1000);
        System.out.println(Instant.ofEpochMilli(System.currentTimeMillis())+ " Scheduled ");

        // fetching the scheduled execution time of
        // the most recent actual execution of the task
        System.out.println(Instant.ofEpochMilli(task.scheduledExecutionTime())+ " fetching execution time ");

        synchronized(obj) {
            //this thread waits until i reaches 4
            obj.wait();
        }

        System.out.println(Instant.ofEpochMilli(System.currentTimeMillis())+ " FINISHED TIMERTASK ");
        //canceling the task assigned
        System.out.println("Cancel the timer task: " + task.cancel());

        // at this point timer is still running
        // without any task assigned to it

        // canceling the timer instance created
        timer.cancel();
        System.out.println(Instant.ofEpochMilli(System.currentTimeMillis())+ " FINISHED TEST");
    }
}