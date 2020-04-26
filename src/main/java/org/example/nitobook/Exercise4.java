package org.example.nitobook;

public class Exercise4 {
    public static void main(String[] args) {
    Shared1 shared1 = new Shared1();
    Producer1 producer1 = new Producer1(shared1);
    Consumer1 consumer1 = new Consumer1(shared1);
    Consumer1 consumer2 = new Consumer1(shared1);
    Consumer1 consumer3 = new Consumer1(shared1);
    Consumer1 consumer4 = new Consumer1(shared1);
    Consumer1 consumer5 = new Consumer1(shared1);/**/
    producer1.start();
    consumer1.start(); // !"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ
     consumer2.start();  // !#"%&$()*+,'.-/023456781:9<=;?>@ABCDEFHIJGLMKOPQRNSTVUWXY[\Z]^_`abcdefghijklmnopqrstuvwxyz{|}~
     consumer3.start(); // !$%&'#)*"+(-,/.134560792;<:>?8@=BAEDGHIJKLMNCPQOFSUVWXRZY[\]^_T`badcefgijhlkmno   / !#$"'(&*+,-.%)/
     consumer4.start();  // !$&#(")+*-.'%12305/689,:7=>?4@B<;DFGHIJKLMCAPORSTUNWXYZE[V\Q^`]ba_dcfhijelmkgno   / !"#&(%$*)-'/01.,4+57382;:=>?@AB9DE6FC<IKLMNHPGRQTUVWXYZOJ\[^S_]bca`fgediklhmojqrspuvnwyz{|t~}x
     consumer5.start();  // !#"'&*%$,.+)(10/-543298<=>7@6A?;:EDCBIHGFMLKJQPOUVNWYZTSR\^_[Xa`]edcbhjgkfmliponsrqwvutzyx}|{~  / "%&$#*+!-.,01)(45'789:6<32?@ABCDEF/G>=;LMKOPQRSTUVWXJZIH[Y^_Nabc`]fgh\iedlkopjqnmtswxyz{|}~rvu

    }
}
class Producer1 extends Thread{
    private final Shared1 shared;
    public Producer1(Shared1 shared){
        this.shared = shared;
    }

    @Override
    public void run() {
     //   System.out.println("Producer starts running ! ");
        for (char ch = 33 ;ch<=127;++ch){
            shared.setValue(ch);
        }
        System.out.println(" \n ... ending Producer run ... ");
    }
}
class Consumer1 extends Thread{
    private final Shared1 shared;
    public Consumer1(Shared1 shared){
        this.shared = shared;
    }
    @Override
    public void run() {
        char ch;
      //  System.out.println("Consumer starts running ! ");
        do {
            ch = shared.getValue();
            System.out.print(
                    //"[" +
                    ch
                 //   + "["+Integer.valueOf(ch)+"]"
                    //+ "] , Thread:" + Thread.currentThread().getId()
                    );
        } while (ch <127);
        System.out.println(" ... ending Consumer run ... ");
    }}
class Shared1 {
    private char value;
    private volatile boolean writeable = true;

    public synchronized void setValue(char value){
        while (!writeable) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
     //  System.out.println("------ Writing enabled, setValue, writeable: "+writeable);
        this.value = value;
        writeable = false;
        notifyAll();
    }
    public synchronized char getValue(){
        while (writeable){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    //    System.out.println(">>>> Reading enabled, getValue, writeable: "+writeable+ ", Thread: "+Thread.currentThread().getId());
        writeable = true;
        notify();
        return value;
    }
}