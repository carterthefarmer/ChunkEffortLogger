package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import application.Controller;
public class LogEditor implements Initializable {
//variable declarations
	
protected LocalTime startLTime;
protected LocalTime endLTime;
protected boolean inputNotValid = true;

	@FXML
	private Button cancelBtn;

	 @FXML
	 private Button cancelClearBtn;
	 
	    @FXML
	    private Label clearAskLabel;
	    
    @FXML
    private MenuItem closeApp;

    @FXML
    private DialogPane clearPane;

    @FXML
    private Button clearLog;
    
    @FXML
    private Button consoleReturn;

    @FXML
    private Button dashboardReturn;
    
    @FXML
    private DatePicker date;
    
    @FXML
    private MenuItem defMenu;

    @FXML
    private Label deleteAskLabel;

    @FXML
    private DialogPane deletePane;
    
    @FXML
    private Button deleteEntry;

    @FXML
    private ChoiceBox<?> effortCat;
    
    @FXML
    private Label endErrorLabel;

    @FXML
    private TextField endTime;
    
    @FXML
    private TextField startTime;
    
    @FXML
    private Label endtimeError;
    
    @FXML
    private Label invalidInputLbl;
    
    @FXML
    private Label invalidDate;

    @FXML
    private ChoiceBox<String> lifeCycleStep;
    
    String[] lifeCycleSteps = {"Planning", "Requirements", "Gathering", "Design", "Development", "Testing", "Deployment", "Maintenence"};
    

    @FXML
    private ChoiceBox<Log> logSel;
    
    ArrayList<Log> logs = new ArrayList<Log>();
    ObservableList<Log> logsOL = FXCollections.observableList(logs);
    
    String designStep = "Example design step";
 

    @FXML
    private ChoiceBox<?> plan;

    @FXML
    private ChoiceBox<?> projSel;
    
    @FXML
    private Label savedUpdateLbl;
    

    @FXML
    private Button selectEntryBtn;
    
    @FXML
    private Button splitEntry;

    @FXML
    private Label startErrorLabel;
    
    @FXML
    private TextField timeText;

    @FXML
    private Button updateEntry;
    
    @FXML
    private Button yesBtn;
   
    @FXML
    private Button yesClearBtn;
    
    @FXML
    void clearLog(MouseEvent event) { //opens clear log dialog window
    	//makes dialog window elements visible
    	clearPane.setVisible(true);
    	clearAskLabel.setVisible(true);
    	yesClearBtn.setVisible(true);
    	cancelClearBtn.setVisible(true);
    }
    
    @FXML
    void cancelClear(MouseEvent event) {
    	//makes clear log dialog windows elements invisible
    	clearPane.setVisible(false);
    	clearAskLabel.setVisible(false);
    	yesClearBtn.setVisible(false);
    	cancelClearBtn.setVisible(false);
    }
    
    @FXML
    void cancelDelete(MouseEvent event) {
    	//makes delete log dialog window elements invisible
    	deletePane.setVisible(false);
    	deleteAskLabel.setVisible(false);
    	yesBtn.setVisible(false);
    	cancelBtn.setVisible(false);
    }

    @FXML
    void confirmClear(MouseEvent event) { //confirms user wants to clear log
    	//sets clear log dialog window elements to invisible
    	clearPane.setVisible(false);
    	clearAskLabel.setVisible(false);
    	yesClearBtn.setVisible(false);
    	cancelClearBtn.setVisible(false);
    	
    	//clears entry fields of log. Does NOT delete from log
    	date.setValue(null);
    	startTime.setText(null);
    	endTime.setText(null);
    	lifeCycleStep.setValue(null);
    }
    
    @FXML
    void confirmDelete(MouseEvent event) throws IOException { //user wants to delete log
    	//sets delete dialog window elements to invisible
    	deletePane.setVisible(false);
    	deleteAskLabel.setVisible(false);
    	yesBtn.setVisible(false);
    	cancelBtn.setVisible(false);
    	//calls function to delete the log. Passes the selected log string name to function
    	deleteLog(logSel.getValue().toString());
    }
    
