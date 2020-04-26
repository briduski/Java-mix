package org.example.ud_javamultithread.K_Deadlock_11;


import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <a href="https://wikipedia.org/wiki/Deadlock">Deadlock</a>
 * <br><br>
 * Codes with minor comments are from
 * <a href="http://www.caveofprogramming.com/youtube/">
 * <em>http://www.caveofprogramming.com/youtube/</em>
 * </a>
 * <br>
 * also freely available at
 * <a href="https://www.udemy.com/java-multithreading/?couponCode=FREE">
 * <em>https://www.udemy.com/java-multithreading/?couponCode=FREE</em>
 * </a>
 *
 * @author Z.B. Celik <celik.berkay@gmail.com>
 */
@SuppressWarnings("InfiniteLoopStatement")
public class Runner {

    private Account acc1 = new Account();
    private Account acc2 = new Account();
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    //don't hold several locks at once. If you do, always acquire the locks in the same order
    //try to get the both locks
    private void acquireLocks(Lock firstLock, Lock secondLock, String id) throws InterruptedException {
        while (true) {
            // Acquire locks
            boolean gotFirstLock = false;
            boolean gotSecondLock = false;
            try {
                System.out.println("-------adquiring id "+id);
                /**
                 * tryLock() which will only acquire a lock if it’s available
                 * and not already acquired by another thread and tryLock(long
                 * time,TimeUnit unit), which will try to acquire a lock and, if
                 * it's unavailable wait for the specified timer to expire
                 * before giving up
                 */
                gotFirstLock = firstLock.tryLock();
                gotSecondLock = secondLock.tryLock();
            } finally {
                if (gotFirstLock && gotSecondLock) return;
                else if (gotFirstLock) {
                    System.out.println("Releash first lock, not got second, "+id);
                    firstLock.unlock();
                }
                else if (gotSecondLock) {
                    System.out.println("Releash second lock, not got first, "+id);
                    secondLock.unlock();}
            }
            // Locks not acquired
            Thread.sleep(1);
        }
    }

    //firstThread runs
    public void firstThread() throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            acquireLocks(lock1, lock2, "[1]");
            try {
                System.out.println("***************************** \n[1] from acc1 to acc2, #"+i);
                Account.transfer(acc1, acc2, random.nextInt(100));
               // finished();
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
            Thread.sleep(1);
        }
    }

    //SecondThread runs
    public void secondThread() throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            acquireLocks(lock2, lock1,"[2]");
            try {
                System.out.println("***************************** \n[2] from acc2 to acc1, #"+i);
                Account.transfer(acc2, acc1, random.nextInt(100));
              //  finished();
            } finally {
                lock1.unlock();
                lock2.unlock();
            }  Thread.sleep(1);
        }
    }

    //When both threads finish execution, finished runs
    public void finished() {
        System.out.println("Account 1 balance: " + acc1.getBalance());
        System.out.println("Account 2 balance: " + acc2.getBalance());
        System.out.println("Total balance: " + (acc1.getBalance() + acc2.getBalance()));
    }
}