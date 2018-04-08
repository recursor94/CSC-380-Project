package apz.airplane.admin;

import java.util.ArrayList;

import apz.airplane.User;
import apz.airplane.UserController;
import apz.airplane.util.MessageBox;
import apz.airplane.util.State;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UserWindow {
	
	private GridPane mainPane;
//	private VBox mainPane = new VBox(10);
//	private HBox subPane = new HBox(10);
	private ArrayList<User> userList;
	private UserController uc;
	private TextField userField; 
	private PasswordField passField;
	private Button createButton, removeButton;
	private ListView<User> userView;
	
	public UserWindow() {
		initialize();
		content();
		actionEvents();
		properties();
	}
	
	private void initialize() {
		mainPane = new GridPane();
		userList = new ArrayList<>();
		userField = new TextField();
		passField = new PasswordField();
		createButton = new Button("Create");
		removeButton = new Button("Remove");
		userView = new ListView<>();
//		loadFile();
	}
	
	private void content() { // col row
		mainPane.add(new Label("Username"), 0, 0);
		mainPane.add(userField, 1, 0);
		
		mainPane.add(new Label("Password"), 0, 1);
		mainPane.add(passField, 1, 1);
		
		mainPane.add(createButton, 0, 2);
		mainPane.add(removeButton, 1, 2);
		
		mainPane.add(userView, 1, 3);
		
		mainPane.setVgap(10);
		mainPane.setHgap(10);
		mainPane.setAlignment(Pos.CENTER);
		
		
//		subPane.getChildren().addAll(createButton, removeButton);
//		mainPane.getChildren().addAll(new Label("Enter Username"), userField, new Label("Enter Password"), passField,
//				subPane, userList);
	}
	
	private void properties() {
		Stage stage = new Stage();
		stage.setTitle("Create Users");
		stage.setScene(new Scene(mainPane, 350, 500));
		stage.show();
	}
	
	private void actionEvents() {
//		createButton.setOnAction(event -> {
//			verifyInput(userField.getText(), passField.getText());
//		});
//		removeButton.setOnAction(event -> {
//			if(userList.getSelectionModel().getSelectedItem() != null) {
//				User user = userList.getSelectionModel().getSelectedItem();
//				uc.removeUser(user.getUsername());
//				apz.airplane.util.State.saveInformation(uc);
//				loadFile();
//			}
//			else {
//				MessageBox.message(AlertType.ERROR, "ERROR: No User Selected", "You must select a user to remove");
//			}
//		});
	}
	
	private void verifyInput(String username, String password) {
		if (uc.userExists(username)) {
			System.out.println("The username you chose already exists");
			MessageBox.message(AlertType.ERROR, "ERROR: The User Name Already Exists", "Please choose a different user name");
		} else {
			if (!(username.isEmpty()) && !(password.isEmpty())) {
				if (username.contains(" ")) 
					MessageBox.message(AlertType.ERROR, "Invalid User Name", "Your user name cannot contain the empty space character");
				else if (password.contains(" ")) 
					MessageBox.message(AlertType.ERROR, "Invalid Password", "Your password cannot contain the empty space character");
				else {
					uc.addUser(new User (username, password));
					State.saveInformation(uc);
					System.out.println("User successfully created!");
					MessageBox.message(AlertType.INFORMATION, "Successful User Creation", "Your account has been created!");
					System.out.println(uc);
//					loadFile();
				}	
			}
			else {
				MessageBox.message(AlertType.ERROR, "ERROR: You must enter a user name and password", "Please enter a user name and password");
			}
		}
	}
	
//	private void loadFile() {
//		uc = apz.airplane.util.State.loadInformation();
//		users = uc.getUserList();
//		if (!userList.getItems().isEmpty())
//			userList.getItems().clear();
//		for (int i = 0; i < users.size(); i++)
//			userList.getItems().add(users.get(i));
//	}
}
