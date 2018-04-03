package apz.airplane.admin;

import java.util.ArrayList;

import apz.airplane.User;
import apz.airplane.UserController;
import apz.airplane.util.MessageBox;
import apz.airplane.util.VerifyUserInput;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserManagement {
	
	private VBox mainPane = new VBox(10);
	private HBox subPane = new HBox(10);
	private ArrayList<User> users = new ArrayList<>();
	private UserController uc;
	private ListView<User> userList = new ListView<>();
	private TextField userField, passField;
	private Button createButton, removeButton, viewButton;
	
	public UserManagement() {
		initialize();
		content();
		actionEvents();
		properties();
	}
	
	private void initialize() {
		userField = new TextField();
		passField = new TextField();
		createButton = new Button("Create");
		removeButton = new Button("Remove");
		viewButton = new Button("View");
		loadFile();
	}
	
	private void content() {
		
		subPane.getChildren().addAll(createButton, removeButton, viewButton);
		mainPane.getChildren().addAll(new Label("Enter Username"), userField, new Label("Enter Password"), passField,
				subPane, userList);
	}
	
	private void properties() {
		Stage stage = new Stage();
		stage.setScene(new Scene(mainPane, 350, 500));
		stage.show();
	}
	
	private void actionEvents() {
		createButton.setOnAction(event -> {
			if (!userField.getText().isEmpty() && !passField.getText().isEmpty()) {
				VerifyUserInput.verifyInput(userField.getText(), passField.getText(), uc);
				loadFile();
			}
			
			else
				MessageBox.message(AlertType.ERROR, "ERROR: You must enter a user name and password", "Please enter a user name and password");
		});
		removeButton.setOnAction(event -> {
			if(userList.getSelectionModel().getSelectedItem() != null) {
				User user = userList.getSelectionModel().getSelectedItem();
				uc.removeUser(user.getUsername());
				apz.airplane.util.State.saveInformation(uc);
				loadFile();
			}
			else {
				MessageBox.message(AlertType.ERROR, "ERROR: No User Selected", "You must select a user to remove");
			}
		});
		
		viewButton.setOnAction(event -> {
			System.out.println("List Of Users:");
			for(int i = 0; i < users.size(); i++) {
			System.out.println(users.get(i).getUsername());
			}
		});
	}
	
	private void loadFile() {
		uc = apz.airplane.util.State.loadInformation();
		users = uc.getUserList();
		if (!userList.getItems().isEmpty())
			userList.getItems().clear();
		for (int i = 0; i < users.size(); i++)
			userList.getItems().add(users.get(i));
		
	}
}
