package org.example.ud_javamultithread.D_LockObjects;

public class App {
    public static void main(String[] args) {
        System.out.println("Synchronized Objects: ");
        Worker worker = new Worker();
        worker.main();
        System.out.println("Synchronized Methods: ");
        WorkerMethodsSynchronized worker2 = new WorkerMethodsSynchronized();
        worker2.main();
    }
}
