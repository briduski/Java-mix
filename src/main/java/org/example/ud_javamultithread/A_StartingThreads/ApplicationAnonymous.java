package org.example.ud_javamultithread.A_StartingThreads;

public class ApplicationAnonymous {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("Hello: " + i + " Thread: " + Thread.currentThread().getId());
                   sleepy(100);
                }
            }
        });

        thread1.start();


        Thread  t2 = new Thread(
                () -> {
                    for (int i =0; i<5;++i) {
                        System.out.println("Hola: #"+i +", thread: "+Thread.currentThread().getId());
                        sleepy(50);
                    }
                }

        );
        t2.start();
    }

    public static void sleepy(long val){
        try {
            Thread.sleep(val);
        } catch (InterruptedException ignored) {}
    }
}
