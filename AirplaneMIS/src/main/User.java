package main;
public class User {
	
	private String userName;
	private String password;
	
	
	public User (String name, String pw) {
		userName = name;
		password = pw;
	}


	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + "]";
	}

}
