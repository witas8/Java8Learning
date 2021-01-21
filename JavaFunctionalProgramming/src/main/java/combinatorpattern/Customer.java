package combinatorpattern;

import java.time.LocalDate;

public class Customer {

    private final String name;
    private final String email;
    private final String phoneNumber;
    private final LocalDate localDate;

    public Customer(String name, String email, String phoneNumber, LocalDate localDate) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.localDate = localDate;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }
}
