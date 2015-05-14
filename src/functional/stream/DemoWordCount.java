package functional.stream;

import functional.utilities.AbstractDemo;
import functional.utilities.FileUtil;
import functional.utilities.Unchecked;
import functional.utilities.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Created by nizamuddin on 06/05/2015.
 */
public class DemoWordCount extends AbstractDemo {
	private static Logger log = LoggerFactory.getLogger(DemoWordCount.class);
	String dirRoot;
	List<File> files;

	public DemoWordCount(String dirRoot) {
		this.dirRoot = dirRoot;
		try {
			files = FileUtil.scanFiles(dirRoot);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Get the list of files with its associated word count
	 *
	 * @throws IOException
	 */
	private void listCountFile() throws IOException {
		files.stream()
				.forEach(Unchecked.consumer(file ->
						log.info("File: " + file.getName() + "[" + FileUtil.wordCount(file.getAbsolutePath()) + "]")));
	}

	/**
	 * Count words from a list of files using map-reduce
	 *
	 * Steps:
	 * 1. map phase     - map each file into word count of file content (output=List)
	 * 2. reduce phase  - sum up all word counts (output=single value)
	 *
	 * @return long
	 * @throws IOException
	 */
	private long countMapReduce() throws IOException {
		log.info("{Starting countMapReduce()}");

		return files.stream()
				.map(Unchecked.function(file -> FileUtil.wordCount(file.getAbsolutePath())))
				.reduce(0L, ((acc, b) -> acc + b));
	}

	/**
	 * Count words from a list of files using flatmap
	 *
	 * Steps:
	 * 1. flatmap - map each file into lines and "flatten" them
	 * 2. flatmap - map each line into words and "flatten" them
	 *
	 * @return long
	 * @throws IOException
	 */
	public long countFlatMap() throws IOException {
		log.info("{Starting countFlatMap()}");

		return files.stream()
				.flatMap(Unchecked.function(file -> Files.lines(Paths.get(file.getAbsolutePath()))))
				.flatMap(line -> Arrays.stream(line.split(" ")))
				.count();
	}

	/**
	 * Count words from a list of files using map-reduce with parallel stream
	 *
	 * @return
	 * @throws IOException
	 */
	private long countParallelStream() throws IOException {
		log.info("{Starting countParallelStream()}");

		return files.parallelStream()
				.map(Unchecked.function(file -> FileUtil.wordCount(file.getAbsolutePath())))
				.reduce(0L, ((acc, e) -> {
					log.info("{Reduction Phase-ParallelStream}");
					return acc + e;
				}));
	}

	/**
	 * Count words from a list of files asynchronously using CompletableFuture
	 *
	 * @return count
	 * @throws IOException
	 */
	public long countCompletableFuture() throws IOException {
		log.info("{Starting countCompletableFuture()}");

		return files.stream()
				.map(file -> CompletableFuture.supplyAsync(Unchecked.supplier(() -> FileUtil.wordCount(file.getAbsolutePath()))))
				.collect(Collectors.toList()) // ??
				.stream()                     // ??
				.map(future -> future.join())
				.reduce(0L, (acc, e) -> {
					log.info("{Reduction Phase-CompletableFuture}");
					return acc + e;
				});
	}

	/**
	 * Count words from a list of files using flatmap with parallel stream
	 *
	 * @return
	 * @throws IOException
	 */
	private long countParallelStreamFlatMap() throws IOException {
		log.info("{Starting countParallelStreamFlatMap()}");

		return files.parallelStream()
				.flatMap(Unchecked.function(file -> Files.lines(Paths.get(file.getAbsolutePath()))))
				.flatMap(line -> Arrays.stream(line.split(" ")))
				.count();

	}


	/**
	 * Execute the non-functional version
	 */
	@Override
	public void runNonFunctional() {
//		super.runNonFunctional();
//		throw new UnsupportedOperationException();
	}

	/**
	 * Execute the functional version
	 */
	@Override
	public void runFunctional() {
		super.runFunctional();
		Map<String, Long> results = new HashMap<>();

		results.putIfAbsent("sequential(map-reduce): ",
				Util.execute0("Sequential(map-reduce) count =>", Unchecked.supplier(() -> countMapReduce())));
//		results.putIfAbsent("Sequential(flatmap): ",
//				Util.execute0("Sequential(flatmap) count = >", Unchecked.supplier(() -> countFlatMap())));
//		results.putIfAbsent("Completablefuture(map-reduce): ",
//				Util.execute0("completablefuture(map-reduce) count => ", Unchecked.supplier(() -> countCompletableFuture())));
//		results.putIfAbsent("Parallel(map-reduce)" ,
//				Util.execute0("Parallel(map-reduce) count => " , Unchecked.supplier(() -> countParallelStream())));
//		results.putIfAbsent("Parallel(flatmap): ",
//				Util.execute0("Parallel(flatmap) count => ", Unchecked.supplier(() -> countParallelStreamFlatMap())));

		System.out.println("****************************************************");
		System.out.println("* Performance Result of Counting Words in " + files.size() + " files *");
		System.out.println("****************************************************");
		Map<String, Long> sorted = Util.sortByComparator(results, true);
		Util.printMap(sorted);
	}

}
