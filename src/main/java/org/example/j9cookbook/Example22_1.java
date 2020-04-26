package org.example.j9cookbook;

public class Example22_1 {
    public static void main(String[] args) {
        new ThreadDemo1("fisrt thread", 3).start();
        new ThreadDemo1("second thread", 3).start();
    }
}
class ThreadDemo1 extends Thread {
     private int count;
     private String msg;

    public ThreadDemo1(String msg,int count) {
        this.count = count;
        this.msg = msg + ", id: "+Thread.currentThread().getId();

    }

    @Override
    public void run() {
        while(count-- > 0){
            System.out.println(msg);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                return;
            }
        }
        System.out.println(msg + " all done");
    }
}
