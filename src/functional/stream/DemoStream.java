package functional.stream;

import functional.utilities.AbstractDemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by nizamuddin on 06/05/2015.
 */
public class DemoStream extends AbstractDemo {
	List<String> list = Arrays.asList("d2", "a2", "b1", "b3", "c");
	List<User> users = new ArrayList<>();

	public DemoStream() {
		// add users
		IntStream
				.range(1, 5)
				.forEach(i -> users.add(new User("User" + i)));

		// add roles to user
		users.forEach(u -> {
					IntStream
							.range(1, 3)
							.forEach(i -> u.roles.add(new Role("Role" + i)));
				}
		);

		// add resources to user
		users.forEach(user -> {
			user.roles.forEach(role -> {
				IntStream
						.range(1, 3)
						.forEach(i -> role.resources.add(
								new Resource(user.name + "," + role.name + "," + "Resource" + i)));
			});
		});

	}

	@Override
	public void runNonFunctional() {
		super.runNonFunctional();
		nestedLoop();
	}

	@Override
	public void runFunctional() {
		super.runFunctional();
//		laziness();
		flattenNestedLoop();
	}

	public void laziness() {
		list.stream()
				.filter(s -> {
					System.out.println("filter: " + s);
					return true;
				});
//				.forEach(s -> System.out.println("forEach: " + s));

		list.stream()
				.map(s -> {
					System.out.println("map: " + s);
					return s.toUpperCase();
				})
				.anyMatch(s -> {
					System.out.println("anyMatch: " + s);
					return s.startsWith("A");
				});
	}

	public void nestedLoop() {
		for (User user : users) {
			for (Role role : user.roles) {
				for (Resource resource : role.resources) {
					System.out.println("Resource: " + resource.name);
				}
			}
		}
	}

	public void flattenNestedLoop() {
		users.stream()
				.flatMap(i -> i.roles.stream())
				.flatMap(i -> i.resources.stream())
				.forEach(i -> System.out.println("Resource: " + i.name));
	}
}
