package application;

import java.util.ArrayList;
import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.scene.control.ComboBox;

public class LogController {
    private ComboBox<Log> comboBox;
    private LogModel logModel;
    private Timeline timeline;

    public LogController(ComboBox<Log> comboBox) {
        this.comboBox = comboBox;
        logModel = new LogModel();
    }

//    public void addLogEntry(String designStep, int StoryPoints) {
//        logModel.addLog(designStep, );
//        updateComboBox();
//    }

    private void updateComboBox() {
        ArrayList<Log> logEntries = logModel.getLogEntries();
        comboBox.getItems().clear();
        comboBox.getItems().addAll(logEntries);
    }

}

