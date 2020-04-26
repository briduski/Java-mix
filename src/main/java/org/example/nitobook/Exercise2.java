package org.example.nitobook;

public class Exercise2 {
    public static void main(String[] args) {
        long[] values ={ 1000000, 10000000, 100000000,
                // 1000000000, 10000000000L, 100000000000L,
                // 1000000000000L, 10000000000000L, 100000000000000L,
                // 1000000000000000L, 10000000000000000L, 100000000000000000L,
                1000000000000000000L };
        Prime[] primes = new Prime[values.length];
        for (int i = 0; i < primes.length; ++i) primes[i] = new Prime();
        Thread t1 = new Thread(
                () ->{
                    System.out.println("["+ Thread.currentThread().getId() + "] Looping :) ") ;
                    for (int i = 0; i < values.length; ++i) {
                        exe(values[i], primes[i]);
                    }
                }
        );
        t1.start();
        try {
            System.out.println("Waiting to finish processing ... ");
            t1.join( 9000);
            System.out.println("Finished :) !!!");
        }catch (Exception e){}

        for (int i = 0; i < primes.length; ++i) System.out.println(primes[i].getValue());

        // nextPrime() is performed must now change the main() method, so each call of the method nextPrime()
        // is performed in its own thread. You can of course use the class Creator to create Runnable objects. in its own thread.
    }

    public static void exe(final long value, final Prime prime){
        new Thread(
                () -> {
                    System.out.println("------["+ Thread.currentThread().getId() + "] exe :) ") ;
                    Creator.nextPrime(value, prime);
                }
        ).start();
    }
}

class Prime {
    private long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
    public void setValueIncr(long incr){
        this.value+=incr;
    }
}
class Creator implements  Runnable{
    private long value;
    private Prime prime;
    public Creator(long value, Prime prime){
        this.value = value;
        this.prime = prime;
    }

    @Override
    public void run() {
        nextPrime(value, prime);
    }
    public static  void nextPrime(long t, Prime prime){
        if (t<2) prime.setValue( 2);
        else{
            long init = t%2 ==0 ? t+1 : t;
            long incr = prime.getValue()+2;
            for (prime.setValue(init) ; !isPrime(prime.getValue());prime.setValueIncr(2));
        }
        System.out.println("["+ Thread.currentThread().getId() + "] Result is: "+prime.getValue());
    }

    private static boolean isPrime(long t){
        if(t==1 || t==2 || t==3 || t== 5 || t==7) return true;
        if (t<11 || t%2 == 0) return false;
        for (long k = 3, m = (long)Math.sqrt(t)+1; k <= m;k+=2){
            // System.out.println("k: "+ k+ ", m: "+ ((long)Math.sqrt(t)+1));
            // System.out.println(t+"%"+k + "=" + (t%k));
            if(t%k == 0)return false;
        }
        return true;
    }
}

