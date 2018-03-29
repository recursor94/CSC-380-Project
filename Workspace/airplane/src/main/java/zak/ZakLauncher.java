package zak;

import java.util.Optional;

import apz.airplane.Booking;
import apz.airplane.UserController;
import apz.airplane.util.MessageBox;
import apz.airplane.util.State;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ZakLauncher extends Application {
	
	//rootpane and usermanagement
	
	private static UserControllerZak uc;
	private static BorderPane bp;
	private static MenuBar menubar;
	private static Menu fileMenu;
	private static MenuItem exitItem;
	private static MenuItem logoutItem;
	private static Stage stage;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		uc = StateUtil.loadInformation();
		bp = new BorderPane();
		createMenuBar();
		
		primaryStage.setScene(new Scene(bp, 300, 400));
		primaryStage.initStyle(StageStyle.UTILITY);
		primaryStage.setTitle("APZ Airplane Application");
		primaryStage.show();
		
		new LoginWindowZak();
	}
	
	
	private static void createMenuBar() {
		menubar = new MenuBar();
		fileMenu = new Menu("File");
		exitItem = new MenuItem("Exit");
		logoutItem = new MenuItem("Logout");
		
		
		menubar.getMenus().add(fileMenu);
		fileMenu.getItems().addAll(exitItem, logoutItem);
		bp.setTop(menubar);
		
		exitItem.setOnAction(event -> {
			Platform.exit();
		});
		
		logoutItem.setOnAction(event -> {
			Optional<ButtonType> result = MessageBox.message(AlertType.CONFIRMATION, "Logout Confirmation",
					"Do you wish to logout?");
			if (result.get() == ButtonType.OK) {
				new LoginWindowZak();
			}
		});
	}

	public static UserControllerZak getUserController() {
		return uc;
	}
	
	public static BorderPane getBorderPane() {
		return bp;
	}
	
	public static Stage getStage() {
		return stage;
	}

}
