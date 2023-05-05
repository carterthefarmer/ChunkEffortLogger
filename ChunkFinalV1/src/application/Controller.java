package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.util.Duration;
;


public class Controller{
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
    @FXML
    private Button editLog;
    
	public void switchToDashboard(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		
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
	
	public void switchToLogScene (ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("LogScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	} 


	
}
