package org.example.webs.baeldung.collectors;

import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public class Ex1 {
    public static void main(String[] args) {
        List<String> givenList = Arrays.asList("a", "bb","zzzz",  "ccc", "dd");
        List<String> listWithDuplicates = Arrays.asList("a", "bb", "c", "d", "bb");
        List<String> result1 = givenList.stream()
                .collect(toList());
        System.out.println(result1);

        List<String> result2 = givenList.stream()
                .collect(toUnmodifiableList());
        System.out.println(result2);
        try {
            result2.add("foo");
        } catch(UnsupportedOperationException e){
            System.out.println("expected exception when inserting to an UnmodifiableList");
        }
        Set<String> result3 = givenList.stream()
                .collect(toSet());
        System.out.println(result3);

        Set<String> result4 = listWithDuplicates.stream().collect(toSet());
        System.out.println("Size: "+result4.size()+", expected 4");

        Set<String> result5 = givenList.stream()
                .collect(toUnmodifiableSet());

        System.out.println(result5);
        try {
            result5.add("foo");
        } catch(UnsupportedOperationException e){
            System.out.println("expected exception when inserting to an UnmodifiableSet");
        }

        List<String> result6 = givenList.stream()
                .collect(toCollection(LinkedList::new));
        System.out.println(result6);

        Map<String, Integer> result7 = givenList.stream()
                .collect(toMap(Function.identity(), String::length));
        System.out.println(result7);

        try {
            listWithDuplicates.stream().collect(toMap(Function.identity(), String::length));
        } catch(IllegalStateException e){
            System.out.println("expected exception when inserting and key collision happens ");
        }

        System.out.println("list with duplicates: "+listWithDuplicates);
        Map<String, Integer> result8 =
                listWithDuplicates.stream() .collect(toMap(Function.identity(), String::length, (item, identicalItem) -> item));
        //The third argument here is a BinaryOperator, where we can specify how we want collisions to be handled. In this case,
        // we'll just pick any of these two colliding values because we know that the same strings will always have the same lengths, too.
        System.out.println(result8);

        Map<String, Integer> result9 = givenList.stream()
                .collect(toUnmodifiableMap(Function.identity(), String::length));
        try {
            result9.put("foo", 3);
        } catch(UnsupportedOperationException e){
            System.out.println("expected exception when inserting to an UnmodifiableMap");
        }

        System.out.println(result9);


        // guava
       /* List<String> result10 = givenList.stream()
                .collect(collectingAndThen(toList(), ImmutableList::copyOf));*/


        String result11 = givenList.stream()
                .collect(joining());
        System.out.println(result11);
        String result12 = givenList.stream()
                .collect(joining(" "));
        System.out.println(result12);
        String result13 = givenList.stream()
                .collect(joining(" ", "PRE-", "-POST"));
        System.out.println(result13);


        Long result14 = listWithDuplicates.stream()
                .collect(counting());
        System.out.println(listWithDuplicates);
        System.out.println(result14);

        DoubleSummaryStatistics result15 = listWithDuplicates.stream()
                .collect(summarizingDouble(String::length));
        System.out.println(result15);

        Double result16 = givenList.stream()
                .collect(averagingDouble(String::length));
        System.out.println(givenList);
        System.out.println(result16);

        Double result17 = givenList.stream()
                .collect(summingDouble(String::length));
        System.out.println(result17);

        Optional<String> result18 = givenList.stream()
                .collect(maxBy(Comparator.naturalOrder()));
        System.out.println(result18);

        Map<Integer, Set<String>> result19 = givenList.stream()
                .collect(groupingBy(String::length, toSet()));
        System.out.println(result19);

        Map<Boolean, List<String>> result20 = givenList.stream()
                .collect(partitioningBy(s -> s.length() > 2));
        System.out.println(result20);

        List<Integer> numbers = Arrays.asList(42, 4, 2, 24);
        Optional<Integer> min1 = numbers.stream().collect(minBy(Integer::compareTo));
        Optional<Integer> max2 = numbers.stream().collect(maxBy(Integer::compareTo));

        System.out.println(numbers);
        System.out.println(min1);
        System.out.println(max2);


        Range range = numbers.stream()
                .collect(teeing(
                        minBy(Integer::compareTo),// The first collector
                        maxBy(Integer::compareTo),// The second collector
                        (min, max) ->
                                new Range(min.orElse(null),
                                          max.orElse(null)
                                        )
                        )
                );
        System.out.println(range);

        // Custom collectors

    }

    private static class Range {

        private final Integer min;

        private final Integer max;

        Range(Integer min, Integer max) {
            this.min = min;
            this.max = max;
        }

        Integer getMin() {
            return min;
        }

        Integer getMax() {
            return max;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Range range = (Range) o;
            return Objects.equals(getMin(), range.getMin()) && Objects.equals(getMax(), range.getMax());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getMin(), getMax());
        }

        @Override
        public String toString() {
            return "Range{" + "min=" + min + ", max=" + max + '}';
        }
    }
}
