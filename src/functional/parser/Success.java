package functional.parser;

import java.util.function.Function;

/**
 * Created by nizamuddin on 09/04/2015.
 */
public class Success<T> implements Result<T> {
	private T result;
	private String next;

	public String getNext() {
		return next;
	}
	public T getResult() {
		return result;
	}

	public Success(T result, String next) {
		this.result = result;
		this.next = next;
	}

	public <U> Success<U> map(Function<? super T, ? extends U> f) {
		return new Success<U>(f.apply(result), next);
	}
}