package application;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LogEditor implements Initializable {
	
protected LocalTime startLTime;
protected LocalTime endLTime;
protected boolean inputNotValid = true;

    
    @FXML
    private MenuItem closeApp;

    @FXML
    private Button consoleReturn;

    @FXML
    private DatePicker date;
    
    @FXML
    private MenuItem defMenu;

    @FXML
    private Button deleteEntry;

    @FXML
    private ChoiceBox<?> effortCat;
    
    @FXML
    private Label endErrorLabel;

    @FXML
    private TextField endTime;
   
    @FXML
    private Label endtimeError;
    
    @FXML
    private Label invalidInputLbl;
    
    @FXML
    private Label invalidDate;

    @FXML
    private ChoiceBox<String> lifeCylcleStep;
    
    String[] lifeCycleSteps = {"Planning", "Requirements", "Gathering", "Design", "Development", "Testing", "Deployment", "Maintenence"};
    

    @FXML
    private ChoiceBox<LogEntry> logSel;
    
    String designStep = "Example design step";
    
    LogEntry log1 = new LogEntry(new Date(), new Date(), new Date(), designStep);
 

    @FXML
    private ChoiceBox<?> plan;

    @FXML
    private ChoiceBox<?> projSel;
    
    @FXML
    private Label savedUpdateLbl;
    
    @FXML
    private Button splitEntry;

    @FXML
    private Label startErrorLabel;
    
    @FXML
    private TextField startTime;

    @FXML
    private Button updateEntry;
    
    @FXML
    void closeProg(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void returnConsole(MouseEvent event) {
    	
    }

    @FXML
    void saveUpdate(MouseEvent event) {
    	//only allows updates to be saved if all inputs are valid.
    	if(inputNotValid) {
    		invalidInputLbl.setVisible(true);
    	}
    	else if(!inputNotValid) {
    		invalidInputLbl.setVisible(false);
    		savedUpdateLbl.setVisible(true);
    	}
    }
    @FXML
    void selectDate(ActionEvent event) {
    	String stdDate = "yyyy.MM.dd"; //string to format the date
    	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(stdDate); //creates a date formatter object using the previously created format string

    		LocalDate dateRaw = date.getValue(); //saves value from pickerbox as localDate
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

    @FXML
    void startTime(ActionEvent event) {
    	//String stdTime = "HH:mm:ss";
    	//DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern(stdTime);
    	//LocalDateTime startLTime;
    	//makes sure time format is valid
    	try {
        	startLTime = LocalTime.parse(startTime.getText());
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
    void endTime(ActionEvent event) {
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
    
    void setup() {
    	
    }

    @FXML
    void openDef(ActionEvent event) {

    }
    @FXML 
    void updateLog(ActionEvent event) {
    	if(inputNotValid == false && logSel.getValue() != null) {
    		LogEntry selectedLog = logSel.getValue();
    		
    	}
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		logSel.getItems().addAll(log1);
		lifeCylcleStep.getItems().addAll(lifeCycleSteps);
	}
}


//Zachary White - 1218373689
//updated by Carter Myers 4/6/2023