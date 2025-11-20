package ci553.happyshop.client.login_register;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Hyperlink; // New import for a hyperlink
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class LoginRegisterView {

    public LoginRegisterController theController; // Link to the controller


    private final Stage loginWindow = new Stage(); // the login/register window


    private final TextField usernameBox = new TextField(); //the box to type their username
    private final PasswordField passwordBox = new PasswordField(); //the box to type their password

    private final Label messageLabel = new Label(" Please Log In or Register"); //login message


    private String roleAfterLogin = null; //moves the user's role to Main.java

    /**
     * MAIN SETUP OF THE LOGIN SCREEN
     */
    public LoginRegisterView() {
        // Creates typing boxes for the username and password fields
        usernameBox.setPromptText("Type your username");
        passwordBox.setPromptText("Type your password");

        // Creates a button to click one the user typed in their username and password
        Button loginButton = new Button("Login");


        // NEW LINE 43: Create a clickable link for registration
        Hyperlink registerLink = new Hyperlink("New User? Register Here");


        // 3. Tell the buttons/link to call our 'buttonClicked' method
        loginButton.setOnAction(this::buttonClicked);
        registerLink.setOnAction(this::linkClicked);


        loginButton.setStyle("-fx-font-size: 14pt; -fx-background-color: lightgreen;");
        registerLink.setStyle("-fx-font-size: 10pt; -fx-text-fill: blue; -fx-underline: true;");



        VBox rootLayout = new VBox(20); // 20 is the spacing between items
        rootLayout.setPadding(new Insets(30)); // Space around the edges
        rootLayout.setAlignment(Pos.CENTER);
        rootLayout.getChildren().addAll(
                messageLabel,
                new Label("Username:"),
                usernameBox,
                new Label("Password:"),
                passwordBox,
                loginButton,
                registerLink
        );

        // 5. Setup the whole scene and window
        Scene loginScene = new Scene(rootLayout, 600, 600);
        loginWindow.setScene(loginScene);
        loginWindow.setTitle("HappyShop 🛒 - Log In");
    }

    // This method shows the login window and blocks the Main application until it's closed.
    public String showAndWaitAndGetRole() {
        // This makes the login window a must-do item! Main.java stops until this window is closed.
        loginWindow.initModality(Modality.APPLICATION_MODAL);

        loginWindow.showAndWait(); // Execution STOPS HERE until the window closes!

        return roleAfterLogin; // Returns the role we set in loginSuccessAndClose()
    }


    /**
     * When the LOGIN button is clicked, this method runs.
     */
    private void buttonClicked(ActionEvent event) {
        Button theButton = (Button) event.getSource();

        if (theController == null) {
            System.err.println("Controller not set up yet!");
            return;
        }

        //when login button is clicked sets the doLoginAction()
        if (theButton.getText().equals("Login")) {
            theController.doLoginAction();
        }
        // We removed the Register button logic from here.
    }

    /**
     * METHOD CALLS WHEN THE REGISTER LINK IS CLICKED
     */
    private void linkClicked(ActionEvent event) {
        // Since we know only the RegisterLink calls this method, we can directly call the action.
        if (theController == null) {
            System.err.println("Controller not set up yet!");
            return;
        }

        // This is the action that the old Register button used to trigger
        theController.doRegisterAction();
    }


    //   ======Methods the controller will use=======


    // This method gets the input from the username
    public String getTheUsername() {
        return usernameBox.getText();
    }

    // This method gets the input from the password
    public String getThePassword() {
        return passwordBox.getText();
    }

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