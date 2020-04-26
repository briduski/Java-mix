package org.example.mutation;

public class Example2 {
    public static boolean isPalindrome2(String inputString) {
        if (inputString== null || inputString.length() == 0) {
            return true;
        } else {
            char firstChar = inputString.charAt(0);
            char lastChar = inputString.charAt(inputString.length() - 1);
            String mid = inputString.substring(1, inputString.length() - 1);
            return (firstChar == lastChar) && isPalindrome2(mid);
        }
    }
}
