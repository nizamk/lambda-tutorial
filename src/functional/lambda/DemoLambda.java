package functional.lambda;

import functional.lambda.pattern.Resource;
import functional.utilities.AbstractDemo;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/**
 * Created by nizamuddin on 06/05/2015.
 */
public class DemoLambda extends AbstractDemo {
	List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

	@Override
	public void runNonFunctional() {
		super.runNonFunctional();
//		internalIteration();
		boilerplate();
	}

	@Override
	public void runFunctional() {
		super.runFunctional();
//		externalIteration();
//		deferredExecution();
		executeAroundMethod();
	}

	public void boilerplate() {
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

	public void executeAroundMethod() {
		withResource( t -> {
			t.use();
			System.out.println("use resource to access data");
		});
	}

	/**
	 * The Lender(withResource() - holding the resource) loans resource to
	 * lendee (the Consumer lambda accessing it)
	 */
	private void withResource(Consumer<Resource> consumer) {
		Resource resource = new Resource();
		try {
			consumer.accept(resource);
		} finally {
			resource.dispose();
		}
	}

	public void deferredExecution() {
		IntConsumer run = i -> System.out.println("Print: " + i);
		repeat(10, run);
	}

	private void repeat(int i, IntConsumer run) {
		for (int j = 0; j < i; j++) {
			run.accept(j);
		}
	}

}
