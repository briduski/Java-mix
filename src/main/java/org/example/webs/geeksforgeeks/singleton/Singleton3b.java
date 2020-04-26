package org.example.webs.geeksforgeeks.singleton;

public class Singleton3b {
    // Eager initialization with static block

    // Pros:
    //
    //Very simple to implement.
    //No need to implement getInstance() method. Instance can be accessed directly.
    //Exceptions can be handled in static block.
    //Cons:
    //
    // May lead to resource wastage. Because instance of class is created always, whether it is required or not.
    // CPU time is also wasted in creation of instance if it is not required.
    public static Singleton3b instance;

    private Singleton3b() {
        // private constructor
    }

    {
        // static block to initialize instance
        instance = new Singleton3b();
    }
}
