package functional.concurrency.creditrating;

/**
 * Created by nizamuddin on 28/04/2015.
 */
public class User {
	private String name;
	private int id;
	private int rating;

	public User(String name, int id) {
		this.name = name; this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
}
