package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class LogViewer implements Initializable {
	
	Controller controller = new Controller();
	
	private ArrayList<Log> logEntries = new ArrayList<Log>();
	private List<String> logStrings;
	
	  @FXML
	  private Label dateDisp;
	  
    @FXML
    private Label elapsedTimeDisp;
    
	@FXML
	ChoiceBox<Log> logSel = new ChoiceBox<Log>();
	
	 @FXML
	 private Label projStageDisp;
	 
	 @FXML
	 private Label startTimeDisp;

	 @FXML
	 private Label stopTimeDisp;
	 
	@FXML
	private Label errorLabel = new Label();
	
	@FXML
	private Label dateLabel = new Label();
	
	@FXML
	private Label startTimeLabel = new Label();
	
	@FXML 
	private Label endTimeLabel = new Label();
	
	@FXML
	private Label storyPointLabel = new Label();
	
	@FXML
	private Label designStepLabel = new Label();
	
	 @FXML
	 private Label storyPointDisplay;
	 
	 @FXML
	 private Button viewLogBtn;
	
	private void loadLogsFromFile() throws IOException, ClassNotFoundException {
        File file = new File("logs.dat");
        if (file.exists()) {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
            logEntries = (ArrayList<Log>) inputStream.readObject();
            inputStream.close();
        }
    }
	
	@FXML
	public void handleDashboardButton(ActionEvent event) throws IOException {
			controller.switchToDashboard(event);
	}
	

    @FXML
    void viewLogBtnHandler(MouseEvent event) {
    	
    	dateDisp.setText(logSel.getValue().getDate()); //displays date of log
    	dateDisp.setVisible(true);
    	
    	startTimeDisp.setText(logSel.getValue().getStartTime()); //displays start time
    	startTimeDisp.setVisible(true);
    	
    	stopTimeDisp.setText(logSel.getValue().getEndTime()); //displays stop time
    	stopTimeDisp.setVisible(true);
    	
    	elapsedTimeDisp.setText(logSel.getValue().getElapsedTime()); //displays elapsed time
    	elapsedTimeDisp.setVisible(true);
    	
    	storyPointDisplay.setText(String.valueOf(logSel.getValue().getTotalStoryPoints())); //converts from int to string to display number of storypoints
    	storyPointDisplay.setVisible(true);
    	
    	projStageDisp.setText(logSel.getValue().getDesignStep()); //displays design step of current project
    	projStageDisp.setVisible(true);
    	
    	
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			loadLogsFromFile();
			logStrings = logEntries.stream().map(Log::toString).collect(Collectors.toList());
			logSel.setItems(FXCollections.observableArrayList(logEntries));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
