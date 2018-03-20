package apz.airplane;

import java.util.ArrayList;

public class UserController {
	
	// need book function that searches through userList for user then adds booking to their arraylist
	private ArrayList<User> userList;
	
	public UserController() {
		userList = new ArrayList<>();
		// load in the file
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
	
	//This checks to see if a username already exists and returns it if it does
	public String checkUsername (String username) {
		for(User user : userList) {
			if(user.getUsername().equals(username)) {
				return user.getUsername();
			}
		}
		return null;
	}
	
	public User login(String username, String password) {
		for(User user : userList) {
			if(user.getUsername().equals(username) && user.validatePassword(password)) {
				return user;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return "User List\n-----------\n" + userList;
	}
	
	

}
