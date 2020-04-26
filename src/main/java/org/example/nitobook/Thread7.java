package org.example.nitobook;

public class Thread7 {
    private static double root = 0;
    public static void main(String[] args) {
    Thread th1 =  new Thread(
            () -> {
                root = sqrt2();
            }
    );
    th1.start();
    /* OPTION 1
        try {
            th1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println(root);

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
