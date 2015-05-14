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
			new Email("hello", "hello world", "ahmad@mimos.my", "me@here.com"),
			new Email("hello", "hello world1", "abc12@mimos.my", "me@here.com"),
			new Email("hello", "hello world12", "anees@mimos.my", "me@here.com"),
			new Email("hello", "hello world123", "abdul@mimos.my", "me@here.com")

	);

	public List<Email> newMailsForUser(List<Email> mails, Predicate<Email> emailFilter) {
		return mails.stream().filter(emailFilter).collect(Collectors.toList());
	}

	@Override
	public void runNonFunctional() {
	}

	@Override
	public void runFunctional() {
		super.runFunctional();

		List<Email> blackList = Arrays.asList(
				new Email("hello", "hello world", "ahmad@mimos.my", "me@here.com"),
				new Email("hello", "hello world", "abc12@mimos.my", "me@here.com")

		);

		List<Email> blackList1 = Arrays.asList(
				new Email("hello", "hello world", "abc12@mimos.my", "me@here.com")

		);


		Predicate<Email> filter0 = FactoryFilters.notSentByAnyOf.apply(blackList);

		System.out.println("Filtered email based on blacklist:");
		newMailsForUser(emails, filter0).forEach(t -> System.out.println(t.getSender()));

		System.out.println("Filtered email based on size:");
		newMailsForUser(emails, FactoryFilters.minSize.apply(12)).forEach(i -> System.out.println(i.getSender()));

	}
}
