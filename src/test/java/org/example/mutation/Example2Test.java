package org.example.mutation;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Example2Test {
    @Test
    public void whenPalindrom_thenAccept() {
        Example1 palindromeTester = new Example1();
        assertTrue(palindromeTester.isPalindrome("noon"));
    }
    @Test
    public void whenPalindrom_thenAccept2() {
        Example1 palindromeTester = new Example1();
        assertTrue(palindromeTester.isPalindrome(""));
    }
    @Test
    public void whenPalindrom_thenAccept3() {
        Example1 palindromeTester = new Example1();
        assertFalse(palindromeTester.isPalindrome("rar1"));
    }
    @Test
    public void whenPalindrom_thenAccept4() {
        Example1 palindromeTester = new Example1();
        assertTrue(palindromeTester.isPalindrome(null));
    }
}
