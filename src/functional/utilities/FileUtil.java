package functional.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by nizamuddin on 21/04/2015.
 */
public class FileUtil {

	private static Logger log = LoggerFactory.getLogger(FileUtil.class);

	/**
	 * Return list of File objects from a given path
	 *
	 * @param dirRoot - absolute path to a directory
	 * @return list of files as List<T>
	 * @throws java.io.IOException
	 */
	public static List<File> scanFiles(final String dirRoot) throws IOException {
		log.info("{Scan files..}");

		return Stream.of(new File(dirRoot).listFiles(p -> p.toString().endsWith(".txt")))
				.collect(Collectors.toList());
	}

	/**
	 * Get words count for a file in a given path
	 *
	 * @param filePath - absolute path to a file
	 * @return count of words in a file
	 * @throws IOException
	 */
	public static long wordCount(String filePath) throws IOException {
		log.info("{Word count..}");

		return Files.lines(Paths.get(filePath))
				.flatMap(line -> Arrays.stream(line.split(" ")))
				.count();
	}
}
