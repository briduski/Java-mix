package org.example.nitobook;

import java.util.concurrent.CyclicBarrier;

public class Thread17 {
private static final int N =5; // matrix size (5x5)
/*  A CyclicBarrier is a synchronization object which can be used to ensure that multiple threads are waiting on each other at a certain barrier point.
   The object is cyclic, since it can be recycled after having released waiting threads. The synchronization mechanism is useful if a recycled after having released waiting threads.
   The synchronization mechanism is useful if a program that starts a certain number of threads which  must wait for each other.
   .. as multiple threads are performing work on a common problem, and that task is first solved when all the threads have completed their work.. */
    public static void main(String[] args) {
        float[][] matrix = create();
       // print(matrix);
        System.out.println();
        Solver solver = new Solver(matrix);
      //  System.out.println();
       // print(matrix);
        System.out.println("done");
    }
    private static float [][]create(){
        float[][]matrix = new float[N][N];
        for (int r=0, n=0; r<matrix.length; ++r){
            for (int c=0; c<matrix[r].length; ++c){
                matrix[r][c]= ++n;
            }
        }
        return matrix;
    }
    private static void print(float[][] matrix){
        for (int r=0; r<matrix.length; ++r){
            for (int c=0; c<matrix[r].length; ++c){
                System.out.printf("%10.4f", matrix[r][c]);
                System.out.println();
            }
        }
    }
}
class Solver {
    private final float[][]data;
    private final CyclicBarrier barrier;
    public Solver(float[][] matrix) {
        this.data = matrix;
        barrier = new CyclicBarrier(matrix.length,
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("["+Thread.currentThread().getId()+"] --Solver - theory: all worker threads completed! ");
                        merge();
                    }
                });
        for (int r=0; r<matrix.length; ++r){
            new Thread(new Worker17(r)).start();
        }
        System.out.println("["+Thread.currentThread().getId()+"] --Solver - before sync");
        synchronized ("abc"){
            try {
                System.out.println("["+Thread.currentThread().getId()+"] --Solver - waiting");
                "abc".wait();
                System.out.println("["+Thread.currentThread().getId()+"] --Solver - notified");
            } catch (InterruptedException e) {
                System.out.println("main thread interrupted");
            }
        }
    }

    public void merge(){
        System.out.println("["+Thread.currentThread().getId()+"] merging / next: synch + notify");
        synchronized ("abc"){
            "abc".notify();
        }
    }
    class Worker17 implements Runnable {
        private final int row;
        private boolean done = false;
        public Worker17(int row) {
            this.row = row;
        }
        public boolean done(){
            return  done;
        }

        private void work(){
            System.out.println("["+Thread.currentThread().getId()+"] work() Handling row: "+row);
            for (int i =0; i< data[row].length; ++i){
                data[row][i]= (float)Math.sqrt(data[row][i]);
            }
            done = true;
        }
        @Override
        public void run() {
            while (!done){
                work();
                try {
                    System.out.println("["+Thread.currentThread().getId()+"] Worker - barrier.await ...  ");
                    barrier.await();
                    System.out.println("["+Thread.currentThread().getId()+"] ! ! Releasing barrier ... ");
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }

        }
    }
}
