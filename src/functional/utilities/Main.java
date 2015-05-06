package functional.utilities;

import functional.lambda.DemoHOF;

import java.util.function.Consumer;

/**
 * Created by nizamuddin on 17/04/2015.
 */
public class Main {
	Demo d;

	public Main(Demo m) {
		this.d = m;
	}

	public static void main(String... args) {
		new Main(new DemoHOF()).run(t -> {
			t.runNonFunctional();
			t.runFunctional();
		});
	}

	public void run(Consumer<Demo> run) {
		run.accept(d);
	}
}
