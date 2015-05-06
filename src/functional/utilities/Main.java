package functional.utilities;

import functional.stream.DemoStream;

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
		new Main(new DemoStream()).run(t -> {
			t.runNonFunctional();
			t.runFunctional();
		});
	}

	public void run(Consumer<IDemo> run) {
		run.accept(d);
	}
}
