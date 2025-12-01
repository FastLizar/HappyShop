package ci553.happyshop.client.login_register;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;  //NEW IMPORT TO READ JAVA FILES


public class LoginRegisterModel {

    private static final String APP_DIR = "HappyShopData";
    private static final String USER_FILE_NAME = // specified path to user_accounts.txt file
            "C:\\Users\\vo73\\IdeaProjects\\HappyShop\\src\\main\\java\\ci553\\happyshop\\client\\login_register\\user_accounts.txt";

    //A list to hold the user data
    private final List<String> currentUsers = new ArrayList<>();

    private String userID = null; //variable used to remember the user


    public String getTheID() {  //returns the variable after successful login
        return userID;
    }
    private void ensureDirectoryExists() {
        //Creates a file inside the directory path
        File appDir = new File(System.getProperty("user.home") + File.separator + APP_DIR);

        //Checks if the file exists
        if (!appDir.exists()) {
            System.out.println("Creating application data directory: " + appDir.getAbsolutePath());
            appDir.mkdirs(); // mkdirs() creates the directory and any necessary parent directories
        }
    }
    //New method which will help run all the accounts from the text file
    private void loadAccountsFromFile() {
        currentUsers.clear();
        System.out.println("Reading users from file...");
        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                // The file will have lines like: username,password,role
                currentUsers.add(line);
            }
        } catch (IOException e) {
            //If the file is missing it will create an error message for the user
            System.err.println("Problem reading user file (maybe it's missing?): " + e.getMessage());
        }
    }


    public boolean tryToLogin(String userWord, String secretWord) { // this line will try to log the user in

        //Loads users from file
        loadAccountsFromFile();

        //Checks the list of users
        for (String userRecord : currentUsers) {
            String[] parts = userRecord.split(",");

            if (parts.length < 3) continue;

            String fileUsername = parts[0];
            String filePassword = parts[1];
            String fileRole = parts[2];

            if (fileUsername.equals(userWord) && filePassword.equals(secretWord)) {
                userID = fileRole; // set the role we found in the file
                return true;
            }
        }

        //checks if the secret word matches the 3 options: customer, picker or warehouse
        userID = null;
        return false;
    }

    // Registering a new customer

    public boolean tryToRegister(String userWord, String secretWord) {


        loadAccountsFromFile();

        if (userWord.isEmpty() || secretWord.isEmpty()) { //checks if the username is taken
            return false;//shows an error
        }

        //Double check if the username is already taken
        for (String userRecord : currentUsers) {
            String fileUsername = userRecord.split(",")[0];
            if (fileUsername.equals(userWord)) {
                return false; //returns message that the username is taken
            }
        }


        String role = "customer"; //New registered users are always customers
        String newRecord = userWord + "," + secretWord + "," + role + "\n"; //Format: user,pass,role, and a new line

        try (FileWriter fw = new FileWriter(USER_FILE_NAME, true)) { //'true' means append to the end

            System.out.println("Attempting to write to absolute path: " + new java.io.File(USER_FILE_NAME).getAbsolutePath());

            fw.write(newRecord);
            System.out.println("New user registered and saved: " + userWord);

            userID = role; // Sets an ID for immediate login
            return true;
        } catch (IOException e) {
            System.err.println("FATAL: Could not write to user file! " + e.getMessage());
            return false; //Registration failed because it cant save
        }
    }
}