    @FXML
    void returnConsole(MouseEvent event) {
    	
    }
    
    @FXML
    void deleteEntry(MouseEvent event) { //dialog asks if user wants to delete log
    	//sets delete dialog window elements to visible
    	deletePane.setVisible(true);
    	deleteAskLabel.setVisible(true);
    	yesBtn.setVisible(true);
    	cancelBtn.setVisible(true);
    }

    @FXML
    void returnToDash(ActionEvent event) throws IOException { //returns to main dashboard
    	Controller controller = new Controller();
    	controller.switchToDashboard(event);
    }
    
    @FXML
    void saveUpdate(MouseEvent event) throws FileNotFoundException, IOException {
    	//only allows updates to be saved if all inputs are valid.
    	if(inputNotValid) {
    		invalidInputLbl.setVisible(true);
    	}
    	else if(!inputNotValid) {
    		invalidInputLbl.setVisible(false);
    		savedUpdateLbl.setVisible(true);
    		updateLog(); //calls update log function if valid input
    	}
    }
    @FXML
    void selectDate(ActionEvent event) { //input validation function
    	String stdDate = "yyyy.MM.dd"; //string to format the date
    	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(stdDate); //creates a date formatter object using the previously created format string
    		LocalDate dateRaw = date.getValue(); //saves value from pickerbox as localDate
    		if(dateRaw != null) { 
    		String date = dateRaw.format(dateFormat); //formats date as string
        	try {
    		dateFormat.parse(date);	//makes sure date is valid format
    		inputNotValid = false;
    	}
    	catch(DateTimeParseException dateError){
    		System.out.print("Date error\n.");
    		inputNotValid = true;
    		
    	}
        	if(dateRaw == null || dateRaw.isAfter(LocalDate.now())) { //makes sure that date is valid (has already occurred and the box isn't blank)
        		//System.out.println("Invalid date");
        		invalidDate.setVisible(true); //sets label to visible. Says 'invalid date'
        		inputNotValid = true;
        	}
        	else if(dateRaw != null && dateRaw.isBefore(LocalDate.now())) {
        		invalidDate.setVisible(false); //sets label to not visible again
        		inputNotValid = false;
        		//System.out.printf("%s\n", date);
        	}
    		}
    }

    @FXML
    void startTime(ActionEvent event) { //input validation function
    	//String stdTime = "HH:mm:ss";
    	//DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern(stdTime);
    	//LocalDateTime startLTime;
    	//makes sure time format is valid
    	try {
        	startLTime = LocalTime.parse(timeText.getText());
        	//System.out.printf("%s\n", startLTime);
        	startErrorLabel.setVisible(false);
        	inputNotValid = false;
    	}
    	catch(DateTimeParseException timeError){
    		//System.out.print("Time Error\n");
    		startErrorLabel.setVisible(true);
    		inputNotValid = true;
    	}
    	//makes sure start time is before end time and not null
    	if(endLTime != null) {
    		if(startLTime.isAfter(endLTime)) {
    			endtimeError.setVisible(true);
    			inputNotValid = true;
    		}
    		else if(startLTime.isBefore(endLTime)){
    			endtimeError.setVisible(false);
    			inputNotValid = false;
    		}
    	}
    }
    
    @FXML
    void endTime(ActionEvent event) { //input validation function
    	//checks if time valid
    	try {
        	endLTime = LocalTime.parse(endTime.getText());
        	//System.out.printf("%s\n", endLTime);
        	endErrorLabel.setVisible(false);
        	inputNotValid = false;
    	}
    	//catches error and notifies user
    	catch(DateTimeParseException timeError){
    		//System.out.print("Time Error\n");
    		endErrorLabel.setVisible(true);
    		inputNotValid = true;
    	}
    	//makes sure start time is before end time
    	if(startLTime != null) {
    		if(endLTime.isBefore(startLTime)) {
    			endtimeError.setVisible(true);
    			inputNotValid = true;
    			//System.out.print("End time must be after start time.\n");
    		}
    		else if(endLTime.isAfter(startLTime)) {
    			endtimeError.setVisible(false);
    			inputNotValid = false;
    			//System.out.print("Valid time\n");
    		}
    	}
    }

