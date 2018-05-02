package apz.airplane.user.gui;

import java.util.Optional;

import apz.airplane.user.gui.account.AccountInfoWindow;
import apz.airplane.user.gui.account.PasswordPromptWindow;
import apz.airplane.user.gui.account.PaymentWindow;
import apz.airplane.user.gui.booking.BookByLocation;
import apz.airplane.user.gui.booking.BookFlightByDateWindow;
import apz.airplane.user.gui.booking.BookingWindow;
import apz.airplane.user.gui.booking.CancelFlightWindow;
import apz.airplane.user.gui.home.BrowseScheduleWindow;
import apz.airplane.user.gui.home.HomeScreenWindow;
import apz.airplane.user.gui.viewtrips.TripViewWindow;
import apz.airplane.util.MessageBox;
import javafx.application.Platform;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Alert.AlertType;

public class APZMenuBar {
	
	private MenuBar menuBar;
	private Menu fileMenu, flightMenu, accountMenu, bookMenu, homeMenu;
	private MenuItem exitItem, logoutItem;
	private MenuItem bookATripItem, bookByDateItem, bookByDestinationItem;
	private MenuItem viewFlightItem, scheduleFlightItem;
	private MenuItem manageAccountItem, managePaymentItem, manageBookingItem;
	private MenuItem returnHomeMenuItem;
	
	
	public APZMenuBar() {
			initialize();
			content();
			actionEvents();
	}
	private void initialize() {
		menuBar = new MenuBar();
		fileMenu = new Menu("File");
		flightMenu = new Menu("Flights");
		accountMenu = new Menu("My Account");
		bookMenu = new Menu("Book a trip");

		homeMenu = new Menu("Home");
		
		bookATripItem = new MenuItem("Book a trip");
		bookByDateItem = new MenuItem("Book flight by date");
		bookByDestinationItem = new MenuItem("Book flight by destination");
		
		exitItem = new MenuItem("Exit");
		logoutItem = new MenuItem("Logout");
		viewFlightItem = new MenuItem("View/Manage my upcoming trips");
		scheduleFlightItem = new MenuItem("Current Schedules");
		
		manageAccountItem = new MenuItem("Information");
		managePaymentItem = new MenuItem("Payment");
		manageBookingItem = new MenuItem("Booking");
		
		returnHomeMenuItem = new MenuItem("Return to Homescreen");
	}
	
	private void content() {
		bookMenu.getItems().addAll(bookATripItem, new SeparatorMenuItem(), bookByDateItem, bookByDestinationItem);
		fileMenu.getItems().addAll(exitItem, new SeparatorMenuItem(), logoutItem);
		flightMenu.getItems().addAll(bookMenu, viewFlightItem, scheduleFlightItem);
		accountMenu.getItems().addAll(manageAccountItem, managePaymentItem, manageBookingItem);
		homeMenu.getItems().add(returnHomeMenuItem);
		menuBar.getMenus().addAll(fileMenu, flightMenu, accountMenu, homeMenu);
		
		APZLauncher.getBorderPane().setTop(menuBar);
	}
	
	private void actionEvents() {
		exitItem.setOnAction(event -> {
			Platform.exit();
		});
		
		logoutItem.setOnAction(event -> {
			Optional<ButtonType> result = MessageBox.message(AlertType.CONFIRMATION, "APZ Application", "Are you sure you want to logout?");
			if (result.get() == ButtonType.OK) {
				new LoginWindow();
				APZLauncher.getBorderPane().setTop(null);
			}
		});
		
		viewFlightItem.setOnAction(event -> {
			new CancelFlightWindow();
		});
		
		bookByDateItem.setOnAction(event -> {
			new BookFlightByDateWindow();
		});
		
		manageAccountItem.setOnAction(event -> {
			new AccountInfoWindow();
		});
		
		managePaymentItem.setOnAction(event -> {
			new PasswordPromptWindow(0);
			new PaymentWindow();
		});
		
		manageBookingItem.setOnAction(event -> {
			new TripViewWindow();
		});
		
		bookByDestinationItem.setOnAction(event -> {
			new BookByLocation();
		});
		
		bookATripItem.setOnAction(event -> {
			new BookingWindow();
		});
		
		returnHomeMenuItem.setOnAction(event -> {
			new HomeScreenWindow();
		});
		scheduleFlightItem.setOnAction(event -> {
			new BrowseScheduleWindow();
		});
	}

}