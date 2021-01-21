import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

public class Stream {

    public static void main(String[] args) {
        List<Person> people = List.of(
                new Person("John", Gender.MALE),
                new Person("Maria", Gender.FEMALE),
                new Person("Aisha", Gender.FEMALE),
                new Person("Alex", Gender.MALE),
                new Person("Aisha", Gender.FEMALE)
        );

        people
                .stream()
                .map(person -> person.name)
                .collect(Collectors.toSet()) //removes duplicates
                .forEach(System.out::println);

        //what is mentioned in my functionalInterface package
        Function<Person, String> personStringFunction = person -> person.name;
        ToIntFunction<String> length = String::length;
        IntConsumer println = System.out::println; // this same as: x -> System.out.println(x)
        people
                .stream()
                .map(personStringFunction) //crtl + alt + v to declare automatically variables
                .mapToInt(length) //name -> name.length()
                .forEach(println);


        //more on java streams - go deeper - more examples
        boolean containsOnlyFemales = people
                .stream()
                .allMatch(person -> Gender.FEMALE.equals(person.gender));// it is predicate

        System.out.println("Are there ONLY females?: " + containsOnlyFemales);


        boolean containsFemales = people
                .stream()
                .anyMatch(person -> Gender.FEMALE.equals(person.gender));// it is predicate

        System.out.println("Are there ANY female?: " + containsFemales);
        // to check if there is no X then use method .noneMatch
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

}
