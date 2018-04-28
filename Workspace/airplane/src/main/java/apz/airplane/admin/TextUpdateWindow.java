package apz.airplane.admin;

import apz.airplane.util.FilePath;
import apz.airplane.util.MessageBox;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TextUpdateWindow {
	
	private Stage stage;
	private ImageView img;
	private VBox mainPane;
	private TextField updateField;
	private GridPane gridPane;
	private Button saveButton;
	private Text header;
	
	public TextUpdateWindow() {
		initialize();
		content();
		actionEvents();
		properties();
		
	}
	
	public void initialize() {
		img = new ImageView(new Image(FilePath.LOGIN_IMAGE));
		mainPane = new VBox(10);
		updateField = new TextField();
		gridPane = new GridPane();
		saveButton = new Button("Save Text");
		header = new Text("Update Home Screen Text");
	}
	
	public void content() {
		
		img.setFitWidth(100);
		img.setFitHeight(100);
		header.setFont(new Font(28));
		
		gridPane.setAlignment(Pos.CENTER);
		
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		
		gridPane.add(new Label("Enter Update Text: "), 0, 0);
		gridPane.add(updateField, 1, 0);
		
		mainPane.getChildren().addAll(header, img, new Separator(), gridPane, saveButton);
		
		mainPane.setAlignment(Pos.CENTER);
	}
	
	public void actionEvents() {
		saveButton.setOnAction(event -> {
			saveText();
		});
		
		mainPane.setOnKeyPressed(event -> {
			if(event.getCode() == KeyCode.ENTER)
				saveText();
		});
	}
	
	public void properties() {
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Update Home Screen Text");
		stage.setScene(new Scene(mainPane, 350, 250));
		stage.setResizable(false);
		stage.show();
	}
	
	private void saveText() {
		if(updateField.getText().isEmpty())
			MessageBox.message(AlertType.ERROR, "ERROR", "You must enter an update");
		else {
			AdminState.saveUpdate(updateField.getText());
			System.out.println(AdminState.loadUpdate());
			MessageBox.message(AlertType.INFORMATION, "Text Saved", "Your update has been saved!");
			stage.close();
		}
	}

}
