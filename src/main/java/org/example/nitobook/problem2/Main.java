package org.example.nitobook.problem2;

import java.math.BigDecimal;
import java.util.concurrent.CyclicBarrier;

public class Main {
   // private static final int N  = 100;
    public static void main(String[] args) {
        BigDecimal [] array = create(12);
       long start1= System.nanoTime();
       BigDecimal sumSqrts= sum1(array);
       long end1 = System.nanoTime();

       Solver solver= new Solver(array);
        long end2 = System.nanoTime();


        System.out.println(" !(1) res "+ sumSqrts.doubleValue()       + ", timeused: "+ (end1-start1));
        System.out.println(" !(2) res "+ solver.getRes().doubleValue()+ ", timeused: "+ (end2-end1));



    }
    private static BigDecimal[] create(int n){
        BigDecimal []res = new BigDecimal[n];
        for (int r=0; r<n; ++r){
            res[r]= Calc.random();
        }
        return res;
    }
    private static BigDecimal sum1(BigDecimal[] tal){
        BigDecimal res=BigDecimal.ZERO;
        for (int r=0; r<tal.length; ++r){
        //    System.out.println("#" + r +", res: "+res.doubleValue() + ", $"+tal[r].doubleValue() + ", sqrt="+Calc.sqrt(tal[r]).doubleValue());
            res = res.add(Calc.sqrt(tal[r]));
        }
        return res;
    }
}
class Solver {
    private final BigDecimal[]data;
    private final CyclicBarrier barrier;
    private BigDecimal res=BigDecimal.ZERO;

    public BigDecimal getRes() {
        return res;
    }

    public Solver(BigDecimal[] array) {
        this.data = array;
        barrier = new CyclicBarrier(array.length,
                new Runnable() {
                    @Override
                    public void run() {
                       // System.out.println("[" q q q+Thread.currentThread().getId()+"] --Solver - theory: all worker threads completed! ");
                        merge();
                    }
                });
        for (int r=0; r<array.length; ++r){
            new Thread(new WorkerProblem2(r)).start();
        }
       // System.out.println("["+Thread.currentThread().getId()+"] --Solver - before sync");
        synchronized ("abc"){
            try {
             //   System.out.println("["+Thread.currentThread().getId()+"] --Solver - waiting");
                "abc".wait();
            //    System.out.println("["+Thread.currentThread().getId()+"] --Solver - notified");
                // here we know all sqrt are done
                for (int r=0; r<array.length; ++r){
                  //  System.out.println("#" + r +", res: "+res.doubleValue() );
                    res = res.add(array[r]);
                }
             //   System.out.println(" ! ! Result is : "+res.doubleValue());
            } catch (InterruptedException e) {
                System.out.println("main thread interrupted");
            }
        }
    }

    public void merge(){
       // System.out.println("["+Thread.currentThread().getId()+"] merging / next: synch + notify");
        synchronized ("abc"){
            "abc".notify();
        }
    }
    class WorkerProblem2 implements Runnable {
        private final int row;
        private boolean done = false;
        public WorkerProblem2(int row) {
            this.row = row;
        }
        public boolean done(){
            return  done;
        }

        private void work(){
          //  System.out.println("["+Thread.currentThread().getId()+"] work() Handling row: "+row);
            data[row] = Calc.sqrt(data[row]);
            done = true;
        }
        @Override
        public void run() {
            while (!done){
                work();
                try {
                   // System.out.println("["+Thread.currentThread().getId()+"] Worker - barrier.await ...  ");
                    barrier.await();
                   // System.out.println("["+Thread.currentThread().getId()+"] ! ! Releasing barrier ... ");
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }

        }
    }
}
