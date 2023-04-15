package application;

import java.util.ArrayList;
import java.util.Date;

public class LogModel {
    private ArrayList<LogEntry> logEntries;

    public LogModel() {
        logEntries = new ArrayList<>();
    }

    public void addLogEntry(Date startTime, Date stopTime, Date date, String designStep) {
        LogEntry entry = new LogEntry(startTime, stopTime, date, designStep);
        logEntries.add(entry);
    }

    public ArrayList<LogEntry> getLogEntries() {
        return logEntries;
    }
}
