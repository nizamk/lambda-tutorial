package functional.parser;

/**
 * Created by nizamuddin on 09/04/2015.
 */
public class Main {
	public static void main(String... args) {
		System.out.println("Parser combinator...");
		
		Success<Integer> o = new Success<String>("abc", "1234").map(s -> s.length());
		System.out.println("Result: " + o.getResult());
		System.out.println(isPrime(1));
		;
	}
	private static boolean isPrime(int i) {
		return false;
	}
}