    @FXML
    void selectEntry(MouseEvent event) { //displays contents of log selected in fields when button clicked
    	
    	try {
    		//gets year, month, and day of selected log. Converts to localdate and displays
    		date.setValue(LocalDate.of(logSel.getValue().year, logSel.getValue().month, logSel.getValue().day));
    	}
    	catch(DateTimeParseException error) {
    		error.printStackTrace();
    	}
    	//set values of log
    	startTime.setText(logSel.getValue().getStartTime());
    	endTime.setText(logSel.getValue().getEndTime());
    	lifeCycleStep.setValue(logSel.getValue().getDesignStep());
    	
    }
    
    @FXML 
    void updateLog() throws FileNotFoundException, IOException {
    	if(inputNotValid == false && logSel.getValue() != null) { //makes sure input is valid
    		if(lifeCycleStep.getValue() != null) {
    			logSel.getValue().setDesignStep(lifeCycleStep.getValue().toString());
    		}
    		if(date.getValue() != null) {
    			logSel.getValue().setNewDate(date.getValue());   
    		}
    		if(startTime.getText()!= null) {
    			String actualStartTime = startTime.getText().substring(11, 19); //gets only the actual time from field
    			logSel.getValue().setNewStartTime(actualStartTime); //updates time
    		}
    		//adding the if statement to update the end time in the log
    		if(endTime.getText() != null)
    		{	
    			String actualEndTime = endTime.getText().substring(11, 19); //get only actual time from field
    			logSel.getValue().newSetEndTime(actualEndTime); 
    			
    		}
    		
    		
    		
    			saveLogsToFile();
    			logSel.getItems().clear(); //clears combobox with logs
    			try {
					loadLogsFromFile(); //need to reload logs to reflect changes
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	    	logsOL = FXCollections.observableList(logs); //updates combo box
    			logSel.setItems(logsOL);
    			
    			//clears values in text fields
    			date.setValue(null);
    	    	startTime.setText(null);
    	    	endTime.setText(null);
    	    	lifeCycleStep.setValue(null);
    	    }	
    	
    }
    
    private void loadLogsFromFile() throws IOException, ClassNotFoundException {
        File file = new File("logs.dat");
        if (file.exists()) {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
            logs = (ArrayList<Log>) inputStream.readObject();
            inputStream.close();
        }
    }
    
    private void saveLogsToFile() throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("logs.dat"));
        outputStream.writeObject(logs);
        outputStream.close();
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lifeCycleStep.getItems().addAll(lifeCycleSteps);
		try {
			loadLogsFromFile();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logSel.getItems().addAll(logs);
		logsOL.addAll(logs);
	}
	
    public Log returnLog(String logName) { //finds a log in list searches by title; returns log object
    	boolean found = false;
    	int i =0;
    	while(found != true) {
    		found = logs.get(i).toString().equals(logName);
    		if(!found) {
    			i++;
    		}
    		
    	}
    	return logs.get(i);
    }
    
    public void updateLogArray(String logName, String newDate) {
    	boolean found = false;
    	int i =0;
    	while(found != true) {
    		found = logs.get(i).toString().equals(logName);
    		if(!found) {
    			i++;
    		}
    		logs.get(i).setNewStartTime(newDate);
    	}
    }
    
    private void deleteLog(String logName) throws IOException { //deletes a log
    	Log deleteLog = returnLog(logName); //finds and returns log object
    	logs.remove(deleteLog); //calls fucntion on log
    	saveLogsToFile(); //saves
    	try {
			loadLogsFromFile(); //reload
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//logSel.getItems().addAll(logs);
    	logSel.getItems().clear();
    	logsOL = FXCollections.observableList(logs);
		logSel.setItems(logsOL);
		date.setValue(null);
    	startTime.setText(null);
    	endTime.setText(null);
    	lifeCycleStep.setValue(null);
    	
    	
    }
}


//Zachary White - 1218373689
//updated by Carter Myers 4/6/2023