package application;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Calendar;

public class Log implements Serializable{
	private long startTime;
    private long endTime;
    private String designStep;
    private int totalStoryPoints;
    int month;
    int day;
    int year;
    private Date date;
    private LocalDate newStartDate = LocalDate.of(2000, 1, 1);
    private LocalDate newEndDate = LocalDate.of(2000, 1, 1);
    

    public Log(String designStep, int totalStoryPoints) {
        this.startTime = System.currentTimeMillis();
        this.designStep = designStep;
        this.totalStoryPoints = totalStoryPoints;
        LocalDate currentDate = LocalDate.now();
        this.day = currentDate.getDayOfMonth();
        this.month = currentDate.getMonthValue();
        this.year = currentDate.getYear();
        this.date = new Date();
    }
    
    
    public void setNewStartTime(String newDateTime) {
    	 // Extract hours, minutes, and seconds from the string
        int hours = Integer.parseInt(newDateTime.substring(0, 2));
        int minutes = Integer.parseInt(newDateTime.substring(3, 5));
        int seconds = Integer.parseInt(newDateTime.substring(6, 8));

        // Create a Calendar object and set it to the current date and time
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Add the new time to the Calendar object
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        calendar.add(Calendar.MINUTE, minutes);
        calendar.add(Calendar.SECOND, seconds);

        // Get the updated Date object
        this.date = calendar.getTime();

        // Update the startTime field
        this.startTime = this.date.getTime();
    	
    }
    public void setNewEndTime(Long endTime, String newDate) {
    	this.endTime = endTime;
    	
    	//new end time will be converted from a string to int, then put in the local date object
    	int month, date, year;
    	if(newDate.substring(1,2).equals("/")) {
    		newDate = "0" + newDate;
    	}
    	month = Integer.parseInt(newDate.substring(0, 2));
    	date = Integer.parseInt(newDate.substring(3, 4));
    	year = Integer.parseInt(newDate.substring(6, 9));
    	
    	LocalDate newEndDate = LocalDate.of(year, month, date);
    	this.newEndDate = newEndDate;
    	
    	calculateStoryPoints();
    }

    public String getStartTime() {
        String time = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Date updatedDate = calendar.getTime();
        time += updatedDate;
        return time;
    }
    
    
    public Long getStartTimeLong() {
    	return this.startTime;
    }

    public String getElapsedTime() {
        String time = "";
        //long elapsedTimeLong = endTime - startTime;
        if (endTime != 0) {
        	int hours = (int) (endTime / 3600000);
            int minutes = (int) ((endTime % 3600000) / 60000); // 60000 milliseconds = 1 minute
            int seconds = (int) ((endTime % 60000) / 1000); // 1000 milliseconds = 1 second
            
            if(minutes >= 60)
            {
            	minutes = minutes - 60;
            	hours++;
            }
            
            if(seconds >= 60)
            {
            	seconds = seconds - 60;
            	minutes++;
            }
            
            time = String.format("%02d:%02d:%02d", hours, minutes, seconds); // Format as mm:ss
        }
        return time;
    }

    public String getEndTime() {

            // Create a Calendar object and set it to the current date and time
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            // Add time to the Calendar object
            int hours = (int) ((endTime / (1000*60*60)) % 24);
            int minutes = (int) ((endTime / (1000*60)) % 60);
            int seconds = (int) (endTime / 1000) % 60;
            
            //if statements that check to make sure that the minutes and seconds are not over 60
            
            if(minutes >= 60)
            {
            	minutes = minutes - 60;
            	hours++;
            }
            
            if(seconds >= 60)
            {
            	seconds = seconds - 60;
            	minutes++;
            }
            
            calendar.add(Calendar.HOUR_OF_DAY, hours); // Add 2 hours
            calendar.add(Calendar.MINUTE, minutes);     // Add 30 minutes
            calendar.add(Calendar.SECOND, seconds);

            // Get the updated Date object
            Date updatedDate = calendar.getTime();
            return "" + updatedDate;
    }

    public String getDesignStep() {
        return designStep;
    }
    
    public void setDesignStep(String designStep) {
    	this.designStep = designStep;
    }
    
    public void setNewDate(LocalDate newDate) {
        // Set the new date
        this.newStartDate = newDate;
        
        // Update the month, day, and year fields
        this.month = newDate.getMonthValue();
        this.day = newDate.getDayOfMonth();
        this.year = newDate.getYear();
        
        // Update the start time
        Calendar calendar = Calendar.getInstance();
        calendar.set(this.year, this.month, this.day);
        Date newdate = Date.from(newDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.date = newdate;
    }

    public int getTotalStoryPoints() {
        return totalStoryPoints;
    }
    
    public String getDate() {
    	String retString = "";
    	retString += (month + "/" + day + "/" + year);
    	return retString;
    }
    
    private void calculateStoryPoints() {
        int storyPoints = (int) (this.endTime / 1800000); // 1,800,000 milliseconds = 30 minutes
        this.totalStoryPoints = storyPoints;
    }
    
    public String toString() {
    	String retString = "";
    	retString += (month + "/" + day + "/" + year + " " + designStep);
    	return retString;
    }
    
    //updated set end time method:
    public void newSetEndTime(String newEndTime)
    {
    	
    	DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
    	String startDate = dateFormat.format(date);
    	
    	int hour, minute, second, startHour, startMinute, startSecond;
    	
    	//parsing the string to get each unit of time into their own variable
    	hour = Integer.parseInt(newEndTime.substring(0, 2));
    	minute = Integer.parseInt(newEndTime.substring(3, 5));
    	second = Integer.parseInt(newEndTime.substring(6, 8));
    	
    	//parsing the string to get the start time 
    	startHour = Integer.parseInt(startDate.substring(0, 2));
    	startMinute = Integer.parseInt(startDate.substring(3, 5));
    	startSecond = Integer.parseInt(startDate.substring(6, 8));
    	
    	//calculating the elapsed time
    	hour = hour - startHour;
    	minute = minute - startMinute;
    	second = second - startSecond;
    	
    	//converting the units of time to milliseconds
    	long hoursToMilli = hour * 60 * 60 * 1000;
    	long minutesToMilli = minute * 60 * 1000;
    	long secondsToMilli = second * 1000; 
    	
    	//adding them all up and setting them equal to the end time
    	this.endTime = hoursToMilli + minutesToMilli + secondsToMilli;
    	
    	calculateStoryPoints();
    }
    
}








