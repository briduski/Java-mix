package org.example.webs.geeksforgeeks.singleton;

public class Singleton4 {
    // Double check locking

    // If you notice carefully once an object is created
    // synchronization is no longer useful because now obj will not be null
    // and any sequence of operations will lead to consistent results.
    // So we will only acquire lock on the getInstance() once, when the obj is null.
    // This way we only synchronize the first way through, just what we want.

    private volatile static Singleton4 obj;
    // volatile keyword here makes sure that the changes made in one thread are immediately reflect in other thread
    // The values of volatile variable will never be cached and all writes and reads will be done to and from the main memory.
    //
    // We have declared the obj volatile which ensures that multiple threads offer the obj variable correctly
    // when it is being initialized to Singleton instance. This method drastically reduces the overhead of calling the synchronized method every time.

    private Singleton4() {}

    public static Singleton4 getInstance() {
        if (obj == null) {
            // To make thread safe
            synchronized (Singleton4.class) {
                // check again as multiple threads
                // can reach above step
                if (obj==null)
                    obj = new Singleton4();
            }
        }
        return obj;
    }

    public static void main(String[] args) {
        Singleton4 sing1 =   Singleton4.getInstance();
        Singleton4 sing2 =   Singleton4.getInstance();
    }
}
