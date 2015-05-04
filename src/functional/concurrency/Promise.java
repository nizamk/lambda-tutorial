package functional.concurrency;

import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class Promise<T> implements Future<T> {

	private final CompletableFuture<T> future;

	private Promise(CompletableFuture<T> future) {
		this.future = future;
	}

	public static final <T> Promise<T> promise (Supplier<T> supplier) {
		return new Promise<T> (CompletableFuture.supplyAsync(supplier));
	}

	public <U> Promise<U> map (Function<? super T, ? extends U> f) {
		return new Promise<U>(future.thenApplyAsync(f));
	}

	public <U> Promise<U> flatMap(Function<? super T, Promise<U>> f) {
		return new Promise<U> (
				this.future.thenComposeAsync( a -> f.apply(a).future));
	}

	/*
	public <U> Promise<U> flatMap2(Function<? super T, Promise<U>> f) {
		return new Promise<U> (
				this.future.thencombin( a -> f.apply(a).future));
	}
	*/


	@Override
	public T get(long timeout, TimeUnit unit)
			throws InterruptedException, ExecutionException, TimeoutException {
		return this.future.get(timeout, unit);
	}
	@Override
	public T get() throws InterruptedException, ExecutionException {
		return this.future.get();
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return this.future.cancel(mayInterruptIfRunning);
	}

	@Override
	public boolean isCancelled() {
		return this.future.isCancelled();
	}

	@Override
	public boolean isDone() {
		return this.future.isDone();
	}
}
