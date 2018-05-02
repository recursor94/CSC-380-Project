package apz.airplane.util;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class JUtility extends Application {

	private ImageView img;
	private Text text;
	private StackPane mainPane;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		mainPane = new StackPane();
		text = new Text("\n\n\n\n\n\n\nGive Jimmy a good grade.");
		img = new ImageView("File:resource/pantaleev.png");

		text.setFont(new Font(32));

		RotateTransition rt = new RotateTransition(Duration.millis(3000), img);
		rt.setByAngle(360);
		rt.setCycleCount(Animation.INDEFINITE);
		rt.play();

		FadeTransition ft = new FadeTransition(Duration.millis(3000), text);
		ft.setFromValue(1.0);
		ft.setToValue(0);
		ft.setCycleCount(Animation.INDEFINITE);
		ft.setAutoReverse(true);

		ft.play();

		mainPane.getChildren().addAll(text, img);
		
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.setScene(new Scene(mainPane, 500, 500, Color.TRANSPARENT));
		primaryStage.show();
		
		
		primaryStage.getScene().setOnKeyPressed(event -> {
			primaryStage.close();
		});
	}

}
