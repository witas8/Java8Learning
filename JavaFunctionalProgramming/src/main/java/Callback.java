import java.util.function.Consumer;

public class Callback {

    //similar to Java Script
    public static void main(String[] args) {

        //pass function as a parameter to the method
        hello("John", null, value -> {
            System.out.println("Last name is not provided for " + value); //value!
        });

        hello2("John", null, () -> {
            System.out.println("Last name is not provided!"); //if we do not take any value
        });

    }

    static void hello(String firstName, String lastName, Consumer<String> callback){
        System.out.println(firstName);
        if(lastName != null){
            System.out.println(lastName);
        } else{
            callback.accept(firstName); //value!
        }
    }

    static void hello2(String firstName, String lastName, Runnable callback){
        System.out.println(firstName);
        if(lastName != null){
            System.out.println(lastName);
        } else{
            callback.run();
        }
    }


}
