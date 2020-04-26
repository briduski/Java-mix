package org.example.ud_javamultithread.B_VolatileKeyword;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Processor pro = new Processor();
        pro.start();
        // Wait for the enter key
        System.out.println("Enter something to stop the thread,\nVolatile variable running will be forced to true :");
        new Scanner(System.in).nextLine();
        pro.shutdown();
        System.out.println("Bye!");
    }
}

class Processor extends Thread {
    private volatile  boolean running = true;
    public void run() {
        while (running) {
            System.out.println("Running ["+Thread.currentThread().getId()+"]");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void shutdown() {
        running = false;
    }
}
