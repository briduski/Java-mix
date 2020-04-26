package org.example.nitobook;

public class Thread3 {
    public static void main(String[] args) {
        for (int i =0; i< 4;++i) new Thread(() ->work()).start();
    }
    private static void work(){
        print("Started");
        double y =0;
        int i=0;
        for (  i =0; i< 100000000L;i++){
            y = Math.cos(Math.sqrt(2));
        }
        print(String.format("%1.4f", y) + ", #"+i);
    }
    private static void print (String text){
        System.out.println("[" + Thread.currentThread().getId() + "] "
                          + text);
    }
}
