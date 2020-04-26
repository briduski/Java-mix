package org.example.webs.geeksforgeeks.singleton;
// https://www.geeksforgeeks.org/java-singleton-design-pattern-practices-examples/?ref=rp



public class Singleton6 {
    // Bill Pugh Singleton Implementation  (concept of inner static classes to use for singleton)
    // aka Initialization-on-demand holder idiom

    // It may seem like eager initialization but it is lazy initialization.
    //This is the most widely used approach as it doesn’t use synchronization.


    private Singleton6() {
        // private constructor 
    }

    // Inner class to provide instance of class 
    private static class BillPughSingleton {
        private static final Singleton6 INSTANCE = new Singleton6();
    }

    public static Singleton6 getInstance() {
        return BillPughSingleton.INSTANCE;
    }

}
