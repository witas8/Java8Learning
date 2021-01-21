package Streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ParallelStreams {
    // Streams can be executed in parallel to increase runtime performance on large amount of input elements
    // The size of the underlying thread-pool uses up to five threads - depending on the amount of available physical CPU cores
    // Parallel streams can bring be a nice performance boost to streams with a large amount of input elements

    // This value can be decreased or increased by setting the following JVM parameter:
    // -Djava.util.concurrent.ForkJoinPool.common.parallelism=5

    //ex:
    //ForkJoinPool commonPool = ForkJoinPool.commonPool();
    //System.out.println(commonPool.getParallelism());    // 3


    public static void main(String[] args) {

        sequentialSort(); //by using stream
        parallelSort(); //by using parallelStream

        List<Person> persons = Arrays.asList(
                new Person("Max", 18),
                new Person("Peter", 23),
                new Person("Pamela", 23),
                new Person("David", 12));

        persons
                .parallelStream()
                .reduce(
                        0,
                        (sum, p) -> {
                            System.out.format("accumulator: sum=%s; person=%s [%s]\n", sum, p, Thread.currentThread().getName());
                            return sum += p.age;
                        },
                        (sum1, sum2) -> {
                            System.out.format("combiner: sum1=%s; sum2=%s [%s]\n", sum1, sum2, Thread.currentThread().getName());
                            return sum1 + sum2;
                        });

        // The console output reveals that both the accumulator and the combiner functions are executed in parallel on all available thread
    }


    static void sequentialSort(){
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
        long t0 = System.nanoTime();

        long count = values.stream().sorted().count();
        System.out.println(count);

        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));
    }

    static void parallelSort(){
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        long t0 = System.nanoTime();

        long count = values.parallelStream().sorted().count();
        System.out.println(count);

        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parallel sort took: %d ms", millis));
    }


}
