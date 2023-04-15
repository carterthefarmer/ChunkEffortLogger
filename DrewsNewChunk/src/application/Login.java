// Written by Carter Myers for ASU CSE360
package application;

import java.io.BufferedWriter;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Login {
	
	private static Scanner x;
	private static Scanner x2;
	@FXML
	private Button loginButton;
	@FXML
	private Label loginText;
	@FXML
	private TextField usernameField;
	@FXML
	private Button registerButton;
	@FXML
	private PasswordField passwordField;
	@FXML
	private TextField emailField;
	@FXML
	private Label registerLabel;
	@FXML
	private TextField registerUsername;
	@FXML
	private PasswordField registerPassword;
	@FXML
	private TextField registerEmail;
	@FXML
	private Button returnToLogin;
	
	private boolean filler = false;
	
	private static String loggedInUser = "";
	private static final String KEY = "CSE360GroupW23";
	
	private Controller controller = new Controller();
	
	
	public void userLogin(ActionEvent event) throws IOException{ // takes text field parameters and calls helper functions to verify and sign in.
		boolean signingIn = verifyLogin(usernameField.getText().toString(), passwordField.getText().toString(), "users.txt");
		if(signingIn == true) {
			loginText.setText("Login Succesfull");
			
			controller.switchToDashboard(event);
			loggedInUser = usernameField.getText().toString();
		}
		else {
			loginText.setText("Invalid Username/Password");
		}
	}
	
	public boolean verifyLogin(String username, String password, String filepath) { // scans the users.txt file if a username and password are in the local database
		boolean found = false;
		String tempUsername = "";
		String tempPassword = "";
		String tempEmail = "";
		
		try {
			x = new Scanner(new File(filepath));
			x.useDelimiter("[,\n]");
			
			while(x.hasNextLine() && !found) {
			    String line = x.nextLine();
			    String[] tokens = line.split(",");
			    if(tokens.length == 3 && decrypt(tokens[0].trim(), KEY).equals(username.trim()) && decrypt(tokens[1].trim(), KEY).equals(password.trim())) {
			        found = true;
			        return true;
			    }
			}
			x.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void createAccount(ActionEvent event) { // calls the controller to access the registration page
		try {
			controller.switchToRegistration(event);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean checkUsername(String username) throws IOException { // security protocol to ensure that usernames cannot fall for SQL Injection practices
		boolean found = false;
		String tempUsername = "";
		String tempPassword = "";
		
		try {
			x = new Scanner(new File("users.txt"));
			x.useDelimiter("[,\n]");
			
			while(x.hasNext() && !found) {
				tempUsername = x.next();
				tempPassword = x.next();
				
				if(tempUsername.trim().equals(username.trim())) {
					found = true;
					return true;
				}
			}
			x.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	public void register(ActionEvent event) throws IOException { // account registration function to add an account to the database
		filler = false; //resets for user to retry account setup
		if (registerUsername.getText().toString() == "" || registerPassword.getText().toString() == "" || registerEmail.getText().toString() == "") {
			registerLabel.setText("Not all arguments are filled out");
		}
		else {
			registerHelper(registerUsername.getText().toString(), registerPassword.getText().toString(), registerEmail.getText().toString());
			if(filler == true) {
			try {
				controller.switchToLogin(event);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			}
		}
	}

	public void registerHelper(String username, String password, String email) throws IOException { // helper function with main logic that associates with register(Action Event)
		boolean foundIssue = false; 
		String tempUser = "";
		String encryptedUsername = encrypt(username, KEY);
	    String encryptedPassword = encrypt(password, KEY);
	    String encryptedEmail = encrypt(email, KEY);
	    
		try {
			if (username.length() < 4 || username.length() > 32) {
		        foundIssue = true;
		        registerLabel.setText("username does not meet correct size!");
		    }
			String regex = "^[a-zA-Z0-9._-]+$";
			if(!username.matches(regex)) {
				foundIssue = true;
				registerLabel.setText("Please only use alphanumerics! (a-z, 0-9, ., -)");
			}
			
			if(username.contains("___")) {
				foundIssue = true;
				registerLabel.setText("String contains too many consecutive '___'");
			}
			
			if(!email.contains("@")) {
				foundIssue = true;
				registerLabel.setText("Not a valid email!");
			}
			
			File file = new File("users.txt");
			x2 = new Scanner(file);
			x2.useDelimiter("[,\n]");
			
			while(x2.hasNext() && !filler) {
				tempUser = x2.next();
				if(tempUser.trim().equals(username.trim())) {
					registerLabel.setText("Username already exists!");
					foundIssue = true;
				}
			
			}
				x2.close();
			if (foundIssue == false) {
				filler = true;
				FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			// create a new buffered writer
				BufferedWriter bw = new BufferedWriter(fw);
				// write the username and password to the file
				 bw.write(encryptedUsername + "," + encryptedPassword + "," + encryptedEmail);
				// create a new line
				bw.newLine();
				// close the buffered writer
				bw.close();
				// close the file writer
				fw.close();
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public void returnToLoginHelper(ActionEvent event) {
		try {
			controller.switchToLogin(event);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String encrypt(String input, String key) {
	    char[] inputChars = input.toCharArray();
	    char[] keyChars = key.toCharArray();
	    char[] outputChars = new char[inputChars.length];
	    for (int i = 0; i < inputChars.length; i++) {
	        outputChars[i] = (char) (inputChars[i] ^ keyChars[i % keyChars.length]);
	    }
	    return new String(outputChars);
	}
	
	private String decrypt(String input, String key) {
	    char[] inputChars = input.toCharArray();
	    char[] keyChars = key.toCharArray();
	    char[] outputChars = new char[inputChars.length];
	    for (int i = 0; i < inputChars.length; i++) {
	        outputChars[i] = (char) (inputChars[i] ^ keyChars[i % keyChars.length]);
	    }
	    return new String(outputChars);
	}

	public static String getLoggedInUser() {
		return loggedInUser;
	}

	public static void setLoggedInUser(String loggedInUser) {
		Login.loggedInUser = loggedInUser;
	}

}
