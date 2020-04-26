package org.example.webs.geeksforgeeks.singleton;

public class Singleton2 {
    // Thread Safe Singleton:

    // Pros:
    //
    //Lazy initialization is possible.
    //It is also thread safe.
    //Cons:
    //
    //getInstance() method is synchronized so it causes slow performance as multiple
    // threads can’t access it simultaneously

    // The main disadvantage of this is method is that using synchronized every time while creating the singleton object
    // is expensive and may decrease the performance of your program.
    // However if performance of getInstance() is not critical for your application this method provides a clean and simple solution.
    private static Singleton2 obj;

    private Singleton2() {}

    // Only one thread can execute this at a time
    public static synchronized Singleton2 getInstance() {
        if (obj==null)
            obj = new Singleton2();
        return obj;
    }

    public static void main(String[] args) {
        Singleton2 sing1 = Singleton2.getInstance();
        Singleton2 sing2 = Singleton2.getInstance();
    }
}
