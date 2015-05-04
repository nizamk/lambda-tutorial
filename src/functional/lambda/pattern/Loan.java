package functional.lambda.pattern;

import functional.utilities.AbstractDemo;

import java.util.function.Consumer;

/**
 * Created by nizamuddin on 17/04/2015.
 */
public class Loan extends AbstractDemo {
	@Override
	public void runNonFunctional() {
		super.runNonFunctional();

		/*
		This boilerplate code is duplicated whenever
		usage of resource is required
		 */
		Resource resource = new Resource();
		try {
			resource.use();
			System.out.println("use resource to access data");
		} finally {
			resource.dispose();
		}
	}

	@Override
	public void runFunctional() {
		super.runFunctional();
		withResource( t -> {
			t.use();
			System.out.println("use resource to access data");
		});
	}

	/**
	 * The Lender(withResource() - holding the resource) loans resource to
	 * lendee (the Consumer lambda accessing it)
	 */
	public void withResource(Consumer<Resource> consumer) {
		Resource resource = new Resource();
		try {
			consumer.accept(resource);
		} finally {
			resource.dispose();
		}
	}

}
