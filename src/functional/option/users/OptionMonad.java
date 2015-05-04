package functional.option.users;

import functional.utilities.AbstractDemo;

import java.util.Optional;

/**
 * Created by nizamuddin on 29/04/2015.
 */
public class OptionMonad extends AbstractDemo {
	@Override
	public void runNonFunctional() {

	}
	@Override
	public void runFunctional() {
		super.runFunctional();

		Optional<User> user = UserRepository.findById(3);

		// Perform side-effect if value present
		user.ifPresent(t -> System.out.println("Namd: " + t.firstName + "," + t.lastName));

		// Mapping an option
		Optional<Integer> age = UserRepository.findById(3).map(i -> i.age);
		age.ifPresent(t -> System.out.println("Age: "+t));

		// Nested monad
		Optional<Optional<String>> gender = UserRepository.findById(3).map(i -> i.gender);

		//Flatmapping an option
		Optional<String> gender1 = UserRepository.findById(3).flatMap(i -> i.gender);
		gender1.ifPresent(i -> System.out.println("Gender: " + i));

	}
}
