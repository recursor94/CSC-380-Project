package apz.airplane.util;

import apz.airplane.User;
import apz.airplane.UserController;
import apz.airplane.gui.APZLauncher;
import apz.airplane.gui.LoginWindow;
import javafx.scene.control.Alert.AlertType;

public class VerifyUserInput {
	public static void verifyInput(String username, String password, UserController uc) {
		//Checks if a user name exists already
		if (uc.userExists(username)) {
			System.out.println("The username you chose already exists");
			MessageBox.message(AlertType.ERROR, "ERROR: The User Name Already Exists", "Please choose a different user name");
		}
		//If a user name does not already exist
		else {
			//Check if the text fields are empty
			if (!(username.isEmpty()) && !(password.isEmpty())) {
				
				//Check if the username contains any empty spaces
				if (username.contains(" ")) {
					MessageBox.message(AlertType.ERROR, "Invalid User Name", "Your user name cannot contain the empty space character");
					
				}
				//Check if the password contains empty spaces 
				else if (password.contains(" ")) {
					MessageBox.message(AlertType.ERROR, "Invalid Password", "Your password cannot contain the empty space character");
				}
				// IF WE WANT A PASSWORD TO BE A CERTAIN LENGTH, WE CAN ADD THIS ELSE IF IN
//						else if (passField.getText().length() < 8) {
//							MessageBox.message(AlertType.ERROR, "Invalid Password", "Your password must be at least 8 characters long");
//						}
				//Create a user if there are not errors in the input
				else {
					User user = new User (username, password);
					uc.addUser(user);
					State.saveInformation(uc);
					System.out.println("User successfully created!");
					MessageBox.message(AlertType.INFORMATION, "Successful User Creation", "Your account has been created!");
					System.out.println(uc);
					//new ViewFlightWindow(primaryStage);
				}	
			}
			//Display error if the textfields are empty
			else {
				MessageBox.message(AlertType.ERROR, "ERROR: You must enter a user name and password", "Please enter a user name and password");
			}
		}
	}
}
