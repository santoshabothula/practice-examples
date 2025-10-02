import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8 {

    public static void main(String[] args) {
        findLongestStr();
        calAvgAge();
        checkPrimeNumber();
        mergeSortedLists();
        findIntersection();
        removeDuplicates();
        sumOfAmtPerDay();
        findSmallest();
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
    private static void checkPrimeNumber() {

        System.out.println("\nCheck if a list of integers contains a prime number using Java streams");

        List<Integer> numbers = List.of(10, 11);

        System.out.println(numbers.stream().anyMatch(Java8::isPrimeNum));
    }

    // Merge two sorted lists into a single sorted list using Java streams
    private static void mergeSortedLists() {

        System.out.println("\nMerge two sorted lists into a single sorted list using Java streams");

        List<Integer> list1 = Arrays.asList(5,6,8,10);
        List<Integer> list2 = Arrays.asList(1,3,8,9);

        Stream.concat(list1.stream(), list2.stream()).sorted().forEach(System.out::print);
    }

    // Find the intersection of two lists using Java streams
    private static void findIntersection() {

        /*
            Intersection:
                List1 = 5,6,8,10
                List2 = 6,7,9,10
                o/p: 6,10
         */
        System.out.println("\n\nFind the intersection of two lists using Java streams");

        List<Integer> list1 = List.of(5,6,8,10);
        List<Integer> list2 = List.of(6,7,9,10);

        list1.stream().filter(list2::contains).toList().forEach(System.out::println);

        // Alternative
        Set<Integer> s1 = new HashSet<>(list1);
        Set<Integer> s2 = new HashSet<>(list2);
        Set<Integer> intersectionSet = new HashSet<>(s1);
        intersectionSet.retainAll(s2);
        intersectionSet.forEach(System.out::println);
    }

    // Remove duplicates from a list while preserving the order using Java streams
    private static void removeDuplicates() {

        System.out.println("\nRemove duplicates from a list while preserving the order using Java streams");
        List<Integer> list1 = List.of(5,6,8,10,6,7,9,10);

        list1.stream().distinct().toList().forEach(System.out::println);
        System.out.println();

        // Alternative - better performance
        Set<Integer> set = new LinkedHashSet<>(list1);
        set.forEach(System.out::println);
    }

    // Given a list of transactions, find the sum of transaction amounts for each day using Java streams
    private static void sumOfAmtPerDay() {

        System.out.println("\nGiven a list of transactions, find the sum of transaction amounts for each day using Java streams");
        List<Transaction> transactions = List.of(
                new Transaction("2022-01-01", 100),
                new Transaction("2022-01-01", 200),
                new Transaction("2022-01-02", 300),
                new Transaction("2022-01-02", 400),
                new Transaction("2022-01-03", 500)
        );

        transactions.stream()
                .collect(Collectors.groupingBy(Transaction::date, Collectors.summingInt(Transaction::amount)))
                .forEach((key, value) -> System.out.println(key + " " + value));
    }

    // Find the kth smallest element in an array using Java streams
    private static void findSmallest() {

        int k = 0;
        List<Integer> list1 = List.of(5,6,8,10,6,7,9,10);
        System.out.println(list1.stream().sorted().toList().get(k));

        // Alternative
        System.out.println(list1.stream().sorted().skip(k).findFirst().orElse(-1));
    }

    private static boolean isPrimeNum(int n) {

        for (int i=2; i<=n/2; i++) {
            if (n % i == 0) {
                return true;
            }
        }

        return false;
    }

}

record Person(String name, int age) {}
record Transaction(String date, int amount) {}