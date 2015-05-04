package functional.utilities;

/**
 * Created by nizamuddin on 17/04/2015.
 */
public class AbstractDemo implements Demo {
	@Override
	public void runNonFunctional() {
		System.out.println("\n******************");
		System.out.println("*   Current Way  *");
		System.out.println("******************");
	}
	@Override
	public void runFunctional() {
		System.out.println("\n******************");
		System.out.println("* Functional Way *");
		System.out.println("******************");
	}
}
