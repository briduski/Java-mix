package org.example.j9cookbook;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Example22_16 {
}
class RecursiveTaskDemo extends RecursiveTask<Long> {
    private static final long serialVersionUID = 3742774374013520116L;

    //static final int N = 10000000; final static int THRESHOLD = 500;
    static final int N = 10; final static int THRESHOLD = 2;
    String name;
    int[] data; int start, length;
        public static void main(String[] args) {
            int[] source = new int[N];
            loadData(source);
            RecursiveTaskDemo fb = new RecursiveTaskDemo(source, 0, source.length, "main");
            ForkJoinPool pool = new ForkJoinPool();
            long before = System.currentTimeMillis();
            pool.invoke(fb);
            long after = System.currentTimeMillis();
            long total = fb.getRawResult();
            long avg = total / N;
            System.out.println("Average: " + avg);
            System.out.println("Time :" + (after - before) + " mSec");
    }
    static void loadData(int[] data) {
        Random r = new Random();
        for (int i = 0; i < data.length; i++) {
            //data[i] = r.nextInt();
            data[i] =i;
        }
    }

    public RecursiveTaskDemo(int[] data, int start, int length, String name) {
            this.data = data;
            this.start = start;
            this.length = length;
            this.name = name;
        }

    @Override
    protected Long compute() {
        if (length <= THRESHOLD) {
            // Compute Directly
            long total = 0;
            for (int i = start; i < start + length; i++) {
                total += data[i];
                System.out.println(" <total>"+total+"</total> data[i]="+data[i] + " ["+Thread.currentThread().getId() +"]");
            }
            System.out.println(name + " -  - (1) " + ", total: "+total + " ["+Thread.currentThread().getId() +"]");
            return total;
        } else { // Divide and Conquer
             int split = length / 2;
            System.out.println(name + " _(2) ["+Thread.currentThread().getId() + "], start:" + start + ", split: "+split);
             RecursiveTaskDemo t1 = new RecursiveTaskDemo(data, start, split, "rita-sp-"+length);
             t1.fork(); // Arranges to asynchronously execute this task in the pool
             RecursiveTaskDemo t2 = new RecursiveTaskDemo(data, start + split, length - split, "rita-sp-"+length);
            System.out.println(name + " ..(3) ["+Thread.currentThread().getId() + "], start+split: "+(start+split) + ", length-split: "+(length-split));
             return t2.compute() + t1.join();
        }
    }
}