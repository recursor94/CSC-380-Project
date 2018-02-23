package main;
import java.util.ArrayList;

public class UserManagement {
	
	private ArrayList<User> userList;
	
	public UserManagement() {
		userList = new ArrayList<>();
	}
	
	public void addUser (User user) {
		userList.add(user);
	}
	
	public void removeUser(String username) {
		for(User user : userList) {
			if(user.getUsername().equals(username)) {
				userList.remove(user);
			}
		}
	}
	
	public User findUser(String username) {
		for(User user : userList) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}

}
