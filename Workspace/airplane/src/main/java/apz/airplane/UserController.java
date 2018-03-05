package apz.airplane;

import java.util.ArrayList;

public class UserController {
	
	// need book function that searches through userList for user then adds booking to their arraylist
	private ArrayList<User> userList;
	
	public UserController() {
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
	
	public User login(String username, String password) {
		for(User user : userList) {
			if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
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
