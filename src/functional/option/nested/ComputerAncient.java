package functional.option.nested;

/**
 * Created by nizamuddin on 07/04/2015.
 */
public class ComputerAncient {
	private SoundCardAncient card;

	public ComputerAncient() {
		card = new SoundCardAncient();
	}

	public SoundCardAncient getCard() {
		return card;
	}
}
