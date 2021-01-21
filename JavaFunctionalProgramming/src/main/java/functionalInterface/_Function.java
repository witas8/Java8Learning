package functionalInterface;

import java.util.function.BiFunction;
import java.util.function.Function;

public class _Function {

    //it takes input and produce output (as function suppose to)
    public static void main(String[] args) {
        //Function:
        //produces the same output as incrementByOne method, but in another, faster way
        int increment = incrementByOneFunction.apply(2); //apply pass the argument
        System.out.println(increment);
        //apply !!
        Function<Integer, Integer> addThenMultiply = incrementByOneFunction.andThen(multiplyBy10Function);
        System.out.println(addThenMultiply.apply(5));

        //----------------------------------

        //BiFunction:
        //function that takes 2 input arguments and produces 1 output
        System.out.println(incrementByOneAndMultiplyBiFunction.apply(4, 100));
    }

    //Functions example:
    static int incrementByOne(int number){
        return number + 1;
    }

    //input, output of the function called Function
    static Function<Integer, Integer> incrementByOneFunction = number -> number++;

    static Function<Integer, Integer> multiplyBy10Function = number -> number * 10;

    //---------------------------

    //BiFunction example:
    static int incrementByOneAndMultiply(int number, int numToMultiplyBy){
        return (number + 1) * numToMultiplyBy;
    }

    static BiFunction<Integer, Integer, Integer> incrementByOneAndMultiplyBiFunction =
            (numberToIncrementByOne, numberToMultiplyBy) -> (numberToIncrementByOne + 1) * numberToMultiplyBy;

}
