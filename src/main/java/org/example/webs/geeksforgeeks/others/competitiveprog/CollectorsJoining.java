package org.example.webs.geeksforgeeks.others.competitiveprog;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsJoining {
    // Collections.joining
    // It returns a Collector which joins or concatenates the input streams into String in the order of there appearance.
    public static void main(String[] args)
    {
        // Create a character array
        char[] ch = { 'G', 'e', 'e', 'k', 's',
                'f', 'o', 'r', 'G', 'e', 'e', 'k', 's' };
        // Create a character list
        List<Character> ch2 = Arrays.asList('G', 'e', 'e', 'k', 's',
                'f', 'o', 'r', 'G', 'e', 'e', 'k', 's');
        // Create a string list
        List<String> str = Arrays.asList("Geeks", "for", "Geeks");

        // Convert the character array into String
        String chString = Stream.of(ch)
                .map(arr -> new String(arr))
                .collect(Collectors.joining());
        // Convert the character list into String
        String chString2 = ch2.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
        // Convert the string list into String
        String chString3 = str.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
        // Convert the character list into String  => Collectors.joining() method & "," as the delimiter
        String chString4 = ch2.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        // Convert the string list into String
        String chString5 = str.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        // Convert the character list into String  => Collectors.joining() method & "," as the delimiter, prefix "[", suffix "]"
        String chString6 = ch2.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));
        // Convert the string list into String  => Collectors.joining() method & "," as the delimiter, prefix "[", suffix "]"
        String chString7 = str.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ", "{", "}"));


        // Print the concatenated String
        System.out.println(chString);
        System.out.println(chString2);
        System.out.println(chString3);
        System.out.println(chString4);
        System.out.println(chString5);
        System.out.println(chString6);
        System.out.println(chString7);
    }
}
