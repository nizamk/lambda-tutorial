package functional.parser;

/**
 * Created by nizamuddin on 09/04/2015.
 */
public class SubOp implements Expr {
	private Expr expr1;
	private Expr expr2;

	public SubOp(Expr expr1, Expr expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}
}
