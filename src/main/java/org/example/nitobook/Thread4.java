package org.example.nitobook;

public class Thread4 {
    private static long prime;
    public static void main(String[] args) {
   //    boolean b= isPrime(17);
    //    System.out.println("Result: "+b);
        long input = 32l;
      Thread th = new Thread(
              () -> nextPrime(input)
      );
      th.start();
        /*  try {
          th.join(); // the primary thread waits until th is finished
      } catch (  Exception e  ){}**/
      System.out.println("!!!Next prime from prime " +input + ", prime: "+prime);
    }
    private static void nextPrime(long t){
        if (t<2) prime = 2;
        else{
            for (prime = t%2 ==0 ? t+1 : t; !isPrime(prime);prime+=2);

        }
        System.out.println("Result is: "+prime);
    }
    private static boolean isPrime(long t){
        if(t==1 || t==2 || t==3 || t== 5 || t==7)return true;
        if (t<11 || t%2 == 0) return false;
        for (long k = 3, m = (long)Math.sqrt(t)+1; k <= m;k+=2){
            // System.out.println("k: "+ k+ ", m: "+ ((long)Math.sqrt(t)+1));
            //System.out.println(t+"%"+k + "=" + (t%k));
            if(t%k == 0)return false;
        }
        return true;
    }

    private static void print (String msg){
        System.out.println("["
                + Thread.currentThread().getId()
                + "] "
                + msg
        );
    }
}
