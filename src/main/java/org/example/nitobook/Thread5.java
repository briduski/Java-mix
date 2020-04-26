package org.example.nitobook;

public class Thread5 {
    private static ID id =new ID();
    public static void main(String[] args) {
        for (int i =0; i<2;i++){
            (new Thread(
                    () ->{
                        int j=0;
                        while (id.getValue()<10){
                            System.out.println(String.format("#%d -> [%d] %d", j++, Thread.currentThread().getId(), id.getId()));
                        }
                    }
            )).start();
        }
    }
}
class ID {
    private int id=1;
    public int getValue(){
        return id;
    }
    public synchronized int getId(){
        int t = id;
        for (int i =0;i< 10000000; ++i){
            Math.cos(Math.sqrt(2));
        }
        ++id;
        //System.out.println(">>>>["+Thread.currentThread().getId()+"] => t: "+t + ", id:"+id);
        return t;
    }
}