package functional.option.nested;

import java.util.Optional;

/**
 * Created by nizamuddin on 06/04/2015.
 */
public class Computer {
	private Optional<SoundCard> card;

	public Computer() {
		card = Optional.ofNullable(new SoundCard());
	}

	public Optional<SoundCard> getCard() {
		return card;
	}
}
