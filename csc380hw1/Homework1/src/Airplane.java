import java.util.ArrayList;

/**
 * 
 * @author Jimmy Nguyen
 * Created 2/13/2018
 *
 */
public class Airplane {

	private String name;
	private int maxPassengers;
	private int planeId;
	private int[] seating;
	private ArrayList<User> users;
	private int numUsers;
	
	//Constructor - Probably needs a few more things in it, but I just wanted to get a start
	public Airplane(int max) {
		
		numUsers = 0;
		maxPassengers = max;
		users = new ArrayList<User>(max);
	}
	
	//Method to add a user to the user array list
	public void addUser(User obj) {
		
		users.add(numUsers, obj);
		numUsers ++;
		
	}
	

}
