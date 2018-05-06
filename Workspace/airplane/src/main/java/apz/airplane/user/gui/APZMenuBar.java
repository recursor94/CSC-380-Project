package apz.airplane.user.gui;

import java.util.Optional;

import apz.airplane.user.gui.account.AccountInfoWindow;
import apz.airplane.user.gui.account.PasswordPromptWindow;
import apz.airplane.user.gui.account.PaymentWindow;
import apz.airplane.user.gui.booking.BookByLocation;
import apz.airplane.user.gui.booking.BookFlightByDateWindow;
import apz.airplane.user.gui.booking.BookingWindow;
import apz.airplane.user.gui.booking.ViewUpcomingTripWindow;
import apz.airplane.user.gui.home.BrowseScheduleWindow;
import apz.airplane.user.gui.home.HomeScreenWindow;
import apz.airplane.user.gui.viewtrips.TripViewWindow;
import apz.airplane.util.MessageBox;
import apz.car.gui.CarRentalViewWindow;
import javafx.application.Platform;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class APZMenuBar {
	
	private MenuBar menuBar;
	private Menu fileMenu, flightMenu, accountMenu, bookMenu, aboutMenu;
	private MenuItem returnHomeMenuItem, logoutItem, exitItem;
	private MenuItem bookATripItem, bookByDateItem, bookByDestinationItem;
	private MenuItem viewFlightItem, scheduleFlightItem;
	private MenuItem manageAccountItem, managePaymentItem, manageBookingItem, manageCarRentalItem;
	private MenuItem aboutClassItem, aboutProjectItem, aboutUsItem;
	
	public APZMenuBar() {
			initialize();
			content();
			actionEvents();
	}
	
	private void initialize() {
		menuBar = new MenuBar();
		
		fileMenu = new Menu("File");
		returnHomeMenuItem = new MenuItem("Homescreen");
		logoutItem = new MenuItem("Logout");
		exitItem = new MenuItem("Exit");
		
		flightMenu = new Menu("Flights");
		bookMenu = new Menu("Booking");
		bookATripItem = new MenuItem("Book a trip");
		bookByDateItem = new MenuItem("Book flight by date");
		bookByDestinationItem = new MenuItem("Book flight by location");
		viewFlightItem = new MenuItem("View my upcoming trips");
		scheduleFlightItem = new MenuItem("Current flight schedules");
		
		accountMenu = new Menu("My Account");
		manageAccountItem = new MenuItem("Information");
		managePaymentItem = new MenuItem("Payment");
		manageBookingItem = new MenuItem("Booking");
		manageCarRentalItem = new MenuItem("Car Rental");
		
		aboutMenu = new Menu("About");
		aboutClassItem = new MenuItem("Class");
		aboutProjectItem = new MenuItem("Project");
		aboutUsItem = new MenuItem("Team Members");
	}
	
	private void content() {
		bookMenu.getItems().addAll(bookATripItem, new SeparatorMenuItem(), bookByDateItem, bookByDestinationItem);
		fileMenu.getItems().addAll(returnHomeMenuItem, logoutItem, new SeparatorMenuItem(), exitItem);
		flightMenu.getItems().addAll(bookMenu, viewFlightItem, scheduleFlightItem);
		accountMenu.getItems().addAll(manageAccountItem, managePaymentItem, new SeparatorMenuItem(), manageBookingItem, manageCarRentalItem);
		aboutMenu.getItems().addAll(aboutClassItem, aboutProjectItem, aboutUsItem);
		menuBar.getMenus().addAll(fileMenu, flightMenu, accountMenu);
		
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
			new ViewUpcomingTripWindow();
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
		
		manageCarRentalItem.setOnAction(event -> {
			new CarRentalViewWindow();
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
		
		returnHomeMenuItem.setOnAction(event -> {
			new HomeScreenWindow();
		});
	}
}
