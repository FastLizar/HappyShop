package ci553.happyshop.client.login_register;

/**
 * THIS IS THE CONTROLLER, IT TAKES INPUTS FROM BUTTONS
 * AND TELLS THE MODEL WHAT TO DO
 *
 */
public class LoginRegisterController {

    //Link to the model file
    public LoginRegisterModel theModel;
    public LoginView theLoginView; // The main window manager/Login Screen
    public RegisterView theRegisterView; // The Register Screen
    private String currentUsername = "";
    private String currentPassword = "";
    public void setInput(String username, String password) {
        this.currentUsername = username;
        this.currentPassword = password;
    }


    public void doLoginAction() {

        //Uses the input captured by the active view
        String user = currentUsername; //takes the username from the screen
        String pass = currentPassword; // takes the password from the screen


        if (theModel.tryToLogin(user, pass)) { // checks if the login details are correct


            // Uses the LoginView to close the window, as it owns the Stage
            theLoginView.loginSuccessAndClose(theModel.getTheID()); //closes the screen and moves onto main.java
        } else {
            // if login details are incorrect


            theLoginView.showErrorMessage(" Login Failed! Wrong username or password."); //displays an error message
        }
    }

    /**
     * THIS METHOD IS BEING CALLED WHEN THE USER CLICKS THE REGISTER BUTTON
     * (Called by the RegisterView)
     */

    public void doRegisterAction() {

        // Use the input captured by the active view (RegisterView)
        String user = currentUsername; //takes the username input
        String pass = currentPassword; //takes the password input


        if (theModel.tryToRegister(user, pass)) { //tries to create a new user in the model


            // Uses the LoginView to close the window, as it owns the Stage
            theLoginView.loginSuccessAndClose(theModel.getTheID()); //closes the screen
        } else {


            // Shows error on the RegisterView
            theRegisterView.showErrorMessage(" Registration Failed! Username is empty or taken."); //displays an error message
        }
    }
}