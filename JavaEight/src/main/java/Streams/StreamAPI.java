package Streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamAPI {
    // Streams can be created from various data sources, especially collections.
    // Filter, map and sorted are intermediate operations, but forEach is a terminal operation
    // A chain of stream operations as seen in the example above is also known as operation pipeline
    // A function is non-interfering, so it does not modify the underlying data source of the stream,
        // example no lambda expression does modify list by adding or removing elements from the collection.
    // Lists and Sets support new methods stream() and parallelStream() to either create a sequential or a parallel stream.
    // Parallel streams are capable of operating on multiple threads

    public static void main(String[] args) {
        // filter, map, sort, foreach
        System.out.println("BASIC EXAMPLE:");
        basicExample();

        // you do not have to create collections in order to work with streams
        System.out.println("\nNO STREAM METHOD EXAMPLE:");
        noStreamMethod();

        // special stream like LongStream and DoubleStream
        System.out.println("\nSPECIAL STREAM EXAMPLE:");
        specialStream();

        // performing computation of array's list interior
        System.out.println("\nARRAY TO STREAM EXAMPLE:");
        arrayToStream();

        // map string to int
        System.out.println("\nSTRING TO INT EXAMPLE:");
        stringToInt();

        // map double to int by using mapToObj
        System.out.println("\nDOUBLE TO INT EXAMPLE:");
        doubleToInt();

        // instead of mapping all elements
        System.out.println("\nANY MATCH USAGE EXAMPLE:");
        anyMatchUsage();

        // do not map again if you found what you want (return statement)
        System.out.println("\nMAP ONLY ONCE EXAMPLE:");
        mapOnlyOnce();

        // firstly sorting is performed for all elements, so be aware if you want this
        System.out.println("\nSORT DEEPLY EXAMPLE:");
        sortingDeeply();

        // sorted is never been called because filter reduces the input collection to just one element
        System.out.println("\nNO SORT EXECUPTED EXAMPLE:");
        noSortExecuted();

        // only once you can use terminate operation, although you can use Supplier Interface
        System.out.println("\nREUSED STREAM EXAMPLE:");
        reusedStream();
    }


    static void basicExample(){
        List<String> myList = Arrays.asList("a1", "a2", "b1", "c1", "c2");

        myList
                .stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
    }

    static void noStreamMethod(){
        Stream.of("a1", "a2", "a3").findFirst().ifPresent(System.out::println);
    }

    static void specialStream(){
        //then use IntFunction, IntPredicate etc
        IntStream.range(1, 4).forEach(System.out::println);
    }

    static void arrayToStream(){
        Arrays.stream(new int[] {1, 2, 3})
                .map(n-> 2*n + 1)
                .average()
                .ifPresent(System.out::println);
    }

    static void stringToInt(){
        // transform the objects of a stream into another type of objects by utilizing the map operation
        Stream.of("a1", "a2", "a3")
                .map(s->s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(System.out::println);
    }

    static void doubleToInt(){
        //mapToObj() = Returns an object-valued Stream consisting of the results of applying the given function
        // to the elements of this stream ex. if (i -> "a" + i) then StringStream, if (i -> 1 + i) then InStream
        Stream.of(1.0, 2.0, 3.0)
                .mapToInt(Double::intValue)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);
    }

    static void anyMatchUsage(){
        //The operation anyMatch returns true as soon as the predicate applies to the given input element.
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });
    }

    static void mapOnlyOnce(){
        //Keep that in mind when composing complex method chains
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));

        //output:
            // filter:  d2
            // filter:  a2
            // map:     a2
            // forEach: A2
            // filter:  b1
            // filter:  b3
            // filter:  c
    }

    static void sortingDeeply(){
        Stream.of("d2", "a2", "b1", "b3", "c")
                .sorted((s1, s2) -> {
                    System.out.printf("sort: %s; %s\n", s1, s2);
                    return s1.compareTo(s2);
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));

        //output: eight times sort (a2; d2), (b1; a2) ... , filter: a2, map: s2,
            // then foreach: A2 (because foreach is terminal operation), filter b1, b3, c, d2

    }

    static void noSortExecuted(){
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .sorted((s1, s2) -> {
                    System.out.printf("sort: %s; %s\n", s1, s2);
                    return s1.compareTo(s2);
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));

        //output: 5 times filter from d2 to c, then map: a2, then forEach A2, so no sorted!!!
    }

    static void reusedStream(){
    //Supplier gives possibilities to use method "get"
        Supplier<Stream<String>> streamSupplier = () -> Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> s.startsWith("a"));

        streamSupplier.get().anyMatch(s -> true);
        streamSupplier.get().noneMatch(s -> true);
    }




}
