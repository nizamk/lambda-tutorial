package functional.concurrency;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import functional.utilities.AbstractDemo;
import functional.utilities.AsynchMockedOperation;
import functional.utilities.Unchecked;
import functional.utilities.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * Created by nizamuddin on 23/04/2015.
 */
public class Asynch extends AbstractDemo {

	private static final Logger log = LoggerFactory.getLogger(Asynch.class);
	private static final ScheduledExecutorService scheduler =
			Executors.newScheduledThreadPool(
					1,
					new ThreadFactoryBuilder()
							.setDaemon(true)
							.setNameFormat("WaitTimeout-%d")
							.build());

	List<String> list = Arrays.asList("A", "B", "C", "D");
	List<String> urls = Arrays.asList("example.com", "www.mimos.my");

	/**
	 * Create a Promise object (CompletableFuture without underlying task/thread pool)
	 * that completes after a give java.util.Duration lapsed.
	 *
	 * @param duration
	 * @param <T>
	 * @return CompletableFuture <T>
	 */
	public static <T> CompletableFuture<T> wait(Duration duration) {
		final CompletableFuture<T> promise = new CompletableFuture<>();
		scheduler.schedule(() -> {
			final TimeoutException ex = new TimeoutException("timeout after " + duration.getSeconds());
			return promise.completeExceptionally(ex);
		}, duration.toMillis(), MILLISECONDS);
		return promise;
	}
	/**
	 * The supplied future (the underlying future) should complete within the duration
	 * which otherwise would result in timeout completablefuture to be returned
	 *
	 * @param future
	 * @param duration
	 * @param <T>
	 * @return
	 */
	private static <T> CompletableFuture<T> within(CompletableFuture<T> future, Duration duration) {
		return future.applyToEither(wait(duration), Function.identity());
	}

	@Override
	public void runFunctional() {
		super.runFunctional();
		demo0();
//		demoAsynchCallWithTimeout();
//		demoCreditRating();
	}

	public void demo0() {
		CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
			Util.delay(2000);
			if (true) {
				throw new RuntimeException();
			}
			return 32;
		});

//		future.exceptionally(t -> -1)
		System.out.println(future.exceptionally(t -> -1).join());
	}

	public void demoCreditRating() {

		// run in different thread - get user detail from remote system
//		log.info("Rating: " + rating.getRating());

//		CreditRating rating = CompletableFuture.supplyAsync(() -> {
//			log.info("getUser()");
//			return new MockedDB().getUser(2);
//		}).thenApply(t -> {
//			log.info("getRating()");
//			return RatingSystem.getRatingFromHSBC(t);
//		}).join();
//
//		System.out.println("Rating: " + rating.getRating());

		/**
		 * Use case:
		 * - Get user from remote system
		 * - Get credit rating for this user from 2 different systems (in parallel)
		 * - Combine the rating to get effective rating
		 */
//		CompletableFuture<User> user = CompletableFuture.supplyAsync(() -> {
//			log.info("getUser");
//			return new MockedDB().getUser(2);
//		});
//		CompletableFuture<CreditRating> rating1 =
//				user.thenApplyAsync(t -> {
//					log.info("getRatingMaybank()");
//			more delays
//					return RatingSystem.getRatingFromMayBank(t);
//				});
//		CompletableFuture<CreditRating> rating2 =
//				user.thenApplyAsync(t -> {
//					log.info("getRatingHSBC()");
//					return RatingSystem.getRatingFromHSBC(t);
//				});
//
//		rating2.thenCombineAsync(rating1, (a,b) -> {
//			log.info("combineRating()");
//			return CreditRating.combine(a, b);
//		})
//		.thenAccept(System.out::println).join();
	}

	public void demo() {
		int len = CompletableFuture.supplyAsync(Unchecked.supplier(() -> new AsynchMockedOperation().downloadFromUrl(new URL("http://example.com"))))
				.thenApply(s -> {
					log.info("thenApply()");
					return s.length();
				}).join();
		System.out.println(len);
	}

	public void demoAsynchCallWithTimeout() {
		try {
			serveNonBlocking0();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	public void demoThenAccept() {

		// Semantics of CompletableFuture.thenAccept() is asynchronous processing Handler
		list.stream()
				.map(t -> CompletableFuture.supplyAsync(() -> {
//					if (t.equals("B"))
//						throw new RuntimeException();
					return "Processing: " + t;

				}))
//				.map(t -> t.whenComplete((result, error) -> log.info(result + ":" + error)))
				.map(t -> t.thenAccept(e -> log.info(Thread.currentThread().getName() + ":" + e)))
				.map(t -> t.join())
				.count(); // terminal operation

	}

	@Override
	public void runNonFunctional() {

	}

	public void send(String response) {
		log.info("Thread => " + Thread.currentThread().getName() + " {Sending response ..." + response.length() + "}\n");
	}

	private void serveNonBlocking0() throws InterruptedException, ExecutionException {
		try {
			final CompletableFuture<String> downloadPromise = new AsynchMockedOperation().downloadFromUrlAsynch(new URL("http://" + "example.com"));
			final CompletableFuture<String> promise = within(downloadPromise, Duration.ofSeconds(1));
			promise
					.thenAccept(this::send)
					.exceptionally(throwable -> {
						log.error("{Problem: " + throwable + " secs}\n");
						return null;
					})
					.join();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void serveNonBlocking1() throws InterruptedException, ExecutionException {
		try {
			long start = System.nanoTime();

			// Download page asynchronously
			CompletableFuture<String> future = new AsynchMockedOperation().downloadFromUrlAsynch(new URL("http://" + "example.com"));

			// Handler of the asynchronous operation - reactive..
			// Print out the result of the CompletedFuture (Fulfilled promise)
			CompletableFuture<Void> res = future.thenAccept(t -> log.info(t));
			log.info("{Doing other things ... }\n");
			res.join();

			long duration = (System.nanoTime() - start) / 1_000_000;
			log.info("{Completed downloading in " + duration + " msecs}\n");

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void serveBlocking() throws InterruptedException, ExecutionException {
		try {
			long start = System.nanoTime();

			Future<String> future = new AsynchMockedOperation().downloadFromUrlWithFuture(new URL("http://" + "example.com"));
			String res = future.get(1, TimeUnit.SECONDS);

			log.info("continuing..");
			long duration = (System.nanoTime() - start) / 1_000_000;
			log.info(res);
			log.info("Completed downloading in " + duration + " msecs");

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}
}
