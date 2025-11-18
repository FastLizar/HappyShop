package ci553.happyshop.client.login_register;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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

        // Cretes a button to click one the user typed in their username and password
        Button loginButton = new Button("Login");
        Button registerButton = new Button("New User? Register!"); //register page link

        //Calls the 'buttonClicked' method
        loginButton.setOnAction(this::buttonClicked);
        registerButton.setOnAction(this::buttonClicked);

       //Simple style for the clickable buttons
        loginButton.setStyle("-fx-font-size: 14pt; -fx-background-color: lightgreen;");
        registerButton.setStyle("-fx-font-size: 10pt; -fx-background-color: lightblue;");

        //Creates a virtual box to fill all the parts into a proper layout
        VBox rootLayout = new VBox(20);
        rootLayout.setPadding(new Insets(30));
        rootLayout.setAlignment(Pos.CENTER);
        rootLayout.getChildren().addAll(
                messageLabel,
                new Label("Username:"),
                usernameBox,
                new Label("Password:"),
                passwordBox,
                loginButton,
                registerButton
        );

        //This paragraph sets up the window and the scene
        Scene loginScene = new Scene(rootLayout, 350, 400); //Height and width pixels
        loginWindow.setScene(loginScene);
        loginWindow.setTitle("HappyShop 🛒 - Log In"); // title of the page
    }


    public String showAndWaitAndGetRole() {

        loginWindow.initModality(Modality.APPLICATION_MODAL); //This line ensures the login window must run

        loginWindow.showAndWait();

        return roleAfterLogin;
    }


    private void buttonClicked(ActionEvent event) {
        Button theButton = (Button) event.getSource();

        if (theController == null) {
            System.err.println("Controller not set up yet!");
            return;
        }

      //when login button is clicked sets the doLoginAction()
        if (theButton.getText().equals("Login")) {
            theController.doLoginAction();
        } else if (theButton.getText().equals("New User? Register!")) {
            theController.doRegisterAction();
        }
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