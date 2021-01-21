import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DeclarativeApproach {

    public static void main(String[] args) {
        List<Person> people = List.of(
                new Person("John", Gender.MALE),
                new Person("Maria", Gender.FEMALE),
                new Person("Aisha", Gender.FEMALE),
                new Person("Alex", Gender.MALE),
                new Person("Alice", Gender.FEMALE)
        );

        declarativeProgrammingExample(people);
    }

    static class Person{
        private final String name;
        private final Gender gender;

        Person(String name, Gender gender){
            this.name = name;
            this.gender = gender;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", gender=" + gender +
                    '}';
        }
    }

    enum Gender {
        MALE, FEMALE;
    }

    static void declarativeProgrammingExample(List<Person> people){
        System.out.println("Just print the filtered list");
        people
                .stream()
                .filter(person -> Gender.FEMALE.equals(person.gender))
                .forEach(System.out::println);

        System.out.println("\nStore the filtered list in a variable");
        List<Person> myCollection = people
                                        .stream()
                                        .filter(person -> Gender.FEMALE.equals(person.gender))
                                        .collect(Collectors.toList());
        System.out.println(myCollection);

        //Predicate Interface (kind of Functional Interface) that can be passed to the filter method
        Predicate<Person> personPredicate = person -> Gender.FEMALE.equals(person.gender);

        System.out.println("\nOr print the collector this same way as is just printing case:");
        myCollection.forEach(System.out::println);

    }

}