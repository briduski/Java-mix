package org.example.nitobook;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.concurrent.*;

public class Thread28 {
    // CompletionService
    // detach the creation of async tasks
    // producer uses submit() to start a callable object in a thread
    // consumer calls a blocking take() waiting for the result

    private static int N =6;
    private static int M =3;
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(N);
        CompletionService<BigDecimal>completion = new ExecutorCompletionService<BigDecimal>(executor);
        Future<BigDecimal> []res = new Future[N];
        for(int i=0, n=M;i<N;++i,n+=M){
            System.out.println("Iteration #"+i+", n="+n);
            res[i]= completion.submit(new ECalculatorThread28(n));
            completion.take();
        }
        System.out.println("\n# # # Result length: "+res.length);
        for(int i =0; i<res.length; ++i){
            System.out.println(  res[i].get());
        }
        executor.shutdown();
    }
}

class ECalculatorThread28 implements Callable<BigDecimal> {
    private  int dec;

    public ECalculatorThread28(int dec) {
        this.dec = dec;
    }

    @Override
    public BigDecimal call() throws Exception {
       // System.out.println("Starting ECalculator dec: "+dec);
        MathContext mc = new MathContext(dec, RoundingMode.HALF_UP);
        BigDecimal y = BigDecimal.ZERO;
        int i =0;
        for (i =0;;i++){
            BigDecimal fac = BigDecimal.ONE.divide
                    (factorial(new BigDecimal(i)), mc);
           // System.out.println("Factorial "+fac.toString());
            BigDecimal z = y.add(fac, mc);
            //System.out.println("z: "+z+",y: "+y);
            if (z.compareTo(y) == 0) break;
            y = z;
            //System.out.println("y: "+y);
        }
        System.out.println(" Finished ECalculator, iterations #"+i);
        return  y;
    }
    private BigDecimal factorial (BigDecimal n){
        //System.out.println(">>Factorial: "+n);
        if (n.equals(BigDecimal.ONE) || n.equals(BigDecimal.ZERO)){
            return BigDecimal.ONE;
        } else {
            BigDecimal res = n.multiply(n.subtract(BigDecimal.ONE));
           // System.out.println(" >> "+res);
            return res;
        }
       /* return
                ?
                : ;*/
    }
}