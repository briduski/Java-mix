package org.example.nitobook;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercise7 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        final CountDownLatch primeLatch = new CountDownLatch(100);
        final CountDownLatch sqrtLatch = new CountDownLatch(100);

        for (int i = 0; i < 100; i++) {
            PrimeCalculator4 primeCalculator4 = new PrimeCalculator4((i+1)*9900000, primeLatch);
            SqrtCalculator4 sqrtCalculator4 = new SqrtCalculator4(new BigDecimal((i + 1) * 9900000), 3, sqrtLatch);
            executor.submit(primeCalculator4);
            executor.submit(sqrtCalculator4);
            // System.out.println("  >  >  >  > Complete iteration #"+i);
        }
        System.out.println("Main waiting to finished all tasks");
        try {
            primeLatch.await();
            sqrtLatch.await();
            executor.shutdown();
            System.out.println("Finished!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SqrtCalculator4 implements Runnable{
    private BigDecimal x;
    private int dec;
    private double res;
    private final CountDownLatch done;

    public SqrtCalculator4(BigDecimal x , int dec,   CountDownLatch done ){
        this.done = done;
        if (x.signum()<0) {
            throw new IllegalArgumentException("The arg must be not negative");
        }
        this.x = x;
        this.dec = dec;
        this.res=0;
    }
    @Override
    public void run() {
       /* try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        res = Math.sqrt(x.doubleValue());
        done.countDown();
        System.out.println("["+ Thread.currentThread().getId() + "] Sqrt ("+x+") = "+ res + " / Count = "+ done.getCount());

    }

    public double getRes() {
        return res;
    }
}

class PrimeCalculator4 implements Runnable {
    private long n;
    private long result;
    private final CountDownLatch done;
    public PrimeCalculator4(long n,   CountDownLatch done ){
        this.done = done;
        this.n = n;
    }
    public void run(){
        result = n;
        if (result<= 2) result = 2;
        if (result % 2 == 0) ++result;
        while (!isPrime(result)) result+=2;
        done.countDown();
        System.out.println("["+ Thread.currentThread().getId() + "] PrimeCalculator: "+result + " / Count = "+ done.getCount());

    }

    public long getResult() {
        return result;
    }

    private static boolean isPrime(long t){
        if(t==1 || t==2 || t==3 || t== 5 || t==7) return true;
        if (t<11 || t%2 == 0) return false;
        for (long k = 3, m = (long)Math.sqrt(t)+1; k <= m;k+=2){
            // System.out.println("k: "+ k+ ", m: "+ ((long)Math.sqrt(t)+1));
            // System.out.println(t+"%"+k + "=" + (t%k));
            if(t%k == 0)return false;
        }
        return true;
    }
}