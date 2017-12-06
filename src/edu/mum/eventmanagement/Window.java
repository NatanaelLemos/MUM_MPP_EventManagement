package edu.mum.eventmanagement;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Window extends Stage {

	Window(String fXmlFile, String title, int width, int height) {
		this(fXmlFile, title, width, height, false);
	}

	Window(String fXmlFile, String title, int width, int height, Boolean isMaximized) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(fXmlFile + ".fxml"));
			this.setTitle(title);

			Scene currentScene = new Scene(root, width, height);
			currentScene.getStylesheets().add(getClass().getResource("Application.css").toExternalForm());

			this.setScene(currentScene);
			this.setMaximized(isMaximized);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
