package apz.airplane.user.gui.account;

import java.util.ArrayList;

import apz.airplane.model.Payment;
import apz.airplane.user.gui.APZLauncher;
import apz.airplane.util.APZState;
import apz.airplane.util.FilePath;
import apz.airplane.util.GuiApplication;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PaymentWindow implements GuiApplication {
	
	private ImageView img;
	private Text headerText;
	private VBox mainPane;
	private GridPane gridPane; 
	private ComboBox<Payment> paymentBox;
	private Button createButton, removeButton;

	public PaymentWindow() {
		initialize();
		content();
		actionEvents();
		properties();
	}
	
	public void initialize() {
		img = new ImageView(new Image(FilePath.PAYMENT_INFO_IMAGE));
		headerText = new Text("Payment Information");
		mainPane = new VBox(10);
		gridPane = new GridPane();
		paymentBox = new ComboBox<>();
		createButton = new Button("Create new payment");
		removeButton = new Button("Remove selected payment");
	}

	public void content() {
		populateComboBox();
		
		gridPane.add(new Label("Payment Method: "), 0, 0);
		gridPane.add(paymentBox, 1, 0);
		gridPane.add(createButton, 0, 1);
		gridPane.add(removeButton, 1, 1);
		mainPane.getChildren().addAll(new Label(), headerText, img, new Separator(), gridPane, new Separator());
	}

	public void actionEvents() {
		createButton.setOnAction(event -> {
			new PaymentAddWindow();
		});
		
		removeButton.setOnAction(event -> {
			Payment payment = paymentBox.getSelectionModel().getSelectedItem();
			APZLauncher.getCurrentUser().getPaymentInformation().remove(payment);
			APZState.saveInformation();
			populateComboBox();
		});
		
		paymentBox.setOnAction(event -> {
			if (paymentBox.getSelectionModel().getSelectedItem() == null)
				removeButton.setDisable(true);
			else 
				removeButton.setDisable(false);
		});
		
		if (paymentBox.getSelectionModel().getSelectedItem() == null)
			removeButton.setDisable(true);
	}

	public void properties() {
		headerText.setFont(new Font(32));
		
		img.setFitWidth(250);
		img.setFitHeight(150);
		
		gridPane.setVgap(15);
		gridPane.setHgap(15);
		
		mainPane.setAlignment(Pos.TOP_CENTER);
		gridPane.setAlignment(Pos.TOP_CENTER);
		
		APZLauncher.getBorderPane().setCenter(mainPane);
		APZLauncher.getStage().setTitle("APZ Application - Payment Methods");
	}
	
	private void populateComboBox() {
		paymentBox.getItems().clear();
		ArrayList<Payment> payList = APZLauncher.getCurrentUser().getPaymentInformation();
		for (int i = 0; i < payList.size(); i++) 
			paymentBox.getItems().add(payList.get(i));
		
		if (!payList.isEmpty())
			paymentBox.setValue(payList.get(0));
		else 
			paymentBox.setDisable(true);
	}
}
