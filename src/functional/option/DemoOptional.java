package functional.option;

import functional.option.nested.*;
import functional.option.users.User;
import functional.option.users.UserRepository;
import functional.utilities.AbstractDemo;

import java.util.Optional;

/**
 * Created by nizamuddin on 06/05/2015.
 */
public class DemoOptional extends AbstractDemo {

	Computer computer = new Computer();

	public void runNonFunctional() {
		super.runNonFunctional();
		System.out.println("The USB version is: " + this.getUsbVersionAncient(new ComputerAncient()));
	}

	@Override
	public void runFunctional() {
		super.runFunctional();
//		demoNestedNonNull();
		demoUsers();
	}

	/*
	Without monad....then...Deep nesting ...
	 */
	public String getUsbVersionAncient(ComputerAncient computer) {
		if (computer != null) {
			SoundCardAncient card = computer.getCard();
			if (card != null) {
				Usb usb = card.getUsb();
				if (usb != null)
					return usb.getVersion();
			}
		}
		return null;
	}

	/*
	With monad...deep nesting of "if non-null logic is removed
	*/
	private String getUsbVersion(Optional<Computer> computer) {
		return computer
				.flatMap(Computer::getCard)
				.flatMap(SoundCard::getUsb)
				.map(Usb::getVersion)
				.orElse("Version: UNKNOWN");

	}

	/*

	if (soundcard != null)
		do_stg(soundcard)

	 */
	private void ifValuePresent() {
		Optional<SoundCard> card = computer.getCard();
		card.ifPresent(System.out::println);
	}

	/*

	if (soundcard != null)
	USB usb = soundcard.getUsb()
	if(usb != null && "3.0".equals(usb.getVersion()) {
		System.out.println("version OK");
	}

	*/
	private void extractAndTransform() {
		Optional<SoundCard> card = computer.getCard();
		Optional<Optional<Usb>> usb = card.map(SoundCard::getUsb);
		Optional<Usb> usb1 = card.flatMap(SoundCard::getUsb);

		card.map(SoundCard::getUsb)
				.filter(usb2 -> { return "3.0".equals(usb1.get().getVersion());})
				.ifPresent(usb3 -> {
					System.out.println(usb3.get().getVersion());
				});
	}


	/*
	Demonstrates that with Optional<T>, "if non-null" logic
	is removed
	 */
	public void demoNestedNonNull() {
		System.out.println("The USB version is: " + this.getUsbVersion(Optional.ofNullable(computer)));
	}

	public void demoUsers() {
		Optional<User> user = UserRepository.findById(3);

		// Perform side-effect if value present
		user.ifPresent(t -> System.out.println("Name: " + t.getFirstName() + "," + t.getLastName()));

		// Mapping an option
		Optional<Integer> age = UserRepository.findById(3).map(i -> i.getAge());
		age.ifPresent(t -> System.out.println("Age: "+t));

		// Nested option - what??
		Optional<Optional<String>> gender = UserRepository.findById(3).map(i -> i.getGender());

		// Flatmapping an option
		Optional<String> gender1 = UserRepository.findById(3).flatMap(i -> i.getGender());
		gender1.ifPresent(i -> System.out.println("Gender: " + i));

		// Chaining options
		User user1 = UserRepository.findById(4).orElse(new User(0, "User Not Found", "", -1, Optional.empty()));
		System.out.println("User: " + user1.getFirstName());
	}
}
