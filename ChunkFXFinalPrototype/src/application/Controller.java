package application;

import java.io.IOException;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import java.util.Timer;
import javafx.util.Duration;
;


public class Controller {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private Timeline timeline;
	
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
	
	//Drew Kail
	@FXML
	private Label timerLabel;

	private boolean timerRunning = false;
	private long startTime = 0;
	private long elapsedTime = 0;
	private long pausedTime = 0;

	private Duration duration = new Duration(10);
	
	//called when start button is pressed
	//records and increments time in milliseconds as timer is being run
	@FXML
	public void handleStartButton(ActionEvent event) {
	    if (!timerRunning) {
	        startTime = System.currentTimeMillis() - pausedTime;
	        timerRunning = true;

	        timeline = new Timeline(new KeyFrame(javafx.util.Duration.millis(10), e -> {
	            elapsedTime = System.currentTimeMillis() - startTime;
	            timerLabel.setText(formatTime(elapsedTime));
	        }));
	        timeline.setCycleCount(Timeline.INDEFINITE);
	        timeline.play();
	    }
	}
	
	//called when pause button is pressed
	//records and displays elapsed time when user presses pause button
	@FXML
	public void handlePauseButton(ActionEvent event) {
	    if (timerRunning) {
	        timerRunning = false;
	        pausedTime += System.currentTimeMillis() - startTime;

	        timeline.pause();
	    }
	}

	//called with reset button
	//resets timer to 00:00:00
	@FXML
	public void handleResetButton(ActionEvent event) {
	    timerRunning = false;
	    startTime = 0;
	    elapsedTime = 0;
	    pausedTime = 0;
	    timerLabel.setText("00:00:00");

	    timeline.stop();
	}
	
	//called with stop button
	//stops timer at current elapsed time
	//calls Logger() to write data to logs.txt file
	@FXML
	public void handleStopButton(ActionEvent event) {
	    if (timerRunning) {
	        timerRunning = false;
	        pausedTime += System.currentTimeMillis() - startTime;

	        timeline.stop();
	        elapsedTime = System.currentTimeMillis() - startTime;

	        
	        long totalClockTime = elapsedTime;

	        timerLabel.setText(formatTime(totalClockTime));
	        
	        Logger logger = new Logger();
	        Logger.logTime(totalClockTime);
	    }
	}

	
	private String formatTime(long timeInMillis) {
	    int seconds = (int) (timeInMillis / 1000);
	    int minutes = seconds / 60;
	    int hours = minutes / 60;
	    seconds %= 60;
	    minutes %= 60;

	    return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}


	
}
