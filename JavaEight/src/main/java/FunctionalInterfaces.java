import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaces {

    public static void main(String[] args) {
        FunctionalInterfaces fi = new FunctionalInterfaces();
        fi.predictorsExample();
        fi.functionsExample();
        fi.suppliersExample();
        fi.consumerExample();
        fi.comparatorExample();
        fi.optionalExample();
    }

    // 1. Predicates
    // Predicates are boolean-valued functions of one argument.
    // The interface contains various default methods for composing predicates to complex logical terms (and, or, negate)
    void predictorsExample(){
        Predicate<String> predicate = s -> s.length() > 0;
        predicate.test("Czy Dziala?"); //true
        predicate.negate().test("Czy dziala?"); //false

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
    }


    // 2. Functions
    // Functions accept one argument and produce a result.
    // Default methods can be used to chain multiple functions together (compose, andThen)
    void functionsExample(){
        Function<String, Integer> myFuncToInteger = Integer::valueOf;
        Function<String, String> backToString = myFuncToInteger.andThen(String::valueOf);

        backToString.apply("123");
    }


    // 3. Suppliers
    // Suppliers produce a result of a given generic type. Unlike Functions, Suppliers don't accept arguments
    // and can be used as the assignment target for a lambda expression or method reference
    void suppliersExample(){
        Supplier<Person> personSupplier = Person::new;
        personSupplier.get(); // get method !!!
    }


    // 4. Consumers
    // Consumers represents operations to be performed on a single input argument.
    void consumerExample(){
        Consumer<Person> welcomer = (person) -> System.out.println("Hello, " + person.firstName);
        welcomer.accept(new Person("Mikołaj", "Witkowski"));
    }


    // 5. Comparators
    // Comparators are well known from older versions of Java. Java 8 adds various default methods to the interface.
    void comparatorExample(){
        Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);
        Person p1 = new Person("Mikołaj", "Witkowski");
        Person p2 = new Person("Michael", "Jordan");

        comparator.compare(p1, p2);            // > 0
        comparator.reversed().compare(p1, p2); // < 0
    }


    // 6. Optionals
    // Optional is a simple container for a value which may be null or non-null. Think of a method which may return
    // a non-null result but sometimes return nothing. Instead of returning null you return an Optional in Java 8.
    void optionalExample(){
        Optional<String> optional = Optional.of("java");

        optional.isPresent(); //true
        optional.get(); //java
        optional.orElse("tyryryry"); //java

        optional.ifPresent((s) -> System.out.println(s.charAt(0))); //j
    }

}
