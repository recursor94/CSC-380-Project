package zak;

import java.io.Serializable;
import java.util.ArrayList;

public class UserControllerZak implements Serializable {
	
	// Default ID KEEP IN MIND FOR LATER
	
	// need book function that searches through userList for user then adds booking to their arraylist
	private ArrayList<UserZak> userList;
	
	public UserControllerZak() {
		userList = new ArrayList<>();
		// load in the file
	}
	
	public void addUser (UserZak user) {
		userList.add(user);
	}
	
	public boolean removeUser(String username) {
		for(UserZak user : userList) {
			if(user.getUsername().equals(username)) {
				userList.remove(user);
				return true;
			}
		}
		return false;
	}
	
	public UserZak findUser(String username) {
		for(UserZak user : userList) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}
	
	//This checks to see if a username exists
	public boolean userExists (String username) {
		for(UserZak user : userList) {
			if(user.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	public UserZak login(String username, String password) {
		for(UserZak user : userList) {
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
