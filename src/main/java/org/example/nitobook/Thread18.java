package org.example.nitobook;

import java.util.Random;
import java.util.concurrent.Exchanger;

public class Thread18 {
    // EXCHANGER
    // synchronization point where threads can exchange objects
    // thread1 call exchange(), thread2 call exchange() => threads can switch two objects , the objects are inter-changed.

    private static final Random rand = new Random();
    private static final Exchanger<Buffer<Character>>exchanger = new Exchanger();
    private static final Buffer<Character> buffer1 = new Buffer<>(3);
    private static final Buffer<Character> buffer2 = new Buffer<>(1);
    private static final char ch = 'Z';

    public static void main(String[] args) {
       Thread t1 = new Thread(new Producer18(buffer1, ch,exchanger));
       Thread t2 = new Thread(new Consumer18(buffer2, ch,exchanger));
        System.out.println("Buffer 1 HC: "+buffer1.hashCode());
        System.out.println("Buffer 2 HC: "+buffer2.hashCode());
       t1.start();
       t2.start();

    }
}
class Producer18 implements Runnable {
    private  Buffer<Character> buffer1 ;
    private  char ch  ;
    private Random rand = new Random();
    private Exchanger<Buffer<Character>>exchanger ;

    public Producer18(Buffer<Character> buf1,char ch, Exchanger exchanger) {
        this.buffer1 = buf1;
        this.ch = ch;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            while (true){
                buffer1.insert(next());
                Thread.sleep(rand.nextInt(2000));
                if (buffer1.full()){
                    System.out.println(" [Prod] exchange -> ("+buffer1.getCount()+")  - HC: " + buffer1.hashCode());
                    buffer1 = exchanger.exchange(buffer1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private  char next() {
        if (ch == 'Z'){
            ch = 'A';
        }else {
            ++ch;
        }
        return ch;
    }
}

class Consumer18 implements Runnable{
    private  Buffer<Character> buffer2 ;
    private  char ch  ;
    private Random rand = new Random();
    private Exchanger<Buffer<Character>>exchanger ;

    public Consumer18(Buffer<Character> buf2,char ch, Exchanger exchanger) {
        this.buffer2 = buf2;
        this.ch = ch;
        this.exchanger = exchanger;
    }
    @Override
    public void run() {
        try {
       //     System.out.println("init consumer, buffer size "+buffer2.);
            while (true){
                Thread.sleep(rand.nextInt(2000));
                if (buffer2.empty()){
                    System.out.println("[Cons] exchange <- ");
                    buffer2 = exchanger.exchange(buffer2);
                    System.out.println("[Cons] Buffer 2 count:" + buffer2.getCount() + " - HC: " +buffer2.hashCode());
                }
                System.out.println("letter: " + buffer2.remove());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}