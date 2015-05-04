package functional.lambda.dry;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by nizamuddin on 29/04/2015.
 */
public class FactoryFilters {

	/**
	 * Function that filters only email sent by any one of
	 *
	 * arg: List
	 * result: Predicate
	 */
	public static Function<List<Email>, Predicate<Email>> sentByOneOf =
			senders -> email -> senders.stream().anyMatch(i -> i.sender == email.sender);

	/**
	 * Function that filters only emails NOT sent by any one of
	 *
	 * arg: List
	 * result: Predicate
	 */
	public static Function<List<Email>, Predicate<Email>> notSentByAnyOf =
			senders -> email -> senders.stream().noneMatch(i -> i.sender == email.sender);

	/**
	 * Function that filters only email with size at least n
	 *
	 * arg: Integer
	 * result: Predicate
	 */
	public static Function<Integer, Predicate<Email>> minimumSize =
			n -> email -> email.getContent().length() >= n;

	/**
	 * Function that filters only email with size at most n
	 *
	 * arg: Integer
	 * result: Predicate
	 */
	public static Function<Integer, Predicate<Email>> maximumSize =
			n -> email -> email.getContent().length() <= n;

	/**
	 * Function that provide size contraint
	 *
	 * arg: Predicate
	 * result: Predicate
	 */
	public static Function<Predicate<Integer>, Predicate<Email>> SizeContraint =
			f -> email -> f.test(email.getContent().length());

	/**
	 * Minimum size filter - version 2.0
	 */
	public static Function<Integer, Predicate<Email>> minSize =
			n -> SizeContraint.apply(t -> t >= n);

	/**
	 * Maximum size filter - version 2.0
	 */
	public static Function<Integer, Predicate<Email>> maxSize =
			n -> SizeContraint.apply(t -> t <= n);

	/**
	 * Staying DRY with function composition
	 */
	public static Function<List<Email>, Predicate<Email>> notSentByAnyOf2 =
			sentByOneOf.andThen(g -> complement(g));

	/**
	 * Composing predicates
	 *
	 * @param predicate
	 * @param <T>
	 * @return predicate
	 */
	public static <T> Predicate<T> complement(Predicate<T> predicate) {
		return t -> !predicate.test(t);
	}

	/**
	 *
	 * @param predicates
	 * @param <T>
	 * @return predicate
	 */
	public static <T> Predicate<T> any(Predicate<T>... predicates) {
		return a -> {
			for (Predicate<T> predicate : predicates) {
				if (predicate.test(a)) {
					return true;
				}
			}
			return false;
		};
	}
}