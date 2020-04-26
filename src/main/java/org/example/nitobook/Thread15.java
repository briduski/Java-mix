package org.example.nitobook;

import java.math.*;
import java.util.concurrent.*;

// Executor: alternative to starting a thread. Decoupling start from the execution
// call() (and run())), which is  the method  that is  performed when  a corresponding corresponding object ii started as a thread
public class Thread15 {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<BigDecimal>callE = new ECalculator(200);
        Callable<Long>callP = new PrimeCalculator2(1000000000L);
        Future<BigDecimal>eTask = executor.submit(callE);
        Future<Long>pTask = executor.submit(callP);

        try{
            while(!eTask.isDone() || !pTask.isDone()){
                System.out.println("... waiting ...");
                try {
                    Thread.sleep(100);
                }catch (Exception e ){}

            }
            System.out.println(eTask.get());
            System.out.println(pTask.get());
        } catch (Exception e){ e.printStackTrace();}
        executor.shutdownNow();
    }
}
class ECalculator implements Callable<BigDecimal>{
    private  int dec;

    public ECalculator(int dec) {
        this.dec = dec;
    }

    @Override
    public BigDecimal call() throws Exception {
        System.out.println("Starting ECalculator dec: "+dec);
        MathContext mc = new MathContext(dec, RoundingMode.HALF_UP);
        BigDecimal y = BigDecimal.ZERO;
        for (int i =0;;i++){
            BigDecimal fac = BigDecimal.ONE.divide
                            (factorial(new BigDecimal(i)), mc);
            System.out.println("Factorial "+fac.toString());
            BigDecimal z = y.add(fac, mc);
            if (z.compareTo(y) == 0) break;
            y = z;
            System.out.println("y: "+y);
        }
        System.out.println(" Finished ECalculator ");
        return  y;
    }
    private BigDecimal factorial (BigDecimal n){
            return n.equals(BigDecimal.ZERO)
                    ? BigDecimal.ONE
                    : n.multiply(n.subtract(BigDecimal.ONE));
    }
}

class PrimeCalculator2 implements Callable<Long> {
    private long n;

    public PrimeCalculator2(long n) {
        this.n = n;
    }
    public Long call(){
        //System.out.println("Starting PrimeCalculator2 n: "+n);
        long t = n;
        if (t<= 2) return new Long(2);
        if (t % 2 == 0) ++t;
        while (!isPrime(t)) t+=2;
        //System.out.println(" Finished PrimeCalculator2 t: "+t);
        return t;
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