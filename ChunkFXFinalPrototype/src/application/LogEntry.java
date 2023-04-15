package application;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogEntry {
    private Date startTime;
    private Date stopTime;
    private Date date;
    private String designStep;

    public LogEntry(Date startTime, Date stopTime, Date date, String designStep) {
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.date = date;
        this.designStep = designStep;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDesignStep() {
        return designStep;
    }

    public void setDesignStep(String designStep) {
        this.designStep = designStep;
    }

    // Override toString method to display the log entry in the desired format
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date) + " - " + designStep + " - Start: " + dateFormat.format(startTime) + " - Stop: " + dateFormat.format(stopTime);
    }
    
    public String getLogName() {
    	return date.toString();
    }
}
