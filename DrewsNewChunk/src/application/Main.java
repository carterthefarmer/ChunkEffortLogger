package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		try {
	    	Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
	    	Scene loginScreen = new Scene(root);
//	    	Image icon = new Image(getClass().getResourceAsStream("images/cookie.png"));
//	    	primaryStage.getIcons().add(icon);
	    	primaryStage.setScene(loginScreen);
	    	primaryStage.setTitle("Chunk Efffort Logger V1.2");
	    	primaryStage.show();
	    	}
	    	catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void changeScene(String fxml) throws IOException{
	    Parent pane = FXMLLoader.load(getClass().getResource(fxml));
	    primaryStage.getScene().setRoot(pane);
	}

}