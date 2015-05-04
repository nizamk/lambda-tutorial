package functional.option.users;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by nizamuddin on 29/04/2015.
 */
public class UserRepository {
	private static Map<Integer, User> users = new HashMap<Integer, User>();
	static {
		users.put(1, new User(1,"John","Arthur",43, Optional.of("male")));
		users.put(2, new User(2,"Abu","Samad",25, Optional.empty()));
		users.put(3, new User(3,"Hamidah","Bujang",34, Optional.of("female")));
	}

	public static Optional<User> findById(int id) {
		return Optional.ofNullable(users.get(id));
	}

	public static List<User> findAll() {
		return users.values().stream().collect(Collectors.toList());
	}
}
