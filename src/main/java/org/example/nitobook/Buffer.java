package org.example.nitobook;

import java.lang.reflect.Array;

public class Buffer<T> {
    private T [] buff;
    private int head=0;
    private int tail=0;
    private long count=0;
    private int capacity =0;
    private final Object lock = new Object();
    public Buffer(T[] buffer) {
        buff = buffer;
    }
    public Buffer(int n) {
        capacity = n;
    }

    public long getCount(){
        return count;
    }
    public synchronized boolean empty() {
        return count==0;
    }
    public synchronized boolean full(){
        return (buff !=null && count == buff.length);

    }
    public synchronized T peek() throws Exception {
        if (empty()) throw new Exception("The buffer is empty");
        return buff[head];
    }
    public synchronized    T remove()throws Exception {
        //System.out.println(">Removed - Size: "+count);
        if (empty()) throw new Exception("The buffer is empty");
        T elem;
       synchronized (lock){
            elem = buff[head];
            head = next(head);
            --count;
        }
       /* if (count % 100 ==0){
            System.out.println("[Buffer] count: "+count);
        }*/

        return elem;

    }
    public  synchronized void insert(T elem)throws Exception {
        //System.out.println(">Insert - Size: "+count);
        if (buff == null)
            buff = (T[]) Array.newInstance(elem.getClass(), capacity);
        if (full()) throw new Exception("The buffer is full");
     //   System.out.println("tail: "+tail + ", elem: "+elem);
        synchronized (lock) {
            buff[tail] = elem;
            tail = next(tail);
            ++count;
       }
    }
    public int next(int n){
        return (n+1)%buff.length;
    }
}
