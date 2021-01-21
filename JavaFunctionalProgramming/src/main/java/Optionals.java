import java.util.Optional;

public class Optionals {

    public static void main(String[] args) {

        //1
        Object objectValue = Optional
                .ofNullable("Hello")
                .orElseThrow(() -> new IllegalStateException("exception")); //example of supplier

        System.out.println(objectValue);


        //2
        Optional
                .ofNullable(null)
                .ifPresentOrElse(email ->
                                System.out.println("Sanding email to " + email),
                                () -> {
                                    System.out.println("cannot sand email");
                                }
                        );

        //3
        Optional<String> hello = Optional.ofNullable(null); //.of(null) = error
        System.out.println(hello.isEmpty());

        String orElse = hello.map(String::toUpperCase).orElse("Hello is not presented so I'm here");
        System.out.println("orElse case: " + orElse);

        //4 orElseGet = else + business logic
        String orElseGet = hello
                .map(String::toUpperCase)
                .orElseGet(() -> {
                    //put some logic with an extra computation to retrieve value
                    return "Hi";
                });
        System.out.println("If hello is not in the database then give me: " + orElseGet);

        //5
        Person person = new Person("john", null);
        //System.out.println(person.getEmail().map(String::toLowerCase).orElse("email is not provided"));

        if(person.getEmail().isPresent()){
            String email = person.getEmail().get(); //.get() method to get String from Optional<String>
            System.out.println(email.toLowerCase());
        }else{
            System.out.println("email is not provided");
        }
    }


    static class Person{

        private final String name;
        private final String email;

        Person(String name, String email) {
            this.name = name;
            this.email = email;
        }

        //getter
        public String getName() {
            return name;
        }

        public Optional<String> getEmail(){
            return Optional.ofNullable(email);
        }
    }


}
