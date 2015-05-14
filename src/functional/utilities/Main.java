package functional.utilities;

import functional.concurrency.DemoAsynch;

import java.util.function.Consumer;

/**
 * Created by nizamuddin on 17/04/2015.
 */
public class Main {
	IDemo d;

	public Main(IDemo m) {
		this.d = m;
	}

	public static void main(String... args) {
		new Main(new DemoAsynch()).run(t -> {
			t.runNonFunctional();
			t.runFunctional();
		});
	}

	public void run(Consumer<IDemo> run) {
		run.accept(d);
	}
}
