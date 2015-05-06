package functional.lambda;

import functional.utilities.AbstractDemo;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.Predicate;

/**
 * Created by nizamuddin on 06/05/2015.
 */
public class DemoLambda extends AbstractDemo {
	List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

	@Override
	public void runNonFunctional() {
		super.runNonFunctional();
		internalIteration();
	}
	@Override
	public void runFunctional() {
		super.runFunctional();
//		externalIteration();
		deferredExecution();
	}

	private void deferredExecution() {
		IntConsumer run = i -> System.out.println("Print: " + i);
		repeat(10, run);
	}

	private void repeat(int i, IntConsumer run) {
		for (int j = 0; j < i; j++) {
			run.accept(j);
		}
	}

	public void internalIteration() {
		// find the 1st even number greater than 6 and double it
		int n = 0;
		for (Integer integer : list) {
			if (integer % 2 == 0)
				if (integer > 6) {
					n = integer * 2;
					break;
				}
		}
		System.out.println("The number: " + n);
	}

	public void externalIteration() {
		// abstraction through function
		Predicate<Integer> isEven = i -> i % 2 == 0;
		Predicate<Integer> isGreaterThan6 = i -> i > 6;
		Function<Integer, Predicate<Integer>> isGreaterThan = pivot -> i -> i > pivot;

		// function composition
		Function<Integer, Predicate<Integer>> isEvenAndGreaterThan =
				pivot -> isEven.and(isGreaterThan.apply(pivot));

		System.out.println(list.stream()
				.filter(isEvenAndGreaterThan.apply(6))
//				.filter(isGreaterThan.apply(8))
				.map(i -> i * 2)
				.findFirst());
	}
}
