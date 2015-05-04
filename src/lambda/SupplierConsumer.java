package lambda;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by nizamuddin on 25/03/2015.
 */
public class SupplierConsumer<T> extends Thread {
	private Supplier<T> supplier;
	private Consumer<T> consumer;
	private boolean canRun = true;

	public SupplierConsumer(Supplier<T> supplier, Consumer<T> consumer) {
		this.supplier = supplier;
		this.consumer = consumer;
	}

	@Override
	public void run() {
		while (canRun) {
			T item = supplier.get();
			consumer.accept(item);
		}
	}
}
