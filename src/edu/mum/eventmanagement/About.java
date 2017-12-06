package edu.mum.eventmanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class About extends Stage{
	public About() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("about.fxml" ));
			this.setTitle("About")   ;
			
			Scene currentScene = new Scene(root, 300, 500);
			currentScene.getStylesheets().add(getClass().getResource("Application.css").toExternalForm());
	        
			this.setScene(currentScene);
			this.setMaximized(false);	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
    @FXML protected void handleCloseAction(ActionEvent event) {
    	this.hide();
    }
}
