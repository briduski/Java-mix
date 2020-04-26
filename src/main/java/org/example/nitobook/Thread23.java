package org.example.nitobook;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
public class Thread23 {
    // ReadWriteLock => many readers, 1 writer

    private static final ReadWriteLock lock = new ReentrantReadWriteLock(true);
    private static final Map<String, String>dictionary = new HashMap();
    private static final List<String>keys = new ArrayList<>();
    private static final Random rand = new Random();

    public static void main(String[] args) {
            new Thread23().doWork();
    }
    private void doWork(){
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(new Writer23(  lock, dictionary ,  keys,   rand));
        executorService.submit(new Reader23(lock, dictionary, keys, rand));
        executorService.submit(new Reader23(lock, dictionary, keys, rand));
       // executorService.submit(new Reader23(lock, dictionary, keys, rand));
    }
}
class Reader23 implements Runnable{
    private final ReadWriteLock lock;
    private Map<String, String>dictionary;
    private   final List<String>keys ;
    private   final Random rand  ;

    public Reader23(ReadWriteLock lock, Map<String, String>dictionary , List<String>keys, Random rand) {
        this.lock = lock;
        this.dictionary =dictionary;
        this.keys = keys;
        this.rand = rand;
    }

    @Override
    public void run() {
        while (true){
            lock.readLock().lock();;
            try{
                System.out.println("["+Thread.currentThread().getId() + "] "
                        + dictionary.get(keys.get(rand.nextInt(keys.size()))));

            }finally {
                lock.readLock().unlock();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class Writer23 implements Runnable{
    private final ReadWriteLock lock;
    private Map<String, String>dictionary;
    private   final List<String>keys ;
    private   final Random rand  ;
    private String []data = new String [6];

    public Writer23 (ReadWriteLock lock, Map<String, String>dictionary , List<String>keys, Random rand) {
        this.lock = lock;
        this.dictionary =dictionary;
        this.keys = keys;
        this.rand = rand;
        data[0]="0800;Høje Taastrup";
        data[1]="0900;København C";
        data[2]="0999;København C";
        data[3]="1000;København K";
        data[4]="1050;København K";
        data[5]="9990;Skagen";
    }

    @Override
    public void run() {
        for (int i=0; i<data.length;++i){
            lock.writeLock().lock();
            try{
                String []elems = data[i].split(";");
                dictionary.put(elems[0], elems[0]+ " "+elems[1]);
                keys.add(elems[0]);
            }finally {
                lock.writeLock().unlock();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Dictionary initialized");
    }
}