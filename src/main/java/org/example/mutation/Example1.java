package org.example.mutation;

public class Example1 {
    // https://www.baeldung.com/java-mutation-testing-with-pitest
    public static boolean isPalindrome(String inputString) {
        if (inputString == null)return true;

        if (inputString.length() == 0) {
            return true;
        }
        else {
            char firstChar = inputString.charAt(0);
            char lastChar = inputString.charAt(inputString.length() - 1);
            String mid = inputString.substring(1, inputString.length() - 1);
            return (firstChar == lastChar) && isPalindrome(mid);
        }
    }

}
