package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class LogCreator  implements Initializable {
	private Timeline timeline;
	
	private ArrayList<Log> logEntries = new ArrayList<Log>();
	
	@FXML
	private Label DesignStepBox;
	
	@FXML
	private ChoiceBox<String> designSteps;
	
	private String[] designStepsArray = {"Planning", "Requirements", "Gathering", "Design", "Development", "Testing", "Deployment", "Maintenence"};
	
	@FXML
	private Label timerLabel;
	
	@FXML 
	private Button returnDashboard;

	private boolean timerRunning = false;
	private long startTime = 0;
	private long elapsedTime = 0;
	private long pausedTime = 0;

	private Duration duration = new Duration(10);
	
	Controller controller = new Controller();
	
	

	
	private void loadLogsFromFile() throws IOException, ClassNotFoundException {
        File file = new File("logs.dat");
        if (file.exists()) {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
            logEntries = (ArrayList<Log>) inputStream.readObject();
            inputStream.close();
        }
    }
	
	private void saveLogsToFile() throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("logs.dat"));
        outputStream.writeObject(logEntries);
        outputStream.close();
    }
	
		//called when start button is pressed
		//records and increments time in milliseconds as timer is being run
		@FXML
		public void handleStartButton(ActionEvent event) {
		    if (!timerRunning && designSteps.getValue() != null) {
		        startTime = System.currentTimeMillis() - pausedTime;
		        timerRunning = true;

		        timeline = new Timeline(new KeyFrame(javafx.util.Duration.millis(10), e -> {
		            elapsedTime = System.currentTimeMillis() - startTime;
		            timerLabel.setText(formatTime(elapsedTime));
		        }));
		        timeline.setCycleCount(Timeline.INDEFINITE);
		        timeline.play();
		    }
		    else {
		    	if(designSteps.getValue() == null) {
		    		DesignStepBox.setText("Design Step Not Selected");
		    	}
		    	else if(timerRunning) {
		    		DesignStepBox.setText("Timer Already Running");
		    	}
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
		        DesignStepBox.setText("Timer Paused!");
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
		    DesignStepBox.setText("Timer Reset");
		}
		
		//called with stop button
		//stops timer at current elapsed time
		//calls Logger() to write data to logs.txt file
		@FXML
		public void handleStopButton(ActionEvent event) throws FileNotFoundException, IOException {
		    if (timerRunning) {
		        timerRunning = false;
		        pausedTime += System.currentTimeMillis() - startTime;
		        
		        timeline.stop();
		        elapsedTime = System.currentTimeMillis() - startTime;
		        
		        
		        long totalClockTime = elapsedTime;

		        timerLabel.setText(formatTime(totalClockTime));
		    
		  
		        addLogEntry(designSteps.getValue(), 0, elapsedTime);
		        saveLogsToFile();
		        
		    }
		}
		
		@FXML
		public void handleDashboardButton(ActionEvent event) throws IOException {
			if(timerRunning == false) {
				controller.switchToDashboard(event);
			}
			else {
				DesignStepBox.setText("Can't Exit with Running Log");
			}
		}
		
	    public void addLogEntry(String designStep, int TotalStoryPoints, long endTime) {
	        Log entry = new Log(designStep, TotalStoryPoints);
	        
	        //creating a string that will hold the date of a log
	        String newDate = entry.getDate();
	        if(newDate.substring(1,2).equals("/")) {
	        	newDate = "0" + newDate;
	        }
	        if(endTime > 0) {
	        	entry.setNewEndTime(endTime, newDate);
	        }
	        logEntries.add(entry);
	    }

		
		private String formatTime(long timeInMillis) {
		    int seconds = (int) (timeInMillis / 1000);
		    int minutes = seconds / 60;
		    int hours = minutes / 60;
		    seconds %= 60;
		    minutes %= 60;

		    return String.format("%02d:%02d:%02d", hours, minutes, seconds);
		}


		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			try {
				loadLogsFromFile();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("No Logs in .dat file, continue proceeding.");
			}
			
			designSteps.getItems().addAll(designStepsArray);
			
		}
	
}
