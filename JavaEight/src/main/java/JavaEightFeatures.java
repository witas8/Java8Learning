import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class JavaEightFeatures {
    public static void main(String[] args) {
        //1
        //List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        //Lambdas.oldSorter(names);
        //Lambdas.lambdaSorter(names);

        //2
        //interfaceUsage();

        //3
        //startsWithMethodUsage();

        //4
        referenceConstructorUsage();
    }

    //1. Checking differences between Java and Java 8
    public static void oldSorter(List<String> namesList){
        Collections.sort(namesList, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println(namesList);
    }

    public static void lambdaSorter(List<String> namesList){
        //1) bad, long lambda:
        //skip the return statement
        /*Collections.sort(namesList, (String a, String b) -> {
            return b.compareTo(a);
        });*/

        //2 short:
        Collections.sort(namesList, (String a, String b) -> b.compareTo(a));

        //3) the shortest:
        //The java compiler is aware of the parameter types
        Collections.sort(namesList, (a,b) -> b.compareTo(a));
    }


    //2. Usage of Functional Interfaces
    public static void interfaceUsage(){
        Converter<String, Integer> converter = (from -> Integer.valueOf(from));
        //or simplified method by utilizing static method references
        //Converter<String, Integer> converter = Integer::valueOf;
        Integer converted = converter.convert("123");
        System.out.println(converted);
    }


    //3. Passing references of methods via the :: keyword
    static class Something{
        static String startsWith(String s) {
            return String.valueOf(s.charAt(0));
        }
    }

    static void startsWithMethodUsage(){
        Converter<String, String> converter = JavaEightFeatures.Something::startsWith;
        String converted = converter.convert("Java");
        System.out.println(converted);
    }


    //4. Passing references of constructors via the :: keyword
    static void referenceConstructorUsage(){
        PersonFactory<Person> personFactory = Person::new; //create a reference to the Person constructor via Person::new
        Person person = personFactory.create("Mikołaj", "Witkowski");
        System.out.println("Name: " + person.firstName + "\n"+ "Surname: " + person.lastName);
    }
}
