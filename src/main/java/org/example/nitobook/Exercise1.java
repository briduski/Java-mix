package org.example.nitobook;

import java.util.Random;

public class Exercise1 {

    public static void main(String[] args) {
        Thread th1 = new Thread(
                ()-> {
                    for (int i = 1; i<11;i++){
                        print( "#" +i + " " +   new Random().nextInt(99));
                        sleep();
                    }
                }
        );
        Thread th2 = new Thread(
                ()-> {
                    for (int i = 1; i<11;i++){
                        print( "#" +i + " " +  new Random().nextDouble());
                        sleep();
                    }
                }
        );
        th1.start();
        th2.start();
    }
    private static void sleep(){
        try{
            int  timeToSpleep = new Random().nextInt(999);
            Thread.sleep(timeToSpleep);
        }catch (Exception e){}
    }
    private static void print (String msg){
        System.out.println("["
                + Thread.currentThread().getId()
                + "] "
                + msg
        );
    }
}
