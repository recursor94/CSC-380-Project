package apz.airplane.admin;

import java.util.ArrayList;

import apz.airplane.model.User;
import apz.airplane.model.UserController;
import apz.airplane.util.MessageBox;
import apz.airplane.util.APZState;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserWindow {
	
	private SplitPane mainPane;
	private GridPane gridPane;
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
		mainPane = new SplitPane();
		gridPane = new GridPane();
		userList = new ArrayList<>();
		userField = new TextField();
		passField = new PasswordField();
		createButton = new Button("Create");
		removeButton = new Button("Remove");
		userView = new ListView<>();
		loadFile();
	}
	
	private void content() { // col row
		gridPane.add(new Label("Username"), 0, 0);
		gridPane.add(userField, 1, 0);
		
		gridPane.add(new Label("Password"), 0, 1);
		gridPane.add(passField, 1, 1);
		
		gridPane.add(createButton, 0, 2);
		
		gridPane.setVgap(10);
		gridPane.setHgap(10);
		gridPane.setAlignment(Pos.CENTER);
		
		VBox innerBox = new VBox(10);
		innerBox.getChildren().addAll(new Label("User List"), userView, removeButton);
		
		mainPane.setDividerPositions(.4);
		mainPane.getItems().addAll(gridPane, innerBox);
	}
	
	private void properties() {
		Stage stage = new Stage();
		stage.setTitle("Create Users");
		stage.setScene(new Scene(mainPane, 650, 150));
		stage.show();
	}
	
	private void actionEvents() {
		createButton.setOnAction(event -> {
			verifyInput(userField.getText(), passField.getText());
		});
		removeButton.setOnAction(event -> {
			if(userView.getSelectionModel().getSelectedItem() != null) {
				User user = userView.getSelectionModel().getSelectedItem();
				uc.removeUser(user.getUsername());
				APZState.saveInformation();
				loadFile();
			}
			else
				MessageBox.message(AlertType.ERROR, "ERROR: No User Selected", "You must select a user to remove");
		});
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
					APZState.saveInformation();
					System.out.println("User successfully created!");
					MessageBox.message(AlertType.INFORMATION, "Successful User Creation", "Your account has been created!");
					System.out.println(uc);
					loadFile();
				}	
			}
			else 
				MessageBox.message(AlertType.ERROR, "ERROR: You must enter a user name and password", "Please enter a user name and password");
		}
	}

	private void loadFile() {
		uc = apz.airplane.util.APZState.loadInformation();
		userList = uc.getUserList();
		if (!userView.getItems().isEmpty())
			userView.getItems().clear();
		for (int i = 0; i < userList.size(); i++)
			userView.getItems().add(userList.get(i));
	}
}
