package apz.airplane.admin;

import apz.airplane.util.GuiApplication;
import apz.airplane.util.MessageBox;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewsWindow implements GuiApplication {

	private Stage stage;
	private VBox mainPane;
	private TextArea updateField;
	private GridPane gridPane;
	private Button saveButton;
	private Text header;

	public NewsWindow() {
		initialize();
		content();
		actionEvents();
		properties();
	}

	public void initialize() {
		mainPane = new VBox(10);
		updateField = new TextArea(AdminState.readUpdate());
		gridPane = new GridPane();
		saveButton = new Button("Save Text");
		header = new Text("Update Home Screen Text");
	}

	public void content() {
		gridPane.add(updateField, 1, 0);
		mainPane.getChildren().addAll(header, new Separator(), new Label("Update the news update section"), gridPane,
				saveButton);
	}

	public void actionEvents() {
		saveButton.setOnAction(event -> {
			saveText();
		});
	}

	public void properties() {
		header.setFont(new Font(28));
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		mainPane.setAlignment(Pos.CENTER);
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Update Home Screen Text");
		stage.setScene(new Scene(mainPane, 400, 310));
		stage.setResizable(false);
		stage.show();
	}

	private void saveText() {
		if (updateField.getText().isEmpty())
			MessageBox.message(AlertType.ERROR, "ERROR", "You must enter an update");
		else {
			AdminState.writeUpdate(updateField.getText());
			MessageBox.message(AlertType.INFORMATION, "Text Saved", "Your update has been saved!");
			stage.close();
		}
	}
}
