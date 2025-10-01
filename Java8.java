import java.util.Comparator;
import java.util.List;

public class Java8 {

    public static void main(String[] args) {
        findLongestStr();
        calAvgAge();
    }

    // Find the longest string in a list of strings using Java streams
    private static void findLongestStr() {

        System.out.println("Find the longest string in a list of strings using Java streams");

        List<String> values = List.of("santosh", "rohan", "dilip", "ram", "deva");
        System.out.println(values.stream().max(Comparator.comparingInt(String::length)).get());
    }

    // Calculate the average age of a list of Person objects using Java streams
    private static void calAvgAge() {

        System.out.println("\nCalculate the average age of a list of Person objects using Java streams");

        List<Person> personList = List.of(
                new Person("santosh", 32),
                new Person("rohan", 4),
                new Person("dilip", 33),
                new Person("ram", 34),
                new Person("deva", 10)
        );

        System.out.println(personList.stream().mapToInt(Person::age).average().orElse(0));
    }

    // Check if a list of integers contains a prime number using Java streams

}

record Person(String name, int age) {}
