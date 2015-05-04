package functional.concurrency.creditrating;

/**
 * Created by nizamuddin on 28/04/2015.
 */
public class CreditRating {
	private User user;
	private int rating;

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}

	public static int combine(CreditRating a, CreditRating b) {
		int highest = a.getRating();
		return highest > b.getRating() ? highest : b.getRating();
	}
}
