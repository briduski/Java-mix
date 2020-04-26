package org.example.j9cookbook;

public class Example22_3 {
    public static void main(String[] args) {
        new ThreadDemo3("Hi from Y", 3,5);
            new ThreadDemo3("Hi from X", 3, 10);
    }
}

class ThreadDemo3  {
    private int count;
    private Thread t;

    public ThreadDemo3(String msg,int c, int pri) {
        this.count = c;
        this.t = new Thread(() -> {
            while (count-- > 0) {
                System.out.println(msg+", id: "+Thread.currentThread().getId());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    return;
                }
            }
            System.out.println(msg + " all done");
        });
        t.setPriority(pri);
        t.start();
    }
}
