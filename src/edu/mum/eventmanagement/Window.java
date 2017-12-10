package edu.mum.eventmanagement;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.function.Function;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;

public class Window extends Stage {

	Window(String fXmlFile, String title, int width, int height) {
		this(fXmlFile, title, width, height, false);
	}

	Window(String fXmlFile, String title, int width, int height, Boolean isMaximized) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(fXmlFile + ".fxml"));
			this.setTitle(title);

			customizeRoot(root);

			Scene currentScene = new Scene(root, width, height);
			currentScene.getStylesheets().add(getClass().getResource("Bootstrap.css").toExternalForm());
			currentScene.getStylesheets().add(getClass().getResource("Application.css").toExternalForm());

			this.setScene(currentScene);
			this.setMaximized(isMaximized);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void customizeRoot(Parent parent) {
		for (Node node : parent.getChildrenUnmodifiable()) {
			if (node instanceof Button) {
				((Button) node).setPrefHeight(30);
			}
			if (node instanceof Parent) {
				customizeRoot((Parent) node);
			}
		}
	}
	
	static void close(Pane windowToClose) {
		windowToClose.getScene().getWindow().hide();
	}
	
	public static void alert(String title, String message) {
		showAlert(AlertType.INFORMATION, title, message);
	}
	
	static void error(String title, String message) {
		showAlert(AlertType.ERROR, title, message);
	}
	
	static void confirm(String title, String message, Runnable okCallback, Runnable cancelCallback) {
		Optional<ButtonType> result = showAlert(AlertType.CONFIRMATION, title, message);

		if (result.get() == ButtonType.OK){
			if(okCallback != null) {
				okCallback.run();
			}
		} else {
			if(cancelCallback != null) {
				cancelCallback.run();
			}
		}
	}
	
	private static Optional<ButtonType> showAlert(AlertType type, String title, String message) {
		javafx.scene.control.Alert alert = new javafx.scene.control.Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		return alert.showAndWait();
	}
	
	static File chooseFile(String title) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(title);
		return fileChooser.showOpenDialog(new Stage());	
	}
}
