package application;

import java.time.LocalDate;

public class Log {
    private long startTime;
    private long endTime;
    private String designStep;
    private int totalStoryPoints;
    int month;
    int day;
    int year;

    public Log(String designStep, int totalStoryPoints) {
        this.startTime = System.currentTimeMillis();
        this.designStep = designStep;
        this.totalStoryPoints = totalStoryPoints;
        LocalDate currentDate = LocalDate.now();
        this.day = currentDate.getDayOfMonth();
        this.month = currentDate.getMonthValue();
        this.year = currentDate.getYear();
    }
    
    public void setNewStartTime(Long starTime) {
    	this.startTime = starTime;
    }
    
    public void setNewEndTime(Long endTime) {
    	this.endTime = endTime;
    	calculateStoryPoints();
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public String getDesignStep() {
        return designStep;
    }
    
    public String setDesignStep(String designStep) {
    	return designStep;
    }

    public int getTotalStoryPoints() {
        return totalStoryPoints;
    }
    
    private void calculateStoryPoints() {
        long timeDiffMillis = this.endTime - this.startTime;
        int storyPoints = (int) (timeDiffMillis / 30_000); // 30,000 milliseconds = 30 seconds
        this.totalStoryPoints = storyPoints;
    }
    
    public String toString() {
    	String retString = "";
    	retString += (month + "/" + day + "/" + year + " " + designStep);
    	return retString;
    }
}
