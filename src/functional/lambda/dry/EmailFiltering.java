package functional.lambda.dry;

import functional.utilities.AbstractDemo;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by nizamuddin on 29/04/2015.
 */
public class EmailFiltering extends AbstractDemo {

	List<Email> emails = Arrays.asList(
			new Email("hello", "hello world", "ahmad@mimos.my", "This is hell world"),
			new Email("hello", "hello world", "abc12@mimos.my", "This is hell world"),
			new Email("hello", "hello world", "anees@mimos.my", "This is hell world")
	);

	public List<Email> newMailsForUser(List<Email> mails, Predicate<Email> predicate) {
		return mails.stream().filter(predicate).collect(Collectors.toList());
	}

	@Override
	public void runNonFunctional() {
	}

	@Override
	public void runFunctional() {
		super.runFunctional();

		List<Email> blackList = Arrays.asList(
				new Email("hello", "hello world", "ahmad@mimos.my", "This is hell world"),
				new Email("hello", "hello world", "abc12@mimos.my", "This is hell world")

		);

		List<Email> blackList1 = Arrays.asList(
				new Email("hello", "hello world", "abc12@mimos.my", "This is hell world")

		);

		List<Email> blackList2 = Arrays.asList(
				new Email("hello", "hello world", "ahmad@mimos.my", "This is hell world")
		);

		Predicate<Email> filter0 = FactoryFilters.notSentByAnyOf.apply(blackList);
		Predicate<Email> filter1 = FactoryFilters.any(
				FactoryFilters.notSentByAnyOf2.apply(blackList1)
		);

//		System.out.println(filter1.test(new Email("hello", "world", "ali@mimos.my", "")));
		newMailsForUser(emails, filter0).forEach(t -> System.out.println(t.getSender()));
	}
}
