package main;
public class User {
	
	private String username;
	private String password;
	
	
	public User (String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [userName=" + username + ", password=" + password + "]";
	}
	public String getUserName() {
		return userName;
	};
	public String getPassword() {
		return password;
	}

}
