package functional.concurrency.creditrating;

/**
 * Created by nizamuddin on 28/04/2015.
 */
public class RatingSystem {
	public  static CreditRating getRatingFromHSBC(User o) {
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		CreditRating rating = new CreditRating();
		rating.setUser(o);
		rating.setRating(8);
		return rating;
	}

	public static CreditRating getRatingFromMayBank(User o) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		CreditRating rating = new CreditRating();
		rating.setUser(o);
		rating.setRating(9);
		return rating;
	}
}
