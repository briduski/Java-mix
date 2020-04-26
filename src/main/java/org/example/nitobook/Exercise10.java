package org.example.nitobook;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Exercise10 {
    private static String[]names1= {"1name1", "1name2", "1name3","1name4"};
    private static String[]names2= {"2NAME1", "2NAME2", "2NAME3","2NAME4"};
    private static final ReadWriteLock lock = new ReentrantReadWriteLock(true);

    public static void main(String[] args) {
        //Thread to a Writer... add names1
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(new WriterEx10(names1, lock));
        //Thread to a Writer... add names2
        executorService.submit(new WriterEx10(names2, lock));
        //8 Readers
        executorService.submit(new ReaderEx10(lock));
        executorService.submit(new ReaderEx10(lock));
        executorService.submit(new ReaderEx10(lock));




    }
}
class StorageEx10 {
    private static  int ID = 0;
    private Map<Integer, String> cache = new HashMap();
    public int insert (String name){
        //new name to the data structure identified by the next id
        cache.put(ID++, name);
        return 1;
    }
    public String getName(int id){
        // a name with an id or null, the id is not found
       return  cache.get(id);
    }
    public int length(){
        // number of names
        return cache.size();
    }

    private StorageEx10() {
        // private constructor
    }

    // Inner class to provide instance of class
    private static class BillPughSingletonStorageEx10 {
        private static final StorageEx10 INSTANCE = new StorageEx10();
    }

    public static StorageEx10 getInstance() {
        return StorageEx10.BillPughSingletonStorageEx10.INSTANCE;
    }
}
class WriterEx10 implements Runnable {
    private String []names;
    private final ReadWriteLock lock;

    public WriterEx10(String[] names,ReadWriteLock lock) {
        this.names = names;
        this.lock = lock;
    }

    @Override
    public void run() {
        StorageEx10 storageEx10 = StorageEx10.getInstance();
        for (int i=0; i<names.length;++i){
            //write lock
            lock.writeLock().lock();
            try{
                // add name
                storageEx10.insert( names[i]);
            }finally {
                // release lock
                lock.writeLock().unlock();
            }
            // sleep 2 seconds
            try {
                int sleeeep= 2000;
                System.out.println("["+Thread.currentThread().getId() +"] > Writer - Sleeping "+sleeeep);
                Thread.sleep(sleeeep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Storage initialized");
    }
}
class ReaderEx10 implements Runnable{
    //private String names;
    private final ReadWriteLock lock;
    private final Random rand = new Random();

    public ReaderEx10(ReadWriteLock lock) {
        this.lock = lock;
    }
    @Override
    public void run() {
        StorageEx10 storageEx10 = StorageEx10.getInstance();
        while (true){
            lock.readLock().lock();;
            try{
                System.out.println("["+Thread.currentThread().getId() + "] "
                        + storageEx10.getName(rand.nextInt(storageEx10.length()))
                );

            }finally {
                lock.readLock().unlock();
            }
            try {
                int sleeeep=1000;
                System.out.println("["+Thread.currentThread().getId() + "]  <> Reader - Sleeping "+sleeeep);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
