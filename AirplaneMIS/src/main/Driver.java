/**
 * 
 * @author Andrew Spano, Jimmy Nguyen, Zak Gudlin
 * Created 2/6/2018
 *
 */
import java.util.Scanner;
public class Driver {

	public static void main(String[] args) {
		
		//Scanner object
		Scanner scan = new Scanner (System.in);
		
		//Input variables for creating a user
		String username;
		String password;
		
		//Create a username and password
		System.out.print("Please enter a user name: ");
		username = scan.nextLine();
		System.out.print("Please create a password: ");
		password = scan.nextLine();
		
		//Create the user and display the details
		User user = new User (username, password);
		System.out.println();
		System.out.println();
		user.displayUser();
		
		
	}

}
