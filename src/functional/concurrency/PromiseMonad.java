package functional.concurrency;

import functional.utilities.AbstractDemo;
import functional.utilities.MockedOperation;

import java.util.concurrent.CompletableFuture;

/**
 * Created by nizamuddin on 22/04/2015.
 */
public class PromiseMonad extends AbstractDemo {

	@Override
	public void runNonFunctional() {
		super.runNonFunctional();
	}

	@Override
	public void runFunctional() {
		super.runFunctional();
//		demo_Composition();
//		demo_FutureHandler();
		demo_Transformation();
	}

	/**
	 * Completable futures are composable
	 */
	public void demo_Composition() {
		System.out.println("Future Composition:");
		System.out.println("Thread ==> " + Thread.currentThread().getName() + " {Starting a non-blocking process..}");

		// Compose two completable futures with one completion stage followed by another
		CompletableFuture future = CompletableFuture.supplyAsync(() -> MockedOperation.slowLength("0123456789"))
				.thenCompose(i -> CompletableFuture.supplyAsync(() -> MockedOperation.slowDouble(i)));

		System.out.println("Thread ==> " + Thread.currentThread().getName() + " {Continuing...}");
		System.out.println("Thread ==> " + Thread.currentThread().getName() + " {Result returned: " + future.join() + "}");
	}

	public void demo_FutureHandler() {
		CompletableFuture future = CompletableFuture.supplyAsync(() -> MockedOperation.slowLength("HELLO"))
				.whenComplete((result, ex) -> {
					if (ex == null) {
						System.out.println("Result: " + result);
					} else {
						System.out.println("Thread ==> " + Thread.currentThread().getName() + " {Exception occurred..}");
						ex.printStackTrace();
					}
				});

		System.out.println("Thread ==> " + Thread.currentThread().getName() + " {Continuing...}");
		future.join();
	}


	public void demo_Monad() {
		Promise<Integer> promise =
				Promise.promise(() -> MockedOperation.slowLength("0123456789"))
						.flatMap(i -> Promise.promise(() -> MockedOperation.slowDouble(i)));

		try {
			int val = promise.get();
			System.out.println("Thread ==> " + Thread.currentThread().getName() + " {Result returned: " + val + "}");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Thread ==> " + Thread.currentThread().getName() + " {Doing other things..}");
	}


	public void demo_Transformation() {
		CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> MockedOperation.slowLength("0123456789"))
				.thenApplyAsync(i -> i * 10)
				.thenAccept(i -> System.out.println("Thread ==> "
						+ Thread.currentThread().getName()
						+ " {Result return: " + i + "}"));

		System.out.println("Thread ==> " + Thread.currentThread().getName() + " {Doing other things..}");
		future.join();
	}

}
