package functional.option.users;

import java.util.Optional;

/**
 * Created by nizamuddin on 29/04/2015.
 */
public class User {
	int id;
	String firstName;
	String lastName;
	int age;
	Optional<String> gender;

	public User(int id, String firstName, String lastName, int age, Optional<String> gender) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Optional<String> getGender() {
		return gender;
	}
	public void setGender(Optional<String> gender) {
		this.gender = gender;
	}
}
