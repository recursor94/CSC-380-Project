package apz.airplane.gui.test;

import apz.airplane.UserController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DisplayDriver extends Application {
	
	//rootpane and usermanagement
	
	private static UserController uc;
	private static BorderPane bp;
	private static MenuBar menubar;
	private static Menu fileMenu;
	private static MenuItem exitItem;
	private static Stage stage;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static UserController getUserController() {
		return uc;
	}
	
	public static BorderPane getBorderPane() {
		return bp;
	}
	
	public static Stage getStage() {
		return stage;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		uc = UserControlSaveState.loadInformation();
		bp = new BorderPane();
		createMenuBar();
		
		new LoginView();
		
		primaryStage.setScene(new Scene(bp, 300, 400));
		primaryStage.initStyle(StageStyle.UTILITY);
		primaryStage.setTitle("APZ Airplane Application");
		primaryStage.show();
		
		
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