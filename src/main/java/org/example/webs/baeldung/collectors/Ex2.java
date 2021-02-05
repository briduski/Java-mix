package org.example.webs.baeldung.collectors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ex2 {
    public static void main(String[] args) {
        final List<String> modifiableList = new ArrayList<>(Arrays.asList("one", "two", "three"));
        System.out.println(modifiableList);
        final List<String> unmodifiableList = List.of(modifiableList.toArray(new String[]{}));
        System.out.println(unmodifiableList);
        try {
            unmodifiableList.add("four");
        } catch(UnsupportedOperationException e){
            System.out.println("expected exception when inserting to an Unmodifiable List");
        }

        // guava
        //ImmutableList.copyOf(modifiableList);

        //apache.collections. common
        //ListUtils.unmodifiableList(list);

    }
}
