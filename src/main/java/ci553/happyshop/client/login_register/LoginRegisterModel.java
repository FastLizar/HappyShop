package ci553.happyshop.client.login_register;

import ci553.happyshop.storageAccess.DatabaseRW;
import ci553.happyshop.storageAccess.DatabaseRWFactory;




public class LoginRegisterModel {

    private String userID = null; //variable used to remember the user


    public String getTheID() {  //returns the variable after successful login
        return userID;
    }

    public boolean tryToLogin(String userWord, String secretWord) { // this line will try to log the user in



        if (userWord.equals("customer") && secretWord.equals("pass")) {
            userID = "customer"; //customer ID check
            return true;
        } else if (userWord.equals("picker") && secretWord.equals("pass")) {
            userID = "picker"; // staff member ID check
            return true;
        } else if (userWord.equals("warehouse") && secretWord.equals("pass")) {
            userID = "warehouse"; //manager ID check
            return true;
        }

        //checks if the secret word matches the 3 options: customer, picker or warehouse
        userID = null;
        return false;
    }

   // Registering a new customer

    public boolean tryToRegister(String userWord, String secretWord) {


        if (userWord.isEmpty() || secretWord.isEmpty()) { //checks if the username is taken

            return false;//shows an error
        }


        System.out.println("New user registered: " + userWord);
        userID = "customer";
        return true;
    }
}




