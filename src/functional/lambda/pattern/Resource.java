package functional.lambda.pattern;

/**
 * Created by nizamuddin on 17/04/2015.
 */
public class Resource {
	public void use() {
		System.out.println("operating on the resource..");
	}

	public void dispose() {
		System.out.println("releasing resource...");
	}
}
