package org.example.nitobook;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercise6 {
    public static void main(String[] args) {
        
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100; i++) {
             PrimeCalculator3 primeCalculator3 = new PrimeCalculator3((i+1)*9900000);
            SqrtCalculator sqrtCalculator = new SqrtCalculator(new BigDecimal((i + 1) * 9900000), 3);
            executor.submit(primeCalculator3);
             executor.submit(sqrtCalculator);
           // System.out.println("  >  >  >  > Complete iteration #"+i);
        }
        System.out.println("Main waiting to finished all tasks");
        try {
            Thread.currentThread().join(2000);
            executor.shutdown();
            System.out.println("Finished!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class SqrtCalculator implements Runnable{
    private BigDecimal x;
    private int dec;
    private double res;

    public SqrtCalculator(BigDecimal x , int dec){
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
        System.out.println("["+ Thread.currentThread().getId() + "] Sqrt ("+x+") = "+ res);
    }

    public double getRes() {
        return res;
    }
}
class ECalculator2 implements Runnable {
    private  int dec;
    private BigDecimal result;

    public ECalculator2(int dec) {
        this.dec = dec;
    }

    @Override
    public void run()  {
        MathContext mc = new MathContext(dec, RoundingMode.HALF_UP);
        BigDecimal y = BigDecimal.ZERO;
        for (int i =0;;i++){
            BigDecimal fac = BigDecimal.ONE.divide
                    (factorial(new BigDecimal(i)), mc);
            BigDecimal z = y.add(fac, mc);
            if (z.compareTo(y) == 0) break;
            result = z;
        }
        System.out.println("["+ Thread.currentThread().getId() + "] ECalculator2: "+result);
    }
    private BigDecimal factorial (BigDecimal n){
        System.out.println("This is the factorial .. "+n);
        if (n.equals(BigDecimal.ZERO)) {
             return BigDecimal.ONE;
        }else {
            System.out.println("factorial elsing ... ");
             BigDecimal a= n.subtract(BigDecimal.ONE);
            System.out.println("n.subtract(BigDecimal.ONE): "+a);
           return  n.multiply(a);
        }

        /*return n.equals(BigDecimal.ZERO)
                ? BigDecimal.ONE
                : n.multiply(n.subtract(BigDecimal.ONE));*/
    }
}

class PrimeCalculator3 implements Runnable {
    private long n;
    private long result;

    public PrimeCalculator3(long n) {
        this.n = n;
    }
    public void run(){
        result = n;
        if (result<= 2) result = 2;
        if (result % 2 == 0) ++result;
        while (!isPrime(result)) result+=2;
        System.out.println("["+ Thread.currentThread().getId() + "] PrimeCalculator: "+result);
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