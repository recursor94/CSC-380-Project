package jimmy.pack;

import java.util.ArrayList;

import apz.airplane.model.Payment;
import apz.airplane.user.APZLauncher;
import apz.airplane.user.PaymentAddWindow;
import apz.airplane.util.APZState;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PaymentWindow {
	
	private Text header;
	private VBox mainPane;
	private GridPane gridPane; 
	private ComboBox<Payment> paymentBox;
	private Button createButton, removeButton;

	
	// please enter password first
	public PaymentWindow() {
		initialize();
		content();
		actionEvents();
		properties();
	}
	
	private void initialize() {
		header = new Text("Payment Information");
		mainPane = new VBox(10);
		gridPane = new GridPane();
		paymentBox = new ComboBox<>();
		createButton = new Button("Create new payment");
		removeButton = new Button("Remove selected payment");
	}

	private void content() {
		header.setFont(new Font(32));
		populateComboBox();
		
		
		gridPane.setVgap(15);
		gridPane.setHgap(15);
		
		
		gridPane.add(new Label("Payment Method: "), 0, 0);
		gridPane.add(paymentBox, 1, 0);
		gridPane.add(createButton, 0, 1);
		gridPane.add(removeButton, 1, 1);
		mainPane.getChildren().addAll(header, gridPane);
		mainPane.setAlignment(Pos.TOP_CENTER);
		gridPane.setAlignment(Pos.TOP_CENTER);
		
		
		paymentBox.setOnAction(event -> {
			if (paymentBox.getSelectionModel().getSelectedItem() == null) {
				removeButton.setDisable(true);
			} else {
				removeButton.setDisable(false);
			}
		});
		
		
		if (paymentBox.getSelectionModel().getSelectedItem() == null) {
			removeButton.setDisable(true);
		}
		
	}

	private void actionEvents() {
		createButton.setOnAction(event -> {
			new PaymentAddWindow();
		});
		
		removeButton.setOnAction(event -> {
			Payment payment = paymentBox.getSelectionModel().getSelectedItem();
			APZLauncher.getCurrentUser().getPaymentInformation().remove(payment);
			APZState.saveInformation();
			populateComboBox();
		});
	}

	private void properties() {
		APZLauncher.getBorderPane().setCenter(mainPane);
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
