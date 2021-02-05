package org.example.webs.jenkov.concurrency;

public class Ex2 {

    static class Counter {
        protected long count = 0;
        public void add(long value){
            this.count = this.count + value;
        }
    }
    static class TwoSums {
        private int sum1 = 0;
        private int sum2 = 0;
        public void add(int val1, int val2){
            synchronized(this){
                this.sum1 += val1;
                this.sum2 += val2;
            }
        }
    }
    static class TwoSums2 {
        private int sum1 = 0;
        private int sum2 = 0;
        private Integer sum1Lock = Integer.valueOf(1);
        private Integer sum2Lock = Integer.valueOf(2);
        public void add(int val1, int val2){
            synchronized(this.sum1Lock){
                this.sum1 += val1;
            }
            synchronized(this.sum2Lock){
                this.sum2 += val2;
            }
        }
    }
    public static void main(String[] args) {
        Counter c1 = new Counter();

        Thread t1 =  new Thread(" t1 " ){
            public void run(){
                System.out.println("Thread: " + getName() + " running, "+getId());
                try {
                    Thread.sleep(500l);
                    System.out.println("Thread: " + getName() + "Current count " +  c1.count+", adding "+5);
                    c1.add(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t2 =  new Thread(" t2 " ){
            public void run(){
                System.out.println("Thread: " + getName() + " running, "+getId());
                try {
                    Thread.sleep(500l);
                    System.out.println("Thread: " + getName() + "Current count " +  c1.count+", adding "+4);
                    c1.add(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();
        t2.start();
        System.out.println("Main thread adding "+ 3);
        c1.add(3);
        try {
            Thread.sleep(600l);
            System.out.println("Main thread val "+ c1.count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
