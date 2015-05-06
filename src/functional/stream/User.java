package functional.stream;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nizamuddin on 06/05/2015.
 */
public class User {
	String name;
	public List<Role> roles = new ArrayList<>();

	public User(String name) {
		this.name = name;
	}
}
