package functional.stream;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nizamuddin on 06/05/2015.
 */
public class Role {
	String name;
	public List<Resource> resources = new ArrayList<>();

	public Role(String name) {
		this.name = name;
	}
}
