package functional.utilities;

/**
 * Created by nizamuddin on 21/04/2015.
 */
public class MockedOperation {


	public static int slowLength(String s) {
		System.out.println("Thread ==> " + Thread.currentThread().getName()
				+ " {Computing length very slowly}");
		Util.delay(2000);
//		if (true)
//			throw new RuntimeException();
		return s.length();
	}

	public static int slowDouble(int i) {
		System.out.println("Thread ==> " + Thread.currentThread().getName()
				+ " {Computing double very slowly}");
		Util.delay(3000);
		return i * 2;
	}
}