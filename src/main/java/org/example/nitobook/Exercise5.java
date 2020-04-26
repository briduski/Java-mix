package org.example.nitobook;

public class Exercise5 {
    public static final int NUMBER_ITERATION = 100000;
    public static void main(String[] args) {
        Shared2 shared2 = new Shared2();
        Producer2 producer1 = new Producer2(shared2);
        Consumer2 consumer2 = new Consumer2(shared2);
/*
        Thread threadProducers = new Thread(
                () -> {
                    long test=0;
                    for (long i =1; i<=Exercise5.NUMBER_ITERATION; i++){
                        try {
                            nw
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );*/
        producer1.start();
        try{
            Thread.sleep(1000);
        } catch (Exception e){}
        consumer2.start();
    }

}
class Producer2 extends Thread{
    Shared2 shared2;
    public  Producer2(Shared2 shared2){
        this.shared2 = shared2;
    }
    @Override
    public void run() {
        long test=0;
        for (long i =1; i<=Exercise5.NUMBER_ITERATION; i++){
            try {
                test+=i;
                shared2.insert(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("[P] Finalizing Producer");
        System.out.println("[P] counter "+shared2.getCounter());
        System.out.println("[P>>] test-sum "+test);
    }
}
class Consumer2 extends Thread{
    Shared2 shared2;
    public Consumer2(Shared2 shared2){
        this.shared2 = shared2;
    }
    @Override
    public void run() {

        long test=0;
        while (shared2.getCounter()>0) {
            try {
                test++;
                shared2.add();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("[C] Finalizing consumer ");
        System.out.println("[C] Counter "+shared2.getCounter());
        System.out.println("[C>>]----- Sum "+shared2.getSum());
        System.out.println("[C] test-cont "+test);

    }
}

class Shared2 {
    private static Buffer<Long> buffer = new Buffer<>(Exercise5.NUMBER_ITERATION);
    private long counter=0;
    private long sum=0;

    public long getCounter() {
        return counter;
    }

    public long getSum() {
        return sum;
    }

    public synchronized void insert(Long value) throws Exception {
         // buffer is not full counts the counter up by one, and inserts the value in the buffer
        if (!buffer.full()){
            counter++;
            buffer.insert(value);
        }
      }

    public synchronized void add() throws Exception {
        // that in the case where the buffer is not empty, remove a number from the buffer and adds that number to the variable sum
          if (!buffer.empty()){
            Long removedValue = buffer.remove();
            sum+=removedValue;
            counter--;
        }

    }
}