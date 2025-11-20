package ci553.happyshop.client.login_register;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * THE LOGIN SCREEN CLASS WHICH SERVERS AS A LOGIN SCREEN (OBVIOUSLY) AND A WINDOW MANAGER
 */
public class LoginView {

    public LoginRegisterController theController; // Link to the controller
    public RegisterView theRegisterView; //Link to registerview.java file for easy access of the register screen

    private final Stage loginWindow = new Stage(); // the login/register window
    private final Scene loginScene; // The single scene for the window

    // Login UI components
    private final TextField usernameBox = new TextField(); //the box to type their username
    private final PasswordField passwordBox = new PasswordField(); //the box to type their password
    private final Label messageLabel = new Label(" Please Log In or Register"); //login message
    private final VBox loginRootLayout;

    private String roleAfterLogin = null; //moves the user's role to Main.java

    /**
     * MAIN SETUP OF THE LOGIN SCREEN
     */
    public LoginView() {
        // --- 1. SETUP LOGIN UI ---
        // Creates typing boxes for the username and password fields
        usernameBox.setPromptText("Type your username");
        passwordBox.setPromptText("Type your password");

        //Creates a button to click one the user typed in their username and password
        Button loginButton = new Button("Login");
        //Creates the clickable link to the registration page
        Hyperlink registerLink = new Hyperlink("New User? Register Here");

        //Tells the controls what to do when clicked
        loginButton.setOnAction(this::loginButtonClicked);
        registerLink.setOnAction(this::registerLinkClicked);



        loginButton.setStyle("-fx-font-size: 14pt; -fx-background-color: lightgreen;"); //Decided to add a light-green colour to the login button

        registerLink.setStyle("-fx-font-size: 10pt; -fx-text-fill: blue; -fx-underline: true;"); //register link styling


        //Evrything here will be pulled into a window also called a vbox
        loginRootLayout = new VBox(20); // 20 is the spacing between items
        loginRootLayout.setPadding(new Insets(30)); // Space around the edges
        loginRootLayout.setAlignment(Pos.CENTER);
        loginRootLayout.getChildren().addAll(
                messageLabel,
                new Label("Username:"),
                usernameBox,
                new Label("Password:"),
                passwordBox,
                loginButton,
                registerLink
        );

      //The setup scene for the window including the window size and the name of the store
        loginScene = new Scene(loginRootLayout, 600, 600);
        loginWindow.setScene(loginScene);
        loginWindow.setTitle("HappyShop - Log In");
    }

    // This method shows the login window and blocks the Main application until it's closed.
    public String showAndWaitAndGetRole() {
        // This makes the login window a must-do item! Main.java stops until this window is closed.
        loginWindow.initModality(Modality.APPLICATION_MODAL);

        loginWindow.showAndWait(); // Execution STOPS HERE until the window closes!

        return roleAfterLogin; // Returns the role we set in loginSuccessAndClose()
    }

   //This method is build to run when the login button is clicked
    private void loginButtonClicked(ActionEvent event) {
        if (theController == null) {
            System.err.println("Controller not set up yet!");
            return;
        }

        // 1. Tell the controller the input values
        theController.setInput(usernameBox.getText(), passwordBox.getText());

        // 2. Tell the controller to login
        theController.doLoginAction();
    }

 // This method runs when the 'register here' link is clicked
    private void registerLinkClicked(ActionEvent event) {
        showRegisterScreen();
    }



    public void showLoginScreen() {
        loginScene.setRoot(loginRootLayout);
        loginWindow.setTitle("HappyShop - Log In");

        usernameBox.clear();
        passwordBox.clear();
        messageLabel.setText(" Please Log In or Register");
        messageLabel.setTextFill(Color.BLACK);
    }

    //This method switches the scene to the registerview file
    public void showRegisterScreen() {
        if (theRegisterView == null) {
            System.err.println("RegisterView not linked!");
            return;
        }
        loginScene.setRoot(theRegisterView.getRootLayout());
        loginWindow.setTitle("HappyShop - Register");
    }


    //   ======Methods the controller will use=======

    // This paragraph displays a welcome message to the screen or an error message
    public void showErrorMessage(String message) {
        messageLabel.setText(message);
        messageLabel.setTextFill(Color.RED); // Ensures the error messages are displayed in red
        messageLabel.setStyle("-fx-font-size: 16pt; -fx-font-weight: bold;");
    }

    // if the login works, it closes the window and unlocks main.java
    public void loginSuccessAndClose(String role) {
        this.roleAfterLogin = role; // Save the successful role
        loginWindow.close();        // Close the window, letting Main.java run again!
    }
}