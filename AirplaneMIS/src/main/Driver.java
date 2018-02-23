package main;

import javafx.application.Application;
import javafx.stage.Stage;

public class Driver extends Application {

	public static void main(String[] args) {
		User user = new User("Bob Saget", "helloMum");
		System.out.println("User created! " + user);
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		stage.show();
		
	}

}
