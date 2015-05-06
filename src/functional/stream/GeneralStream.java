package functional.stream;

import functional.utilities.AbstractDemo;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by nizamuddin on 13/04/2015.
 */
public class GeneralStream extends AbstractDemo {

	@Override
	public void runFunctional() {
		super.runFunctional();
	}

	@Override
	public void runNonFunctional() {
		super.runNonFunctional();
		demo();
	}
	private void demo() {
		
	}
	
	public static void main(String[] args) {
		List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
		// sum of all even numbers
		int sum = 0;
		for (Integer value : values) {
			if (value > 3 && value % 2 == 0)
				sum += value;
		}
		System.out.println("sum: " + sum);

		// functional way
		Integer result = values.stream()
				.filter( s-> s > 3)
				.filter( s-> s % 2 == 0)
				.reduce((a,b) -> a+b)
				.get();
		System.out.println(result);
		// concatenate all string

		/*
		List<String> values = Arrays.asList("a1", "a2", "b1","b2","c1","c2");

		values.stream()
				.filter(a->a.startsWith("a"))
				.map(a -> a.toUpperCase())
				.sorted()
				.forEach(System.out::println);

		Arrays.asList("a1","a2")
				.stream()
				.findFirst()
				.ifPresent(a -> System.out.println(a));


		// laziness
//		values.stream()
//				.filter(s -> {
//					System.out.println("filter: " + s);
//					return true;
//				})
//				.forEach(s -> System.out.println(s));

		List<Person> persons = Arrays.asList(
				new Person("ali", 5),
				new Person("beruni", 10),
				new Person("ani", 15),
				new Person("ben", 20));

		List<Person> list = persons.stream()
				.filter(a -> a.name.startsWith("a"))
				.collect(Collectors.toList());

		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

		// function is polymorphic
		String x = numbers.stream()
				.reduce("", (result, e) -> {
					System.out.format("accumulator result=%s, e=%s\n", result,e);
					return result = result + e;
				}, (sum1, sum2) -> {
					System.out.format("combiner sum1=%s, sum2=%s\n");
					return sum1 + sum2;
				});

		System.out.println(x);
		Integer y = numbers.parallelStream()
				.reduce(0, (result, e) -> {
					System.out.format("accumulator result=%s, e=%s\n", result,e);
					return result = result + e;
				}, (sum1, sum2) -> {
					System.out.format("combiner sum1=%s, sum2=%s\n", sum1, sum2);
					return sum1 + sum2;
				});

		System.out.println(y);
*/
	}

	public static void streamDemo() {
		List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
		int result = 0;
		for (Integer value : values) {
			if (value > 3 && value % 2 == 0) {
				System.out.println("processing: " + value);
				result = value * 2;
				break;
			}
		}
		System.out.println("result: " + result);

		// a function returning another function
		Function<Integer, Predicate<Integer>> greaterThan = pivot -> e -> e > pivot;

		System.out.println(
				values.stream()
						.filter(greaterThan.apply(4))
						.filter(e -> e % 2 == 0)
						.map(e -> e * 2)
						.findFirst());

	}
}

