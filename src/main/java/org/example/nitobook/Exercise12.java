package org.example.nitobook;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Exercise12 {
private static int M =1200;
private static int N =3000;

    public static void main(String[] args) {
        // 1000 X 2000 - 2000 X 1000   M =1000, N = 2000
        Matrix A = new Matrix(M, N);
        Matrix B = new Matrix(N , M);
        initMatrix(A);
        initMatrix(B);
        System.out.println("Starting Exercise 12. Multiply matrices: ("+ M + ","+ N+") X ("+N+","+M+")");
        ExecutorService executorService =  Executors.newFixedThreadPool(3);
        final ReentrantLock lock = new ReentrantLock();
        executorService.execute(new Task1Ex12(A,B, lock));

        executorService.execute(new Task2Ex12(A,B, lock));

        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdownNow();
    }
    private static void initMatrix(Matrix matrix){
        for(int i=0;i<matrix.getRows();++i){
            for (int j=0; j<matrix.getCols(); ++j){
               matrix.setValue(i,j, new Random().nextInt(9));
            }
        }
    }



}

class Task1Ex12 implements Runnable {
    private final  Matrix A;
    private final  Matrix B;
    private final Lock lock;

    public Task1Ex12(Matrix a, Matrix b, Lock lock) {
        this.lock = lock;
        A = a;
        B = b;
    }
    @Override
    public void run() {
        String id = "["+Thread.currentThread().getId() + "]" ;
        System.out.println(id + " started");
        lock.lock();
        long startThread26 = System.currentTimeMillis();
        Matrix C1 = Thread26.mult(A,B);
        long endThread26 = System.currentTimeMillis();
        System.out.print(id+" START: "+startThread26 + "\n__END: "+endThread26);
        System.out.println("   > Result: "+(endThread26 -startThread26));
        lock.unlock();
    }
}
class Task2Ex12 implements Runnable {
    private final  Matrix A;
    private final  Matrix B;
    private final  Matrix C2;
    private final Lock lock;

    public Task2Ex12(Matrix a, Matrix b, Lock lock) {
        this.lock = lock;
        A = a;
        B = b;
        C2 = new Matrix(A.getRows(), B.getCols());
    }
    @Override
    public void run() {
        String id = "[" + Thread.currentThread().getId() + "]";
        System.out.println(id + " started");
        lock.lock();
        long startThread27 = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new Thread27(A, B, C2));
        Thread27.multSubtask(A, B);
        long endThread27 = System.currentTimeMillis();
        System.out.print(id + " START: " + startThread27 + "\n__END: " + endThread27);
        System.out.println("   > Result: " + (endThread27 - startThread27));
        lock.unlock();
    }

}
