package Streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BasicStreamingTools {

    //Stream represents a sequence of elements on which one or more operations can be performed

    public static void main(String[] args) {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        //filter
        System.out.println("Results from Filter Stream:");
        stringCollection.stream().filter((s) -> s.startsWith("a")).forEach(System.out::println);

        //sorted
        System.out.println("Results from Sorted Stream:");
        stringCollection.stream().sorted().filter((s) -> s.startsWith("a")).forEach(System.out::println);

        //map
        // converts each element into another object via the given function
        // you can also use map to transform each object into another type
        System.out.println("Results from Map Stream:");
        stringCollection
                .stream()
                .map(String::toUpperCase)
                .sorted((a, b) -> b.compareTo(a))
                .forEach(System.out::println);


        //match
        System.out.println("Results from Match Stream:");
        boolean anyStartWithX = stringCollection.stream().anyMatch((string) -> string.startsWith("x"));
        System.out.println(anyStartWithX);

        //count
        System.out.println("Results from Count Stream:");
        long startsWithB = stringCollection.stream().filter((s) -> s.startsWith("b")).count();
        System.out.println(startsWithB);

        //reduce
        // The result is an Optional holding the reduced value.
        System.out.println("Results from Reduce Stream:");
        Optional<String> reduced = stringCollection.stream().sorted().reduce((s1, s2) -> s1 + "#" + s2);
        reduced.ifPresent(System.out::println);

    }

}
