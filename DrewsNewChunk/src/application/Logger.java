// Drew Kail
package application;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

//Logger Class that writes the current elapsed time from the timer to the logs.txt file
public class Logger {
    
    public static void logTime(long totalClockTime) {
        FileWriter writer = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss");
            String currentDate = dateFormat.format(new Date());

            // Formatting time
            String formattedTime = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(totalClockTime),
                TimeUnit.MILLISECONDS.toMinutes(totalClockTime) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(totalClockTime) % TimeUnit.MINUTES.toSeconds(1));

            String data = currentDate + ", " + formattedTime + "\n";
            
            writer = new FileWriter("logs.txt", true);
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
