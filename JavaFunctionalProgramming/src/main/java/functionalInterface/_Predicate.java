package functionalInterface;

import java.util.function.Predicate;

public class _Predicate {

    //is a boolean-valued function that can be useful for validation (thee is also BiPredicate case)
    public static void main(String[] args) {
        System.out.println(isPhoneNumberValidationPredicate.test("07000000000"));
        System.out.println(isContains3.test("0000022211"));
        System.out.println(isPhoneNumberValidationPredicate.and(isContains3).test("07000000300"));

    }

    static boolean isPhoneNumberValidation (String phoneNumber){
        return phoneNumber.startsWith("07") && phoneNumber.length() == 11;
    }

    //predicate:
    static Predicate<String> isPhoneNumberValidationPredicate = phoneNumber ->
            phoneNumber.startsWith("07") && phoneNumber.length() == 11;

    static Predicate<String> isContains3 = phoneNumber ->
            phoneNumber.contains("3");


}
