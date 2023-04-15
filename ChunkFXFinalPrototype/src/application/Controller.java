package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void switchToDashboard(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		//Drew Kail
		// Creates a start and stop button to run the timer
		Button startStopButton = (Button) scene.lookup("#StartStop");
	    startStopButton.setOnAction(e -> {
	        if (startStopButton.getText().equals("Start Timer")) {
	            startStopButton.setText("Stop Timer");
	        } else {
	            startStopButton.setText("Start Timer");
	        }
	    });
	}
	
	public void switchToLogin(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToRegistration(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToLogEditor(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("LogEditor.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToPastLogs (ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("PastLogs.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToNewLog (ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("NewLog.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
}
