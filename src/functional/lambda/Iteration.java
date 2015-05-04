package functional.lambda;

import functional.utilities.AbstractDemo;

import java.util.Arrays;
import java.util.List;

/**
 * Created by nizamuddin on 27/04/2015.
 */
public class Iteration extends AbstractDemo {
	List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

	@Override
	public void runNonFunctional() {
		super.runNonFunctional();
		internalIteration();
	}
	@Override
	public void runFunctional() {
		super.runFunctional();
		externalIteration();
	}

	public void internalIteration() {
		for (Integer integer : list) {
			if(integer % 2 == 0)
				System.out.println(integer);
		}
	}

	public void externalIteration() {
		list.stream()
				.filter(t -> t % 2 == 0)
				.forEach(System.out::println);
	}
}
