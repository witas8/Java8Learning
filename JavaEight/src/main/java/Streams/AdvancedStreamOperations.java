package Streams;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AdvancedStreamOperations {

    public static void main(String[] args) {

        List<Person> people = Arrays.asList(
                        new Person("Max", 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 23),
                        new Person("David", 12));

        // construct a list from the elements of a stream
        System.out.println("personFiltered EXAMPLE:");
        personFiltered(people);

        // groups all persons by age
        System.out.println("groupPeople EXAMPLE:");
        groupPeople(people);

        // Collectors method math options as average and numbers summary
        System.out.println("mathCollectorOperation EXAMPLE:");
        mathCollectorOperation(people);

        // four ingredients of a collector: supplier, an accumulator, a combiner and a finisher
        System.out.println("allCollectorCombination EXAMPLE:");
        allCollectorCombination(people);

        // if we want to transform one object into multiple others or none at all then use flatMap
        System.out.println("flatMapper EXAMPLE:");
        flatMapper();

        // code example can be simplified into a single pipeline of stream operations
        System.out.println("fasterFlatMapper EXAMPLE:");
        fasterFlatMapper();

        // the first reducer is reducing a stream of elements to exactly one element of the stream
        System.out.println("reduceToOneElement EXAMPLE:");
        reduceToOneElement(people);

        // construct a new Person with the aggregated names and ages from all other persons in the stream
        System.out.println("aggregateNamesAges EXAMPLE:");
        aggregateNamesAges(people);

        // add age of all people in the list
        System.out.println("ageSum EXAMPLE:");
        ageSum(people);
    }

    static void personFiltered(List<Person> personList){
        List<Person> filtered = personList
                .stream()
                .filter(p -> p.name.startsWith("P"))
                .collect(Collectors.toList());
        System.out.println(filtered); //[Peter, Pamela]
    }

    static void groupPeople(List<Person> personList){
        Map<Integer, List<Person>> personByAge = personList.stream().collect(Collectors.groupingBy(p -> p.age));

        personByAge.forEach((age, p) -> System.out.format("age %s: %s\n", age, p));
    }

    static void mathCollectorOperation(List<Person> personList){
        Double averageAge = personList.stream().collect(Collectors.averagingInt(p -> p.age));
        System.out.println("Average: " + averageAge);

        IntSummaryStatistics ageSummary = personList.stream().collect(Collectors.summarizingInt(p -> p.age));
        System.out.println("Age summary: " + ageSummary); //{count=4, sum=76, min=12, average=19.000000, max=23}
    }

    static void allCollectorCombination(List<Person> personList){
        // creation of a new result container (supplier())
        // incorporating a new data element into a result container (accumulator())
        // combining two result containers into one (combiner())
        // performing an optional final transform on the container (finisher())

        // p = Person
        Collector<Person, StringJoiner, String> personNameCollector =
                Collector.of(
                        () -> new StringJoiner(" + "), //supplier (for a result)
                        (j, p) -> j.add(p.name.toUpperCase()), //accumulator (add each upper-cased name to the StringJoiner)
                        (j1, j2) -> j1.merge(j2), //combiner (merge two StringJoiners into one)
                        StringJoiner::toString); //finisher (terminal operation, constructs the desired String from the StringJoiner)

        String names = personList.stream().collect(personNameCollector);
        System.out.println(names); // MAX + PETER + PAMELA + DAVID
    }


    //FLATMAP
    // FlatMap transforms each element of the stream into a stream of other objects
    static class Foo {
        String name;
        List<Bar> bars = new ArrayList<>();

        Foo(String name) {
            this.name = name;
        }
    }

    static class Bar {
        String name;

        Bar(String name) {
            this.name = name;
        }
    }
    static void flatMapper(){
        List<Foo> foos = new ArrayList<>();

        //create foos:
        IntStream.range(1, 4).forEach(i -> foos.add(new Foo("Foo" + i))); //result is string "Foo1", "Foo2"

        //create bars
        foos.forEach(foo ->
                IntStream
                        .range(1, 4)
                        .forEach(i -> foo.bars.add(new Bar("Bar" + i + "<- " + foo.name))));
        // Now we have a list of three foos each consisting of three bars.


        foos.stream()
                .flatMap(f -> f.bars.stream())
                .forEach(b -> System.out.println(b.name));
        //output - Bar1 <- Foo1, Bar2 <- Foo1, Bar3 <- Foo1 (and the same for Foo2 and Foo3)
        // so we have successfully transformed the stream of three foo objects into a stream of nine bar objects.
    }

    static void fasterFlatMapper(){
        IntStream.range(1, 4)
                .mapToObj(i -> new Foo("Foo" + i)) //kind of for loop
                .peek(f -> IntStream.range(1, 4)
                        .mapToObj(i -> new Bar("Bar" + i + "<- " + f.name))
                        .forEach(f.bars::add) //add method from ArrayList class
                    )
                .flatMap(f -> f.bars.stream())
                .forEach(b -> System.out.println(b.name));
    }


    //REDUCE
    // reduction operation combines all elements of the stream into a single result
    static void reduceToOneElement(List<Person> personList){
        personList
                .stream()
                .reduce((p1, p2) -> p1.age > p2.age ? p1 : p2)
                .ifPresent(System.out::println); // output: Pamela
    }

    static void aggregateNamesAges(List<Person> personList){
        // The second reduce method accepts both an identity value and a BinaryOperator accumulator.
        Person result = personList.stream()
                .reduce(new Person("", 0), (p1, p2) -> {
                    p1.age += p2.age;
                    p1.name += p2.name;
                    return p1; //p1 is a person that consist p1's age and p1'vales, where the computation were accumulated
        });

        System.out.format("name=%s; age=%s", result.name, result.age);
        //name=MaxPeterPamelaDavid; age=76
    }

    static void ageSum(List<Person> personList){
        //The third reduce method accepts three parameters:
        // an identity value, a BiFunction accumulator and a combiner function of type BinaryOperator.

        //identity – the identity value for the combiner function
        //accumulator – an associative, non-interfering, stateless function for incorporating an additional element into a result
        //combiner – an associative, non-interfering, stateless function for combining two values
        Integer ageSum = personList.stream().reduce(
                    0, (sum, p) -> sum += p.age, (sum1, sum2) -> sum1 + sum2
                );

        System.out.println("the age sum is: " + ageSum); //76
    }

}
