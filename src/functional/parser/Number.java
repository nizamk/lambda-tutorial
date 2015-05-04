package functional.parser;

/**
 * Created by nizamuddin on 09/04/2015.
 */
public class Number implements Expr {
	public int getValue() {
		return value;
	}
	private int value;

	public Number(int value) {
		this.value = value;
	}
}
