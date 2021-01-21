package combinatorpattern;

import java.time.LocalDate;
import java.time.Period;

public class CustomerValidatorService {

    public boolean isCustomerEmailValid(String email){
        return email.contains("@");
    }

    public boolean isCustomerPhoneNumberValid(String phoneNumber){
        return phoneNumber.startsWith("+48");
    }

    public boolean isCustomerAdult(LocalDate localDate){
        return Period.between(localDate, LocalDate.now()).getYears() >= 18;
    }

    public boolean isAllCustomerValid(Customer customer){
        return isCustomerEmailValid(customer.getEmail())
                && isCustomerPhoneNumberValid(customer.getPhoneNumber())
                && isCustomerAdult(customer.getLocalDate());
    }

}
