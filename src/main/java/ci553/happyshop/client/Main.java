package ci553.happyshop.client;

import ci553.happyshop.client.customer.*;

import ci553.happyshop.client.emergency.EmergencyExit;
import ci553.happyshop.client.orderTracker.OrderTracker;
// import ci553.happyshop.client.orderTracker.OrderTrackerClient; // Not strictly needed here, but you can keep it
import ci553.happyshop.client.picker.PickerController;
import ci553.happyshop.client.picker.PickerModel;
import ci553.happyshop.client.picker.PickerView;

import ci553.happyshop.client.warehouse.*;
import ci553.happyshop.orderManagement.OrderHub;
import ci553.happyshop.storageAccess.DatabaseRW;
import ci553.happyshop.storageAccess.DatabaseRWFactory;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.application.Platform; // NEW: Added for Platform.exit()

// === NEW IMPORTS FOR LOGIN/REGISTER ===
import ci553.happyshop.client.login_register.LoginRegisterController;
import ci553.happyshop.client.login_register.LoginRegisterModel;
import ci553.happyshop.client.login_register.LoginRegisterView;
// ======================================


/**
 * The Main JavaFX application class. The Main class is executable directly.
 * It serves as a foundation for UI logic and starts all the clients (UI) in one go.
 *
 * This class launches all standalone clients (Customer, Picker, OrderTracker, Warehouse, EmergencyExit)
 * and links them together into a fully working system.
 *
 * It performs essential setup tasks, such as initializing the order map in the OrderHub
 * and registering observers.
 *
 * Note: Each client type can be instantiated multiple times (e.g., calling startCustomerClient() as many times as needed)
 * to simulate a multi-user environment, where multiple clients of the same type interact with the system concurrently.
 *
 * @version 1.0
 * @author  Shine Shan University of...
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        // The Main class is executable directly.
        // It serves as a foundation for UI logic and starts all the clients (UI) in one go.
        //
        // Note: Each client type can be instantiated multiple times (e.g., calling startCustomerClient() as many times as needed)
        // to simulate a multi-user environment, where multiple clients of the same type interact with the system concurrently.

        // 1. starts the EmergencyExit GUI, which used to close the entire application immediatelly
        startEmergencyExit();

        // 2. NEW: Start the Login/Registration Screen and WAIT for the user's role
        String userRole = runLoginScreenAndGetRole();

        // 3. NEW: If login was successful, proceed to launch the correct client.
        if (userRole != null) {
            // Initialise the order map in the OrderHub
            OrderHub.getOrderHub().initOrderMap();

            // Start the client based on the user's role
            switch (userRole) {
                case "customer":
                    startCustomerClient();
                    break;
                case "picker":
                    startPickerClient();
                    break;
                case "warehouse":
                    startWarehouseClient();
                    break;
                case "tracker": // Assuming OrderTracker is launched for a "tracker" role
                    startOrderTrackerClient(); // <-- The method call that caused the error is now resolved below!
                    break;
                default:
                    // Fallback if the role isn't recognized
                    System.out.println("Unknown role: " + userRole + ". Shutting down.");
                    Platform.exit();
                    System.exit(0);
                    break;
            }
        } else {
            // If userRole is null, the user closed the login window without logging in.
            System.out.println("Login window closed. Shutting down application.");
            Platform.exit(); // Gracefully exit JavaFX
            System.exit(0);  // Forcefully shut down JVM
        }
    }


    /**
     * Initializes the Customer client's Model, View, and Controller, and links them together for communication.
     * It also creates the DatabaseRW instance via the DatabaseRWFactory and injects it into the CustomerModel.
     * Once the components are linked, the customer interface (view) is started.
     *
     * Note: This method is designed to start the client on a new, separate stage (window).
     */
    private void startCustomerClient() {
        CustomerView cusView = new CustomerView();
        CustomerController cusController = new CustomerController();
        CustomerModel cusModel = new CustomerModel();
        DatabaseRW databaseRW = DatabaseRWFactory.createDatabaseRW();

        // Link controller, model, and view
        cusView.cusController = cusController;
        cusController.cusModel = cusModel;
        cusModel.cusView = cusView;
        cusModel.databaseRW = databaseRW;

        // Start view on a new stage
        cusView.start(new Stage());

        // create dependent views that need window info (optional for your existing code)
        // RemoveProductNotifier removeProductNotifier = new RemoveProductNotifier();
        // removeProductNotifier.cusView = cusView;
        // cusModel.removeProductNotifier = removeProductNotifier;
    }


    /**
     * Initializes the Picker client's Model, View, and Controller, and links them together for communication.
     * It also registers the PickerModel with the OrderHub to receive order notifications.
     * Once the components are linked, the picker interface (view) is started.
     */
    private void startPickerClient() {
        PickerModel pickerModel = new PickerModel();
        PickerView pickerView = new PickerView();
        PickerController pickerController = new PickerController();

        // Link controller, model, and view
        pickerView.pickerController = pickerController;
        pickerController.pickerModel = pickerModel;
        pickerModel.pickerView = pickerView;

        pickerModel.registerWithOrderHub();
        pickerView.start(new Stage());
    }


    /**
     * Starts the OrderTracker client.
     * This client is simple and does not follow the MVC pattern. It only registers with the OrderHub
     * to receive order status notifications.
     */
    // NEW: DEFINITION ADDED TO RESOLVE "cannot resolve method" ERROR
    private void startOrderTrackerClient() {
        OrderTracker orderTracker = new OrderTracker();
        orderTracker.registerWithOrderHub();
    }


    /**
     * Initializes the Warehouse client's Model, View, and Controller,and links them together for communication.
     * It also creates the DatabaseRW instance via the DatabaseRWFactory and injects it into the Model.
     * Once the components are linked, the warehouse interface (view) is started.
     *
     * Also creates the dependent HistoryWindow and AlertSimulator,
     * which track the position of the Warehouse window and are triggered by the Model when needed.
     * These components are linked after launching the Warehouse interface.
     */
    private void startWarehouseClient(){
        WarehouseView view = new WarehouseView();
        WarehouseController controller = new WarehouseController();
        WarehouseModel model = new WarehouseModel();
        DatabaseRW databaseRW = DatabaseRWFactory.createDatabaseRW();

        // Link controller, model, and view and start view
        view.controller = controller;
        controller.model = model;
        model.view = view;
        model.databaseRW = databaseRW;
        view.start(new Stage());

        //create dependent views that need window info
        HistoryWindow historyWindow = new HistoryWindow();
        AlertSimulator alertSimulator = new AlertSimulator();

        // Link after start
        model.historyWindow = historyWindow;
        model.alertSimulator = alertSimulator;
        historyWindow.warehouseView = view;
        alertSimulator.warehouseView = view;
    }


    //starts the EmergencyExit GUI, - used to close the entire application immediatelly
    private void startEmergencyExit(){
        EmergencyExit.getEmergencyExit();
    }


    /**
     * NEW HELPER METHOD: Sets up and runs the simple Login/Register MVC system.
     * It uses the LoginRegisterView as a modal window, which stops the Main application
     * until the user successfully logs in or closes the window.
     * @return The job role ("customer", "picker", etc.) of the logged-in user, or null if the window was closed.
     */
    private String runLoginScreenAndGetRole() {
        // 1. Make the new beginner-style MVC parts
        LoginRegisterView theScreen = new LoginRegisterView();
        LoginRegisterController theBoss = new LoginRegisterController();
        LoginRegisterModel theBrains = new LoginRegisterModel();

        // 2. Link them all together so they can talk!
        theScreen.theController = theBoss;
        theBoss.theModel = theBrains;
        theBoss.theView = theScreen;

        // NOTE: If the LoginRegisterModel needs the DatabaseRW object, inject it here too!
        // theBrains.databaseRW = DatabaseRWFactory.createDatabaseRW();

        // 3. Show the login screen and wait for the result
        return theScreen.showAndWaitAndGetRole();
    }
}