package apz.airplane;

import java.util.ArrayList;

public class User {
	private static int id = 0;
	private int userId;
	private String username, password;
	private ArrayList<Booking> tripList;	// need add, remove, find function
	
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		tripList = new ArrayList<>();
		userId = id++;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public static int getId() {
		return id;
	}

	public static void setId(int id) {
		User.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Username: " + username + "\tPassword: " + password;
	}

}
