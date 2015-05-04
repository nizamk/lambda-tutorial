package functional.parser;

import java.util.function.Function;

/**
 * Created by nizamuddin on 09/04/2015.
 */
public class Failure<T> implements Result<T> {
	public <U> Failure<U> map(Function<? super T, ? extends U> f) {
		return new Failure<>();
	}
}
