package org.example.webs.geeksforgeeks.recursivetask;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class RecursiveTaskDemo {
    public static void main(String[] args) {
        ForkJoinPool fjp = new ForkJoinPool();
        double[] nums = new double[100];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (double)(((i % 2) == 0) ? i : -1);
        }
        Sum task = new Sum(nums, 0, nums.length);
        double summation = fjp.invoke(task);
        System.out.println("Summation " + summation);
    }
}

class Sum extends RecursiveTask<Double> {
    final int seqThreshold = 50;
    double[] data;
    int start, end;

    Sum(double[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Double compute() {
        double sum = 0;
        if ((end - start) < seqThreshold) {
            for (int i = start; i < end; i++){
                sum += data[i];
            }
        } else {
            int middle = (start + end) / 2;
            Sum subtaskA = new Sum(data, start, middle);
            Sum subtaskB = new Sum(data, middle, end);
            subtaskA.fork();
            subtaskB.fork();
            sum += subtaskA.join() + subtaskB.join();
        }

        System.out.println(">>>>["+Thread.currentThread().getId() +"], sum:"+sum);
        return sum;
    }
}
