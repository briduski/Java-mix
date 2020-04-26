package org.example.j9cookbook;

public class Example22_2 {
    public static void main(String[] args) {
        new ThreadDemo2("T1", 2);
        new ThreadDemo2("T2", 2);
    }

}
class ThreadDemo2 implements  Runnable {
    private int count;
    private String msg;
    private Thread t;

    public ThreadDemo2(String msg,int count) {
        this.count = count;
        this.msg = msg+" runner Thread, id: "+Thread.currentThread().getId();
        t = new Thread(this);
        t.setName(msg+" runner Thread");
        t.start();
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
