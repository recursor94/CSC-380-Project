package apz.airplane.model;

import java.io.Serializable;
import java.util.ArrayList;

public class UserController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<User> userList;
	
	public UserController() {
		userList = new ArrayList<>();
	}
	
	public void addUser (User user) {
		userList.add(user);
	}
	
	public boolean removeUser(String username) {
		for(User user : userList) {
			if(user.getUsername().equals(username)) {
				userList.remove(user);
				return true;
			}
		}
		return false;
	}
	
	public User findUser(String username) {
		for(User user : userList) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}
	
	public boolean userExists (String username) {
		for(User user : userList) {
			if(user.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	public User login(String username, String password) {
		for(User user : userList) {
			if(user.getUsername().equals(username) && user.validatePassword(password)) {
				return user;
			}
		}
		return null;
	}
	
	public ArrayList<User> getUserList() {
		return userList;
	}
	
	@Override
	public String toString() {
		return "User List\n-----------\n" + userList;
	}
}
