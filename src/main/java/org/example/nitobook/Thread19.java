package org.example.nitobook;

import java.util.Random;
import java.util.concurrent.*;

public class Thread19 {
    // Semaphore

    // permits in relation to a number of threads that want to access a shared resource
    // there is a license, a thread used and release it. Binary Sem (0,1), Counting Sem (0,1,2..)
    public static void main(String[] args) {
        final Resources19 resources = new Resources19();
        Worker19 worker = new Worker19(resources);
        int N = 2 * Resources19.N;
        System.out.println("Threads "+N);
        ExecutorService executor = Executors.newFixedThreadPool(N);
        for (int i = 0; i<N; ++i) executor.execute(worker);
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (Exception e){}
        executor.shutdownNow();

    }
}
class Worker19 implements Runnable {
    private static final Random rand = new Random();
    private Resources19 resources;
    private long counter = 0;
    public Worker19(Resources19 resources){
        this.resources = resources;
    }
    @Override
    public void run(){
        try {
            while (counter < 2){
                Resource19 res = resources.getResource();
                System.out.printf("[%d] -getResource-> %s: %3d\n", Thread.currentThread().getId(), res.toString(), res.getValue());
                Thread.sleep(500 + rand.nextInt(500));
                res.updateValue(++counter);
                resources.putResource(res);
                System.out.printf("[%d] =putResource(Incremented)=> %s: %3d\n", Thread.currentThread().getId(), res.toString(), res.getValue());
                Thread.sleep(500 + rand.nextInt(500));
            }
        }catch (Exception e){

        }
    }
}
class Resources19 {
    public static final int N = 3;
    private final Resource19[]resources= new Resource19[N];
    private final Semaphore sema = new Semaphore(N, true);
    private final boolean[]used = new boolean[N];

    public Resources19() {
        for (int i=0; i< resources.length;i++)resources[i]= new Resource19();
    }
    public Resource19 getResource() throws InterruptedException {
      //  System.out.println("[" + Thread.currentThread().getId() + "] ... getResource ... ");
        sema.acquire();
      //  System.out.println("[" + Thread.currentThread().getId() + "] ... resource got! ");
        return get();
    }
    public void putResource(Resource19 res){
        if (unused(res)) sema.release();
    }
    private synchronized Resource19 get(){
        for (int i=0; i<N;i++){
            if(!used[i]){
                used[i]=true;
                return resources[i];
            }
        }
        return null;
    }
    private synchronized boolean unused(Resource19 res){
        for (int i=0; i<N;i++){
            if (res.equals(resources[i])){
                if (used[i]){
                    used[i]=false;
                    return true;
                }else {
                    return false;
                }
            }
        }
        return false;
    }

}

class Resource19 {
    private static  int ID =0;
    private int id;      // the resource's id
    private long value;  // the resource's value

    public Resource19() {
        id = ++ID;
    }

    public  long getValue() {
        return value;
    }
    public void updateValue(long value){
        this.value +=value;
    }
    public boolean equals (Object obj){
        if (obj == null) return false;
        if (getClass() == obj.getClass() )return ((Resource19)obj).id == id;
        return false;
    }
    public String toString(){
        return String.format("Resource: [%d]", id);
    }
}
