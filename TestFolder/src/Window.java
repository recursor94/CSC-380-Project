import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Window extends Application {
	
	private HBox hbox = new HBox(10);
	private HBox hbox2 = new HBox(10);
	private VBox vbox = new VBox(10);

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Label text = new Label("Username: ");
		TextField textField = new TextField("ZGudlin");
		Button button = new Button("LOGIN");
		Label text2 = new Label("Password: ");
		TextField textField2 = new TextField("*****");
		Button button2 = new Button("EXIT");
		
		button.setOnAction(event -> {
			System.out.println(textField.getText());
		});
		
		button2.setOnAction(event -> {
			primaryStage.close();
		});
		
		hbox.getChildren().addAll(text, textField);
		hbox.setAlignment(Pos.CENTER_LEFT);
		hbox2.getChildren().addAll(text2, textField2, button, button2);
		hbox2.setAlignment(Pos.CENTER_LEFT);	
		
		vbox.getChildren().addAll(hbox,hbox2);
		
		vbox.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(vbox, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("THIS IS A TEST");
		primaryStage.show();
	}

}
