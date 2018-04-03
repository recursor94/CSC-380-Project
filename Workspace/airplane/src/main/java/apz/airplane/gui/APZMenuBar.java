package apz.airplane.gui;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class APZMenuBar {
	
	private MenuBar menuBar;
	private Menu fileMenu, flightMenu, accountMenu;
	private MenuItem exitItem;
	private MenuItem bookFlightItem, viewFlightItem, scheduleFlightItem;
	private MenuItem manageAccountItem, managePaymentItem, manageBookingItem;
	
	
	public APZMenuBar() {
		//check if top already has menubar?
			initialize();
			content();
			actionEvents();
	}
	private void initialize() {
		menuBar = new MenuBar();
		fileMenu = new Menu("File");
		flightMenu = new Menu("Flights");
		accountMenu = new Menu("My Account");
		
		exitItem = new MenuItem("Exit");
		
		bookFlightItem = new MenuItem("Book a trip");
		viewFlightItem = new MenuItem("View my trips");
		scheduleFlightItem = new MenuItem("Current Schedules");
		
		manageAccountItem = new MenuItem("Information");
		managePaymentItem = new MenuItem("Payment");
		manageBookingItem = new MenuItem("Booking");
	}
	
	private void content() {
		menuBar.getMenus().addAll(fileMenu, flightMenu, accountMenu);
		
		fileMenu.getItems().add(exitItem);
		flightMenu.getItems().addAll(bookFlightItem, viewFlightItem, scheduleFlightItem);
		accountMenu.getItems().addAll(manageAccountItem, managePaymentItem, manageBookingItem);
		
		APZLauncher.getBorderPane().setTop(menuBar);
	}
	
	private void actionEvents() {
		exitItem.setOnAction(event -> {
			Platform.exit();
		});
	}

}
