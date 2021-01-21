package combinatorpattern;

import java.time.LocalDate;

import static combinatorpattern.CustomerRegistrationValidator.*;

public class Main {

    //IF DATA IS NOT VALID (ANY ERROR APPEARS) THEN SIMPLY CHECK WHERE IS THE ERROR BY USING THE COMBINATORPATTERN!
    public static void main(String[] args) {
        Customer customer = new Customer(
                "Alice",
                "alice@gmail.com",
                "+48535325",
                LocalDate.of(2000, 1, 1));

        CustomerValidatorService validatorService = new CustomerValidatorService();
        //now if error then cannot simply check where the error appears
        System.out.println("imperative approach: " + validatorService.isAllCustomerValid(customer));


        //Using combinator pattern:
        ValidationResult result = isEmailValid()
                .and(isPhoneNumberValid())
                .and(isAdult())
                .apply(customer); // !!

        System.out.println("Declarative approach with using interface and lambdas: " + result);
//        if (result != ValidationResult.SUCCESS){
//            throw new IllegalStateException(result.name());
//        }


    }

}
