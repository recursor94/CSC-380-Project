package main;
import java.util.ArrayList;

public class UserManagement {
	
	ArrayList<User> userList;
	
	public UserManagement() {
		userList = new ArrayList<>();
	}
	
	public void removeUser(User user) {
		userList.remove(user);
		
	}
	
	public void addUser (User obj) {
		
		userList.add(obj);
	}

}
