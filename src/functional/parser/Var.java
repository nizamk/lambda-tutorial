package functional.parser;

/**
 * Created by nizamuddin on 09/04/2015.
 */
public class Var implements Expr {
	public void setValue(String value) {
		this.value = value;
	}
	private String value;

	public Var(String value) {
		this.value = value;
	}
}
