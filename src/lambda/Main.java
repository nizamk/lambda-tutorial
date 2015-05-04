package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {
		List<String> myList = Arrays.asList("a1", "a2", "a3", "c2", "c1");
		myList
			.stream()
			.filter(s->s.startsWith("a"))
			.map(String::toUpperCase)
			.sorted()
			.forEach(System.out::println);

	    Main main = new Main();
	    main.functionalFunctionTest();
	    main.functionalPredicateTest();
	    main.functionalConsumerTest();
	    main.functionalSupplierTest();
	}

	public void functionalFunctionTest() {
		System.out.println("---functionalFunctionalTest---");

		Function<String, Integer> len = s -> s.length();

		// function chaining - get string length and check if the size greater than 5
		System.out.println(len.andThen(i -> i > 5).apply("This is string"));
		// get string length
		System.out.println(len.apply("This is a string"));
	}

	public void functionalConsumerTest() {
		System.out.println("---functionalConsumerTest---");

		Consumer<User> consumer = v -> System.out.println("Username: " + v.getName());
		Supplier<User> supplier = User::new;
		User user = supplier.get();
		user.setName("ahmad");
		consumer.accept(user);
	}

	public void functionalSupplierTest()  {
		System.out.println("---functionalSupplierTest---");

		BlockingQueue<User> usersQueue = new LinkedBlockingDeque<User>();
		Supplier<User> s = () -> {
			try {
				return usersQueue.take();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		};

		Consumer<User> c = user -> {
			System.out.println("Get data on thread: "
			+ Thread.currentThread().getId()
			+ " for User: " + user.getName());
		};

		// start supplier/consumer thread
		new SupplierConsumer<User>(s,c).start();

		for(int i=0; i < 20; i++) {
			try {
				System.out.println("Put data on thread: " + Thread.currentThread().getId());
				usersQueue.put(new User("user" + i, "" + i, "role" + i));
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

		}
	}

	private List<User> filter(List<User> users, Predicate<User> predicate) {
		List<User> result = new ArrayList<>();
		for(User user: users) {
			if(predicate.test(user)) {
				result.add(user);
			}
		}
		return result;
	}

	public void functionalPredicateTest() {
		System.out.println("---functionalPredicateTest---");

		List<User> users = new ArrayList<>();
		users.add(new User("ali","2","admin"));
		users.add(new User("ahmad","3","member"));
		users.add(new User("abu","4","admin"));

		// filtering list with predicate
		List<User> filtered = filter(users, o -> o.getRole().equals("admin"));
		filtered
				.stream()
//				.map((User u) -> u.getName().toUpperCase())
//				.forEach(System.out::println);
				.map((User u) -> {
					u.setName(u.getName().toUpperCase());
					return u;
				})
				.forEach((User u) -> System.out.println("User: " + u.getName()));
	}
}
