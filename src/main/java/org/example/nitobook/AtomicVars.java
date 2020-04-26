package org.example.nitobook;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicVars {
    //java.util.concurrent.atomic
    // AtomicBoolean,
    // AtomicInteger, AtomicIntegerArray,
    // AtomicLong, AtomicLongArray,
    // AtomicReference, AtomicReferenceArray
    // DoubleAccumulator, DoubleAdder
    // LongAccumulator, LongAdder

    // Option A
    private static volatile long ID = 1;
    private static synchronized long getID(){
        return ID++; // ++ is NOT thread safe
    }

    // Option B
    private static AtomicLong ATOMIC_ID = new AtomicLong(1);
    private static long getAtomicID(){
        return ATOMIC_ID.getAndIncrement();
    }

}
