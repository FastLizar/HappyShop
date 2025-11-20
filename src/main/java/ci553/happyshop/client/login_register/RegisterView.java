package ci553.happyshop.client.login_register;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * REGISTRATION SCREEN DESIGN FILE
 */
public class RegisterView {

    public LoginRegisterController theController; // Link which connects to the controller
    public LoginView theLoginView; // Link back to the login screen

    private final TextField usernameBox = new TextField(); //Username field
    private final PasswordField passwordBox = new PasswordField(); //Password field
    private final Label messageLabel = new Label(" Create a New Account"); //registration message
    private final VBox rootLayout;

    public RegisterView() {

        usernameBox.setPromptText("Choose a username"); //Typing box for username
        passwordBox.setPromptText("Choose a password"); //Typing box for password


        Button registerButton = new Button("Register"); // Register button
        Hyperlink backToLoginLink = new Hyperlink("Already have an account? Log In");

        // Performs an action when the buttons are clicked
        registerButton.setOnAction(this::buttonClicked); // registers the user once clicked
        backToLoginLink.setOnAction(this::linkClicked); // goes back to login screen when clicked


        registerButton.setStyle("-fx-font-size: 14pt; -fx-background-color: #09c609;");
        backToLoginLink.setStyle("-fx-font-size: 10pt; -fx-text-fill: #1395e6; -fx-underline: true;"); //adds a text underline
        messageLabel.setStyle("-fx-font-size: 16pt; -fx-font-weight: bold; -fx-text-fill: black;");

        // Layout for the register fields and buttons
        rootLayout = new VBox(20); // 20 is the spacing between items
        rootLayout.setPadding(new Insets(30)); // Space around the edges
        rootLayout.setAlignment(Pos.CENTER);
        rootLayout.getChildren().addAll(
                messageLabel,
                new Label("Username:"),
                usernameBox,
                new Label("Password:"),
                passwordBox,
                registerButton,
                backToLoginLink
        );
    }


    public VBox getRootLayout() {
        return rootLayout;  // returns the layout back to loginview
    }

  // This method runs when the login button is clicked
    private void buttonClicked(ActionEvent event) {
        if (theController == null) {
            System.err.println("Controller not set up yet!");
            return;
        }

        //Tells the controller new input values
        theController.setInput(usernameBox.getText(), passwordBox.getText());

        //Tells the controller to proceed with a register
        theController.doRegisterAction();
    }

   //When the link to go back is clicked this method runs
    private void linkClicked(ActionEvent event) {
        if (theLoginView == null) {
            System.err.println("LoginView manager not linked!");
            return;
        }

        theLoginView.showLoginScreen();
    }

    // This paragraph displays a welcome message to the screen or an error message
    public void showErrorMessage(String message) {
        messageLabel.setText(message);
        messageLabel.setTextFill(Color.RED); // Ensures the error messages are displayed in red
        messageLabel.setStyle("-fx-font-size: 16pt; -fx-font-weight: bold;");
    }
}