package ci553.happyshop.client.login_register;

/**
 * THIS IS THE CONTROLLER, IT TAKES INPUTS FROM BUTTONS
 * AND TELLS THE MODEL WHAT TO DO

*/
public class LoginRegisterController {

    //Link to the model file
    public LoginRegisterModel theModel;
    public LoginRegisterView theView;


    public void doLoginAction() {

        String user = theView.getTheUsername(); //takes the username from the screen
        String pass = theView.getThePassword(); // takes the password from the screen


        if (theModel.tryToLogin(user, pass)) { // checks if the login details are correct



            theView.loginSuccessAndClose(theModel.getTheID()); //closes the screen and moves onto main.java
        } else {
             // if login details are incorrect


            theView.showErrorMessage(" Login Failed! Wrong username or password."); //displays an error message
        }
    }

    /**
     * THIS METHOD IS BEING CALLED WHEN THE USER CLICKS THE REGISTER BUTTON
     */

    public void doRegisterAction() {

        String user = theView.getTheUsername(); //takes the username input
        String pass = theView.getThePassword(); //takes the password input


        if (theModel.tryToRegister(user, pass)) { //tries to create a new user in the model



            theView.loginSuccessAndClose(theModel.getTheID()); //closes the screen
        } else {



            theView.showErrorMessage(" Registration Failed! Fill in both boxes."); // sends an error message if the user tries to register with blanks
        }
    }
}