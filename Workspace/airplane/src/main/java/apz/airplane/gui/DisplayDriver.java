package apz.airplane.gui;

import apz.airplane.UserController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DisplayDriver extends Application {
	
	//rootpane and usermanagement
	
	private static UserController uc;
	private static BorderPane bp;
	private static MenuBar menubar;
	private static Menu fileMenu;
	private static MenuItem exitItem;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static UserController getUserController() {
		return uc;
	}
	
	public static BorderPane getBorderPane() {
		return bp;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		uc = new UserController();
		bp = new BorderPane();
//		new LoginView(primaryStage);
//		new ViewFlightWindow(primaryStage);
//		new UserRegistrationView(primaryStage);
//		new CancelFlightView(primaryStage);
		new SearchFlightView(primaryStage);
		createMenuBar();
		
		
//		bp.setOnKeyPressed(event -> {
//			if (event.getCode() == KeyCode.A)
//				System.out.println("A was pressed");
//		});
	}
	
	private static void createMenuBar() {
		menubar = new MenuBar();
		fileMenu = new Menu("File");
		exitItem = new MenuItem("Exit");
		
		
		menubar.getMenus().add(fileMenu);
		fileMenu.getItems().add(exitItem);
		bp.setTop(menubar);
		
		exitItem.setOnAction(event -> {
			Platform.exit();
		});
	}


}
