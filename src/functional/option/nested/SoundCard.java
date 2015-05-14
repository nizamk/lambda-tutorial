package functional.option.nested;

import java.util.Optional;

/**
 * Created by nizamuddin on 06/04/2015.
 */
public class SoundCard {
	private Optional<Usb> usb;

	public SoundCard() {

		usb = Optional.ofNullable(new Usb());
	}

	public Optional<Usb> getUsb() { return usb; }
}
