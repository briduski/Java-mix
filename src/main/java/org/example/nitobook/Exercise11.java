package org.example.nitobook;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.*;

public class Exercise11 {
    public static final Random rand = new Random();
    private static final int N = 5;
    private static final int M = 15;

    public static void main(String[] args) {
        CountDownLatch done = new CountDownLatch(M);
        ConcurrentMap<String, BigInteger>map = new ConcurrentHashMap<>();
        ExecutorService executorService = Executors.newFixedThreadPool(M);
        for (int i=0; i<M;++i){
            //System.out.println("Iteration #"+i);
            executorService.submit(new WorkerEx11(map, done, createKey(),  operation(), i));
            try{
                Thread.sleep(300);
            }catch (Exception e){

            }
        }
        try{
            Thread.sleep(10000);
        }catch (Exception e){

        }
        executorService.shutdown();


    }
    private static WorkerEx11.OPERATION operation (){
        int v = rand.nextInt(3);
        if (v==0){
            return WorkerEx11.OPERATION.ADD;
        }else if (v==1){
            return WorkerEx11.OPERATION.REP;
        } else {
            return WorkerEx11.OPERATION.REM;
        }
    }
    private static String createKey(){
        // 65 'A', 65+26=91 'Z'
       char a =  (char)(rand.nextInt(2)+65);
       char b =  (char)(rand.nextInt(2)+65);
       String key = ""+ a+b;
      //  System.out.println("key "+key);
        return key;
    }
}
class WorkerEx11 implements Runnable{
    public static enum OPERATION {ADD, REP, REM}
    private final ConcurrentMap<String, BigInteger> map;
    private final CountDownLatch done;
    private String key;
    private OPERATION opr;
    private int iteration;


    public WorkerEx11(ConcurrentMap<String, BigInteger> map, CountDownLatch done, String key, OPERATION oper,int iteration) {
        this.map = map;
         this.done = done;
         this.key = key;
         this.opr = oper;
         this.iteration = iteration;
    }

    @Override
    public void run() {
        try{

          //  printMap();
            int input = new Random().nextInt(10000);
            BigInteger val =   sum(input);
          //  System.out.println("Operation "+opr);
            String msg= "#"+iteration + " "+ opr + " : " + key;
            BigInteger res;
          if( opr.compareTo(OPERATION.ADD) ==0){
                 res =   map.putIfAbsent(key,val );
                if (res == null){
                    msg+=" sum("+input+")="+val;
                }
          } else if (opr.compareTo(OPERATION.REP)==0){
              // replace
                res = map.replace(key, val);
              if (res == null){
                  msg+= " Not found" ;
              }else {
                  msg += " replaced value:" +val;
              }
          } else if (opr.compareTo(OPERATION.REM)==0){
              // remove
              res =  map.remove(key);
              if (res == null){
                  msg+=" Not found" ;
              }else {
                  msg += " removed value:" +val;
              }
          }
          Thread.sleep(1000);
            System.out.println(msg);
        } catch (Exception e){
                e.printStackTrace();
        }
        done.countDown();
    }
    public void printMap(){
        map.forEach((k,v)-> System.out.println(k + " : "+ v));
    }
    private BigInteger sum (int n){
        BigInteger sum = BigInteger.ZERO;
        for (int i=0;i<n;++i ){
            sum = sum.add(BigInteger.valueOf(i));
        }
        return sum;
    }
}