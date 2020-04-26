package org.example.j9cookbook;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.stream.Collectors;

public class Example22_15 {
}
// square ()2 a bunch of numbers using RecursiveAction.
// We use RecursiveAction here b/c we don't need each
// compute() call to return its result; the work is
// accumulated in the "dest" array.

class RecursiveActionDemo extends RecursiveAction {

    private static final long serialVersionUID = 3742774374013520116L;
    static int[] raw = { 19, 3, 0, -1, 57, 24, 65, Integer.MAX_VALUE, 42, 0, 3, 5 };
    static int[] sorted = null;
    int[] source; int[] dest;
    int length; int start;
    final static int THRESHOLD = 4;

     public static void main(String[] args) {
         sorted = new int[raw.length];
         RecursiveActionDemo fb =
                 new RecursiveActionDemo(raw, 0, raw.length, sorted);
         ForkJoinPool pool = new ForkJoinPool();
         pool.invoke(fb);
         System.out.print('[');
         for (int i : sorted) {
             System.out.print(i + ",");
         }
         System.out.println(']');
     }

     public RecursiveActionDemo(int[] src, int start, int length, int[] dest) {
         this.source = src;
         this.start = start;
         this.length = length;
         this.dest = dest;
     }

     @Override
     protected void compute() {
         System.out.println (" / compute() ["+Thread.currentThread().getId() + "], length: "+length + ", Threshold: "+ THRESHOLD + " ");
         if (length <= THRESHOLD) {

             String result = Arrays.stream(dest)
                     .mapToObj(String::valueOf)
                     .collect(Collectors.joining(" - ", "[", "]"));
           //  System.out.println(" - (1) "+Thread.currentThread().getId());
             // Compute Directly
             for (int i = start; i < start + length; i++) {
                 dest[i] = source[i] * source[i];
             }

             System.out.println(" - (1) "+result + ", [" +Thread.currentThread().getId()+"]");
         } else { // Divide and Conquer

             String result = Arrays.stream(source)
                     .mapToObj(String::valueOf)
                     .collect(Collectors.joining(" - ", "[", "]"));
             System.out.println(" - (2) "+result + ", " +Thread.currentThread().getId());
             int split = length / 2;
              invokeAll(
                      new RecursiveActionDemo(source, start, split, dest),
                      new RecursiveActionDemo(source, start + split, length - split, dest));
         }
     }
 }