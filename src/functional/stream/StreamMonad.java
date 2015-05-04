package functional.stream;

import functional.utilities.AbstractDemo;

import java.util.Arrays;
import java.util.List;

/**
 * Created by nizamuddin on 22/04/2015.
 */
public class StreamMonad extends AbstractDemo {
	List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
	List<String> strings = Arrays.asList("h", "e", "l", "l", "o");

	@Override
	public void runFunctional() {
		super.runFunctional();

		System.out.println(list.stream()
				.filter(t -> t % 2 == 0)
				.filter(t -> t > 8)
				.findFirst().orElse(-1));
	}

	@Override
	public void runNonFunctional() {
		super.runNonFunctional();

		// logic
		// iteration
		// mixed?? HOW + WHAT
		for (Integer integer : list) {
			if (integer % 2 == 0) {
				int n2 = integer * 2;
				if (n2 > 7) {
					System.out.println(n2);
					break;
				}
			}
		}


	}
}
