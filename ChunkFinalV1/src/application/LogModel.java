package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

public class LogModel {
    private ArrayList<Log> logEntries;
    

    public LogModel() {
        logEntries = new ArrayList<Log>();
    }
    

    public void addLogEntry(String designStep, int TotalStoryPoints, long endTime) {
        Log entry = new Log(designStep, TotalStoryPoints);

        //creating a string to hold the new end time
        String newDate = entry.getDate();
        if(endTime > 0) {
        	entry.setNewEndTime(endTime, newDate);
        }
        logEntries.add(entry);
    }

    public ArrayList<Log> getLogEntries() {
        return logEntries;
    }
    
    
    
    private void loadLogsFromFile() throws IOException, ClassNotFoundException {
        File file = new File("logs.dat");
        if (file.exists()) {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
            logEntries = (ArrayList<Log>) inputStream.readObject();
            inputStream.close();
        }
    }
}
