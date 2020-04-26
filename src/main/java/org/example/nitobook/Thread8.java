package org.example.nitobook;

public class Thread8 {

    private static double root = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread th1 =  new Thread(
                () -> {
                    synchronized (lock){
                        root = sqrt2();
                    }
                }
        );
        th1.start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (lock){
            System.out.println(root);
        }

    }
    private static double sqrt2(){
        double y =0;
        for (int i = 0; i< 1000000L; i++){
            y = Math.sqrt(2);
        }
        System.out.println(">> "+y);
        return y;

    }
}
