package functional.utilities;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by nizamuddin on 17/04/2015.
 */
public class Util {

	public static Map<String, Long> sortByComparator(Map<String, Long> unsortMap, final boolean order)
	{
		List<Map.Entry<String, Long>> list = new LinkedList<Map.Entry<String, Long>>(unsortMap.entrySet());

		// Sorting the list based on values
		Collections.sort(list, new Comparator<Map.Entry<String, Long>>() {
			public int compare(Map.Entry<String, Long> o1,
			                   Map.Entry<String, Long> o2) {
				if (order) {
					return o1.getValue().compareTo(o2.getValue());
				} else {
					return o2.getValue().compareTo(o1.getValue());

				}
			}
		});

		// Maintaining insertion order with the help of LinkedList
		Map<String, Long> sortedMap = new LinkedHashMap<String, Long>();
		for (Map.Entry<String, Long> entry : list)
		{
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

	public static void printMap(Map<String, Long> map)
	{
		for (Map.Entry<String, Long> entry : map.entrySet())
		{
			System.out.println("> " + entry.getKey() + " completed in "+ entry.getValue() + " msecs.");
		}
	}

	public static void delay(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures) {
		return CompletableFuture.supplyAsync(() -> futures.stream().
						map(future -> future.join()).
						collect(Collectors.<T>toList())
		);
	}

	public static void execute1(String msg, Supplier<List<String>> s) {
		long start = System.nanoTime();
		long duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println(msg + "{" + s.get() + "} done in " + duration + " msecs");
	}

	public static long execute0(String msg, Supplier<Long> s) {
		long start = System.nanoTime();
		long val = s.get();
		long duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("" + msg + "[" + val + "] done in " + duration + " msecs" + "\n");
		return duration;
	}

	public static <T, R> long measure(Function<T, R> g, T input) {
		long fastest = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			long start = System.nanoTime();
			R result = g.apply(input);
			long duration = (System.nanoTime() - start) / 1_000_000;
			if (duration < fastest) fastest = duration;
		}
		return fastest;
	}
}
