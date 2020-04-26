package org.example.nitobook;

public class Thread2 {
// A deamon thread is intented as a helper thread to another thread,
// deamon thread automatically is terminated when the program's last none deamon thread terminates
// and when the primary thread is none-deamon thread, the deamons-thread terminates with the main program

    /* thread.getState()
    NEW, NEW, a a thread is created, but not yet started
- - RUNNING, RUNNING, the thread is performed on a processor
- - BLOCKED, BLOCKED, a thread is blocked and is waiting on a lock
- - WAITING, WAITING, a thread waits on a a notification from another thread notification from another thread
- - TIMED_WAITING, TIMED_WAITING, a thread waits on a notification because because of a timeout
- - TERMINATED, the thread is terminated
            */
    public static void main(String[] args) {
        System.out.println("available processors ... "+ Runtime.getRuntime().availableProcessors());
        Thread t1 = new Thread(new InfoThread(), "Thread number one");
        Thread t2 = new Thread(new InfoThread(), "Thread number two");
        System.out.println(" | Thread one state: " + t1.getState()+ " | Thread two state: " + t2.getState());
       // t1.setDaemon(true);
        t1.start();
        t2.setDaemon(true);
        try{
            Thread.sleep(2000);
        } catch( Exception e) {
        }
        t2.start();
        System.out.println(" | Thread one isAlive: " +t1.isAlive() + " | Thread two isAlive: " +t2.isAlive() + ". Finished main");
        System.out.println(" | Thread one state: " + t1.getState()+ " | Thread two state: " + t2.getState()+ ". Finished main");
    }
}

class InfoThread implements Runnable{
    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        System.out.println("[" + thread.getId()+ "] " + thread.getName() + " is started ");
        System.out.println("[" + thread.getId()+ "] " + (thread.isDaemon() ? "Deamon" : "None deamon" ));
        System.out.println("[" + thread.getId()+ "] " + thread.getState() );
        try{
            Thread.sleep(3000);
        } catch( Exception e) {
        }
        System.out.println("[" + thread.getId()+ "] " + thread.getName() + " is terminated ");
    }
}