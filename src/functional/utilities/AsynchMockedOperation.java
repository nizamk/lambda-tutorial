package functional.utilities;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by nizamuddin on 23/04/2015.
 */
public class AsynchMockedOperation {

	private static Logger log = LoggerFactory.getLogger(AsynchMockedOperation.class);
	private final ExecutorService pool = Executors.newFixedThreadPool(5);


	public String downloadFromUrl(final URL url) throws IOException {
		try (InputStream input = url.openStream()) {
			log.info("{Started downloading from " + url.toString() + "}\n");
			return IOUtils.toString(input, StandardCharsets.UTF_8);
		}
	}

	public CompletableFuture<String> downloadFromUrlAsynch(final URL url) throws IOException {
		log.info("{Started downloading ... }\n");
		return CompletableFuture.supplyAsync(Unchecked.supplier(() -> {
			try (InputStream input = url.openStream()) {
//				Thread.sleep(2000);
				log.info("Thread => " + Thread.currentThread().getName() + " {Started downloading from " + url.toString() + "}\n");
				return IOUtils.toString(input, StandardCharsets.UTF_8);
			}
		}));
	}

	public Future<String> downloadFromUrlWithFuture(final URL url) throws IOException {
		return pool.submit(() -> {
			try (InputStream input = url.openStream()) {
				log.info("{Started downloading from " + url.toString() + "}\n");
				return IOUtils.toString(input, StandardCharsets.UTF_8);
			}
		});
	}
}
