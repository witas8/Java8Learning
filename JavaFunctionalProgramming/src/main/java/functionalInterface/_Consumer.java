package functionalInterface;

import java.beans.Customizer;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class _Consumer {

    //The Consumer is an operation that accepts a single input argument and returns no result (void)
    public static void main(String[] args) {
        //imperative approach:
        Employer maria = new Employer("Maria", "123456");
        greetEmployer(new Employer("Maria", "123456"));

        //declarative approach (functional interface):
        greetEmployerConsumer.accept(maria);
        greetEmployerBiConsumer.accept(maria, false);
    }

    //non functional (imperative approach):
    static void greetEmployer(Employer employer){
        System.out.println("Hello " + employer.consumerName + ", your number is: " + employer.customerPhoneNumber);
    }

    //functional (declarative approach) = avoiding methods:
    static Consumer<Employer> greetEmployerConsumer = employer ->
            System.out.println("Hello " + employer.consumerName + ", your number is: " + employer.customerPhoneNumber);


    //BiConsumer
    static BiConsumer<Employer, Boolean> greetEmployerBiConsumer = (employer, showPhoneNumber) ->
            System.out.println("Hello " + employer.consumerName + ", your number is: "
                    + (showPhoneNumber ? employer.customerPhoneNumber : "*********"));

    //exemplary class
    static class Employer{
        private final String consumerName;
        private final String customerPhoneNumber;

        Employer(String consumerName, String customerPhoneNumber) {
            this.consumerName = consumerName;
            this.customerPhoneNumber = customerPhoneNumber;
        }
    }
}
