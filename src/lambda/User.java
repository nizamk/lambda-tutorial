package lambda;

/**
 * Created by nizamuddin on 25/03/2015.
 */
public class User {
	String name;
	String id;
	String role;

	public User() {}

	public User(String name, String id, String role) {
		this.name = name;
		this.id = id;
		this.role = role;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole (String role) {
		this.role = role;
	}

	public void print() {
		System.out.println("User: (" + this.getName() + "," + this.getId() + "," + this.getRole() + ")");
	}
}
