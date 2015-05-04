package functional.parser;

import java.util.function.Function;

/**
 * Created by nizamuddin on 09/04/2015.
 */
public interface Result<T> {
	//	T next();
	<U> Result<U> map(Function<? super T, ? extends U> f);
}
