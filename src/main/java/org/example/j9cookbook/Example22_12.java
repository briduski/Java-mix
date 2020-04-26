package org.example.j9cookbook;


import java.io.IOException;
import java.util.LinkedList;

public class Example22_12 {
    public static void main(String[] args) throws IOException {
        ProdCons1 pc = new ProdCons1();
        System.out.println("Ready (p to produce, c to consume):");
        int i;
        // possible deadlock, if consume when list.size ==0
        while ((i = System.in.read()) != -1){
            char ch = (char)i;
            switch (ch){
                case 'p': pc.produce(); break;
                case 'c': pc.consume(); break;
            }
        }
    }
}
class ProdCons1 {
    protected LinkedList<Object>list = new LinkedList<>();
    protected void consume(){
        Object obj = null;
        int len = 0;
        synchronized (list){
            while (list.size() ==0){
                try {
                    list.wait();
                } catch (InterruptedException e){
                    return;
                }
            }
            obj = list.removeLast();
            len = list.size();
        }
        System.out.println("Consuming object "+obj);
        System.out.println("List size now "+len);
    }
    protected void produce(){
        int len = 0;
        synchronized (list){
            Object justProducerd = new Object();
            list.addFirst(justProducerd);
            len = list.size();
            list.notifyAll();
        }
        System.out.println("List size now "+len);
    }
}