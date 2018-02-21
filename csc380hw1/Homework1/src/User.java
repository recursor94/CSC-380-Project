
public class User {
	
	private String userName;
	private String password;
	
	
	//Constructor for a user's login ID and password
	public User (String name, String pw) {
		
		userName = name;
		password = pw;
		
	}
	
	//This is just a test method so I can run something in the Driver; we can delete this later
	public void displayUser() {
		
		System.out.println("Login ID: " + userName +"\nPassword: " + password);
	}

}
