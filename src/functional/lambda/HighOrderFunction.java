package functional.lambda;

import functional.utilities.AbstractDemo;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * Created by nizamuddin on 27/04/2015.
 */
public class HighOrderFunction extends AbstractDemo{
	List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);

	@Override
	public void runNonFunctional() {
	}

	@Override
	public void runFunctional() {
		super.runFunctional();

		// Consumer<T> - void accept(T t)
		Consumer<Integer> cs = t -> System.out.println("Consumer<T>: " + t);
		cs.accept(100);

		// Supplier<T> - T get(void)
		Supplier<String> sp = () -> "Hello";
		System.out.println("Supplier<T>: " + sp.get());

		// Predicate<T> - Boolean test(T t)
		Predicate<Integer> isEven = t -> t % 2 == 0;
		System.out.println("Predicate<T>: " + isEven.test(7));

		// higher-order function - function returning function
		Function<Integer, Predicate<Integer>> isGreaterThan = pivot -> i -> i > pivot;
		System.out.println("Function<T,R>: " + isGreaterThan.apply(4).test(7));

		BiFunction<Integer, Integer, Predicate<Integer>> bounds =
				(lower, upper) -> t -> t >= lower && t <= upper;

		BiFunction<Integer, Integer, Predicate<Integer>> isEvenAndBounds =
				(lower, upper) -> isEven.and(bounds.apply(lower, upper));

		System.out.println("Bounds: " + bounds.apply(2, 6).test(7));
		System.out.println(list.stream()
				.filter(isEvenAndBounds.apply(3,6))
				.map(t -> t * 2)
				.findFirst());



		// composable
		Predicate<Integer> isEvenAndGreaterThan= isEven.and(isGreaterThan.apply(4));
		Consumer<Integer> cs1 = cs.andThen(t -> System.out.print(","));



	}


}
