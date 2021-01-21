import java.util.function.BiFunction;
import java.util.function.Function;

public class Lambdas {

    public static void main(String[] args) {

        //1
        Function<String, String> name1 = name -> name.toUpperCase(); //immediately operation
        //or the same:
        Function<String, String> name2 = String::toUpperCase; //immediately operation

        //2
        Function<String, String> name3 = name ->{
            //logic
            if(name.isBlank()) throw new IllegalArgumentException("");
            return name.toUpperCase();
        };

        //3
        //Input, Input, Output
        BiFunction<String, Integer, String> name4 = (name, age) ->{
            //logic
            if(name.isBlank()) throw new IllegalArgumentException("");
            return name.toUpperCase() + " is " + age.toString();
        };

        //age can be equal to null, because in functions we don't use "primitives" as "int", but instead we use "Integer"
        System.out.println(name4.apply("Alex", 30));
    }

}
