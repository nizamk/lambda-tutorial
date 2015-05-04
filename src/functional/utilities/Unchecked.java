package functional.utilities;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by nizamuddin on 24/04/2015.
 */
public class Unchecked {

	/**
	 * Return a new consumer that wraps an existing consumer that throws exception
	 *
	 * @param f
	 * @param <T>
	 * @return
	 */
	public static <T> Consumer<T> consumer(ConsumerThrowsEx<T> f) {
		return t -> {
			try {
				f.accept(t);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		};
	}

	/**
	 * Return a new function that wraps an existing function that throws exception
	 * @param f
	 * @param <T>
	 * @param <R>
	 * @return
	 */
	public static <T, R> Function<T, R> function(FunctionThrowsEx<T, R> f) {
		return t -> {
			try {
				return f.apply(t);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		};
	}

	/**
	 * Return a new supplier that wraps an existing supplier that throws exception
	 *
	 * @param f
	 * @param <T>
	 * @return
	 */
	public static <T> Supplier<T> supplier(SupplierThrowsEx<T> f) {
		return () -> {
			try {
				return f.get();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		};
	}

	@FunctionalInterface
	public interface SupplierThrowsEx<T> {
		T get() throws Exception;
	}

	@FunctionalInterface
	public interface ConsumerThrowsEx<T> {
		void accept(T t) throws Exception;
	}

	@FunctionalInterface
	public interface FunctionThrowsEx<T, R> {
		R apply(T t) throws Exception;
	}
}
