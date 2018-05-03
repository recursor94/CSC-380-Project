package apz.airplane.user.gui.account;

import apz.airplane.model.User;
import apz.airplane.user.gui.APZLauncher;
import apz.airplane.util.FilePath;
import apz.airplane.util.GuiApplication;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AccountInfoWindow implements GuiApplication {
	
	private ImageView img;
	private VBox mainPane;
	private GridPane gridPane;
	private Text headerText;
	private Button changeButton, deleteButton;
	private User uc;

	public AccountInfoWindow() {
		initialize();
		content();
		actionEvents();
		properties();
	}

	public void initialize() {
		img = new ImageView(new Image(FilePath.ACCOUNT_INFO_IMAGE));
		mainPane = new VBox(10);
		gridPane = new GridPane();
		headerText = new Text("Account Information");
		changeButton = new Button("Change Information");
		deleteButton = new Button("Delete Account");
		uc = APZLauncher.getCurrentUser();
	}

	public void content() {
		gridPane.add(new ImageView(new Image(FilePath.BULLET_POINT_IMAGE)), 1, 2);
		gridPane.add(new Label("Email: "), 2, 2);
		gridPane.add(new Label(uc.getEmail()), 3, 2);
		
		gridPane.add(new ImageView(new Image(FilePath.BULLET_POINT_IMAGE)), 1, 3);
		gridPane.add(new Label("Username: "), 2, 3);
		gridPane.add(new Label(uc.getUsername()), 3, 3);

		gridPane.add(new ImageView(new Image(FilePath.BULLET_POINT_IMAGE)), 1, 4);
		gridPane.add(new Label("Password: "), 2, 4);
		gridPane.add(new Label("******"), 3, 4);
		gridPane.add(changeButton, 3, 6);
		gridPane.add(deleteButton, 4, 6);
		
		mainPane.getChildren().addAll(new Label(), headerText, img, new Separator(), gridPane, new Label(), new Separator());
	}
	
	public void actionEvents() {
		
		for (int i = 0; i < gridPane.getChildren().size(); i++) 
			if (gridPane.getChildren().get(i) instanceof ImageView) {
				((ImageView) gridPane.getChildren().get(i)).setFitWidth(15);
				((ImageView) gridPane.getChildren().get(i)).setFitHeight(15);
		}
		
		changeButton.setOnAction(event -> {
			new AccountChangeWindow();
		});
		
		deleteButton.setOnAction(event -> {
			new AccountDeleteWindow();
		});
	}

	public void properties() {
		headerText.setFont(new Font(32));
		mainPane.setAlignment(Pos.TOP_CENTER);
		gridPane.setVgap(10);
		gridPane.setHgap(10);
		img.setFitWidth(235);
		img.setFitHeight(150);
		APZLauncher.getBorderPane().setCenter(mainPane);
		APZLauncher.getStage().setTitle("APZ Application - Account Information");
	}
}
