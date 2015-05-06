package functional.option.nested;

import functional.utilities.AbstractDemo;

import java.util.Optional;

/*
Optional Monad:
- makes the possibility of missing data EXPLICIT in the type system
- while HIDING the boilerplate of "if non-null" logic
*/
public class OptionalMonad extends AbstractDemo {

	Computer computer = new Computer();

	public void runNonFunctional() {
		super.runNonFunctional();
		System.out.println("The USB version is: " + this.getUsbVersionAncient(new ComputerAncient()));
	}

	@Override
	public void runFunctional() {
		super.runFunctional();
		System.out.println("The USB version is: " + this.getUsbVersion(Optional.ofNullable(computer)));
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
	public String getUsbVersion(Optional<Computer> computer) {
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
	public void ifValuePresent() {
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
	public void extractAndTransform() {
		Optional<SoundCard> card = computer.getCard();
		Optional<Optional<Usb>> usb = card.map(SoundCard::getUsb);

		card.map(SoundCard::getUsb)
				.filter(usb1 -> { return "3.0".equals(usb1.get().getVersion());})
				.ifPresent(usb2 -> {
					System.out.println(usb2.get().getVersion());
				});
	}


	/*
	Demonstrates that with Optional<T>, "if non-null" logic
	is removed
	 */
	public void demo() {
		System.out.println("The USB version is: " + this.getUsbVersion(Optional.ofNullable(computer)));
	}
}
