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
	
	public User findUser(String username) {
		
		for(User user : userList) {
			if(user.getUserName().equals(username)) {
				return user;
			}
		}
		return null;
	}

}
