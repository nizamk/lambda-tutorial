package functional.lambda;

import functional.utilities.AbstractDemo;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

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

		Consumer<Integer> cs = t -> System.out.print(t);

		// composable
		Consumer<Integer> cs1 = cs.andThen(t -> System.out.print(","));

		Predicate<Integer> greaterThan3 = t -> t > 3;
		Predicate<Integer> isEven = t -> t % 2 == 0;

		// higher-order function - function returning function
		Function<Integer, Predicate<Integer>> isGreaterThan = pivot -> t -> t > pivot;

		// composable
		Predicate<Integer> isEvenAndGreaterThan= isEven.and(isGreaterThan.apply(4));

		list.stream()
				.filter(isEvenAndGreaterThan)
				.forEach(cs1);

	}


}
