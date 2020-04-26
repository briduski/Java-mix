package org.example.webs.geeksforgeeks.singleton;
// https://www.geeksforgeeks.org/singleton-design-pattern/

public class Singleton1 {
    // Lazy initialization:

    // Pros:
    //
    //Object is created only if it is needed. It may overcome resource overcome and wastage of CPU time.
    //Exception handling is also possible in method.

    //Cons:
    //
    //Every time a condition of null has to be checked.
    //instance can’t be accessed directly.
    //In multithreaded environment, it may break singleton property.


    //The main problem with above method is that it is not thread safe.

    private static Singleton1 obj;

    // private constructor to force use of
    // getInstance() to create Singleton object
    private Singleton1() {}

    public static Singleton1 getInstance() {
        if (obj==null)
            obj = new Singleton1();
        return obj;
    }

    public static void main(String[] args) {
        Singleton1 sing1 = Singleton1.getInstance();
        Singleton1 sing2 = Singleton1.getInstance();
        // This execution sequence creates two objects for singleton. Therefore this classic implementation is not thread safe.
    }
}
