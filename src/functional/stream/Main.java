package functional.stream;

import functional.utilities.AbstractDemo;

import java.util.Arrays;
import java.util.List;

/**
 * Created by nizamuddin on 22/04/2015.
 */
public class Main extends AbstractDemo {

	List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
	List<String> strings = Arrays.asList("h","e","l","l","o");

	@Override
	public void runFunctional() {
		super.runFunctional();

		System.out.println(list.stream()
				.map(e -> e.toString())
				.reduce("", (acc, e) -> acc + e));
	}

	@Override
	public void runNonFunctional() {
		super.runNonFunctional();
	}
}
