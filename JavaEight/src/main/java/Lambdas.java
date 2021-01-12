import java.util.*;

public class Lambdas {

    public static void main(String[] args) {
        //1
        intToStringConverter();
    }

    //1. Accessing local variables
    static void intToStringConverter(){
        int num = 2;
        Converter<Integer, String> stringConverter = (from -> String.valueOf(from + num));
        String value = stringConverter.convert(6);
        System.out.println(value);
    }

}
