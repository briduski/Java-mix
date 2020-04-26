package org.example.mutation;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class Example3Test {
    @Test
    public void tt1() {
        Example3 t1 = new Example3();
        assertTrue(t1.ex1(null));
        assertFalse(t1.ex1(1));
        assertFalse(t1.ex1(-1));
    }
}
