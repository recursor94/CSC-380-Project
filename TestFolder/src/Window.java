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
	private VBox vbox = new VBox(10);

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Label text = new Label("Username: ");
		TextField textField = new TextField("ZGudlin");
		Button button = new Button("LOGIN");
		
		button.setOnAction(event -> {
			System.out.println(text.getText());
		});
		
		hbox.getChildren().addAll(text, textField, button);
		
		hbox.setAlignment(Pos.CENTER);
		
		Label text2 = new Label("Password: ");
		TextField textField2 = new TextField("*****");
		
		vbox.getChildren().addAll(hbox, text2, textField2);
		
		vbox.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(vbox, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("THIS IS A TEST");
		primaryStage.show();
	}

}
