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

		Optional<User> user = UserRepository.findById(4);
		user.ifPresent(t -> System.out.println(t.firstName + "," + t.lastName));
	}
}
