
public class User {
	
	private String userName;
	private String password;
	
	
	public User (String name, String pw) {
		userName = name;
		password = pw;
	}

	public void displayUser() {
		
		System.out.println("Login ID: " + userName +"\nPassword: " + password);
	}
	public String getUserName() {
		return userName;
	};
	public String getPassword() {
		return password;
	}

}
