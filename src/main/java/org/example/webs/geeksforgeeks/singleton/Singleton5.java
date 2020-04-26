package org.example.webs.geeksforgeeks.singleton;

public class Singleton5 {
    // Lazy initialization with Double check locking

    // Pros:
    //
    //Lazy initialization is possible.
    //It is also thread safe.
    //Performance overhead gets reduced because of synchronized keyword.
    //Cons:
    //
    //First time, it can affect performance.

    private static Singleton5 instance;

    private Singleton5() {
        // private constructor 
    }

    public static Singleton5 getInstance() {
        if (instance == null) {
            //synchronized block to remove overhead 
            synchronized (Singleton5.class) {
                if(instance==null) {
                    // if instance is null, initialize 
                    instance = new Singleton5();
                }
            }
        }
        return instance;
    }

}
