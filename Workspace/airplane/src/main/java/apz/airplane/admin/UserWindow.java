package apz.airplane.admin;

import java.util.ArrayList;

import apz.airplane.model.Flight;
import apz.airplane.model.User;
import apz.airplane.model.UserController;
import apz.airplane.util.APZState;
import apz.airplane.util.MessageBox;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
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
	private TextField userField, emailField;
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
		emailField = new TextField();
		passField = new PasswordField();
		createButton = new Button("Create");
		removeButton = new Button("Remove");
		userView = new ListView<>();

		loadFile();
	}

	private void content() { // col row
		removeButton.setDisable(true);
		
		gridPane.add(new Label("Username"), 0, 0);
		gridPane.add(userField, 1, 0);
		
		gridPane.add(new Label("Email"), 0, 1);
		gridPane.add(emailField, 1, 1);

		gridPane.add(new Label("Password"), 0, 2);
		gridPane.add(passField, 1, 2);

		gridPane.add(createButton, 0, 3);

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
		stage.setScene(new Scene(mainPane, 750, 175));
		stage.show();
	}

	private void actionEvents() {
		createButton.setOnAction(event -> {
			verifyInput(userField.getText(), emailField.getText(), passField.getText());
		});
		removeButton.setOnAction(event -> {
			if (userView.getSelectionModel().getSelectedItem() != null) {
				ArrayList<Flight> flightList = AdminState.loadFlights();
				User user = userView.getSelectionModel().getSelectedItem();
				for(int i= 0; i < user.getTripList().size(); i ++) {
					for (int ind = 0; ind < flightList.size(); ind++) {
						if (flightList.get(i).getFlightNum() == user.getTripList().get(ind).getFlight().getFlightNum()) 
							flightList.set(i, user.getTripList().get(ind).getFlight());
					}
					user.removeTrip(user.getTripList().get(i).getFlight());
				}
				uc.removeUser(user.getUsername());
				AdminState.saveFlight(flightList);
				AdminState.saveInformation(uc);
				loadFile();
			} else
				MessageBox.message(AlertType.ERROR, "ERROR: No User Selected", "You must select a user to remove");
		});
		
		userView.getSelectionModel().selectedItemProperty().addListener(event -> {
			removeButton.setDisable(false);
		});
		
		userView.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ESCAPE) {
				userView.getSelectionModel().clearSelection();
				removeButton.setDisable(true);
			}
		});
	}

	private void verifyInput(String username, String email, String password) {
		if (uc.userExists(username)) {
			System.out.println("The username you chose already exists");
			MessageBox.message(AlertType.ERROR, "ERROR: The User Name Already Exists",
					"Please choose a different user name");
		} else {
			if (!(username.isEmpty()) && !(password.isEmpty()) && !(email.isEmpty())) {
				if (username.contains(" "))
					MessageBox.message(AlertType.ERROR, "Invalid User Name",
							"Your user name cannot contain the empty space character");
				else if (password.contains(" "))
					MessageBox.message(AlertType.ERROR, "Invalid Password",
							"Your password cannot contain the empty space character");
				else if (!emailField.getText().contains("@") && !emailField.getText().contains("."))
					MessageBox.message(AlertType.ERROR, "Invalid Email", "Your email must be properly formatted!");
				else {
					uc.addUser(new User(email, username, password));
					AdminState.saveInformation(uc);
					System.out.println("User successfully created!");
					MessageBox.message(AlertType.INFORMATION, "Successful User Creation",
							"Your account has been created!");
					System.out.println(uc);
					loadFile();
				}
			} else
				MessageBox.message(AlertType.ERROR, "ERROR: You must enter a user name. email and password",
						"Please enter a user name, email and password");
		}
	}

	private void loadFile() {
		uc = AdminState.loadInformation();

		userList = uc.getUserList();
		if (!userView.getItems().isEmpty())
			userView.getItems().clear();
		for (int i = 0; i < userList.size(); i++)
			userView.getItems().add(userList.get(i));
	}
}
