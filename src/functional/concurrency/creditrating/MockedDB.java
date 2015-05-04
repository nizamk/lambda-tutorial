package functional.concurrency.creditrating;

/**
 * Created by nizamuddin on 28/04/2015.
 */
public class MockedDB {
	public User getUser(int id) {
		User user = new User("Ahmad", id);
		return user;
	}
}
