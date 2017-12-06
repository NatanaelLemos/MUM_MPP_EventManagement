package edu.mum.eventmanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public abstract class WindowBase extends Application {
	
	private String fXmlFile;
	private String title;
	private int width;
	private int height;
	private Boolean isMaximized;
		
	WindowBase(String fXmlFile, String title, int width, int height){
		this(fXmlFile, title, width, height, false);
	}
	
	WindowBase(String fXmlFile, String title, int width, int height, Boolean isMaximized){
		this.fXmlFile = fXmlFile;
		this.title = title;
		this.width = width;
		this.height = height;
		this.isMaximized = isMaximized;
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource(fXmlFile + ".fxml" ));
		stage.setTitle(title);
		
		Scene currentScene = new Scene(root, width, height);
		currentScene.getStylesheets().add(getClass().getResource("Application.css").toExternalForm());
        
		stage.setScene(currentScene);
		stage.setMaximized(isMaximized);		
		stage.show();
	}

}
