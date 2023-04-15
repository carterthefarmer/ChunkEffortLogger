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
    
    private void saveLogsToFile(ArrayList<Log> logEntries) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("logs.dat"));
        outputStream.writeObject(logEntries);
        outputStream.close();
    }
    

    public void addLogEntry(String designStep, int TotalStoryPoints) {
        Log entry = new Log(designStep, TotalStoryPoints);
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
