package functional.lambda;

import functional.utilities.AbstractDemo;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * Created by nizamuddin on 06/05/2015.
 */
public class DemoHOF extends AbstractDemo {
	List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
	List<String> str = Arrays.asList("H", "E", "L", "L", "O");

	@Override
	public void runNonFunctional() {
	}

	@Override
	public void runFunctional() {
		super.runFunctional();
		runFunction();
		runHighOrderFunction();
	}

	private void runHighOrderFunction() {
		Predicate<Integer> isEven = t -> t % 2 == 0;

		// higher-order function - function returning function
		Function<Integer, Predicate<Integer>> isGreaterThan = pivot -> i -> i > pivot;
		System.out.println("Function<T,R>: " + isGreaterThan.apply(4).test(7));

		BiFunction<Integer, Integer, Predicate<Integer>> bounds =
				(lower, upper) -> t -> t >= lower && t <= upper;

		// functions are composable
		BiFunction<Integer, Integer, Predicate<Integer>> isEvenAndBounds =
				(lower, upper) -> isEven.and(bounds.apply(lower, upper));

		System.out.println("Bounds: " + bounds.apply(2, 6).test(7));

		System.out.println(list.stream()
				.filter(isEvenAndBounds.apply(8, 9))
				.map(t -> t * 2)
				.findFirst()
				.orElseGet(() -> -9999));

		// functions are polymorphic
		int sum = list.stream()
				.reduce(0, (acc, e) -> acc + e);
		System.out.println("Sum: " + sum);

		String s = str.stream()
				.reduce("", (acc, e) -> acc + e);
		System.out.println("Sum: " + s);
	}

	private void runFunction() {
		// Consumer<T> - void accept(T t)
		Consumer<Integer> cs = t -> System.out.println("Consumer<T>: " + t);
		cs.accept(100);

		Consumer<String> v = t -> System.out.println(t);
		v.accept("hai there");

		// Supplier<T> - T get(void)
		Supplier<String> sp = () -> "Hello";
		System.out.println("Supplier<T>: " + sp.get());

		// Predicate<T> - Boolean test(T t)
		Predicate<Integer> isEven = t -> t % 2 == 0;
		System.out.println("Predicate<T>: " + isEven.test(7));

		// BiFunction<T,R>
		BiFunction<Integer, Integer, Integer> fn = (x, y) -> x * y;
		System.out.println("BiFunction<T,R>: " + fn.apply(2, 5));
	}
}
