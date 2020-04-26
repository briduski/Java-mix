package org.example.j9cookbook;

import org.w3c.dom.ls.LSOutput;

import java.io.IOException;

public class Example22_9 {
    public static void main(String[] args) {
        Thread tjoning = new Thread(
                ()-> {
                    System.out.println("reading ");
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(" thread finished");
                });
        System.out.println("starting ");
        tjoning.start();
        System.out.println("joining ...");
        try {
            tjoning.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main finished ");
    }

}
