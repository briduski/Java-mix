package org.example.webs.geeksforgeeks.singleton;

public class Singleton3 {
    // Eager Instantiation

    // JVM executes static initializer when the class is loaded and hence this is guaranteed to be thread safe.
    // Use this method only when your singleton class is light and is used throughout the execution of your program.

    // It can be used when program will always use instance of this class, or the cost of creating the instance is not too large in terms of resources and time.

    // Cons:
    //
    //May lead to resource wastage. Because instance of class is created always, whether it is required or not.
    //CPU time is also wasted in creation of instance if it is not required.
    //Exception handling is not possible.


    private static Singleton3 obj = new Singleton3();

    private Singleton3() {}

    public static Singleton3 getInstance() {
        return obj;
    }

    public static void main(String[] args) {
        Singleton3 sing1 = Singleton3.getInstance();
        Singleton3 sing2 = Singleton3.getInstance();
    }
}
