package edu.mum.eventmanagement;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class CreateEvent {

	@FXML
	private TextField txtDate;
	@FXML
	private TextField txtDueDate;

	@FXML
	private ComboBox cbxLocation;

	@FXML
	protected void onDateKeyReleased(KeyEvent event) {
		WindowUtils.validateDate(txtDate);
		WindowUtils.validateDate(txtDueDate);
	}

	@FXML
	protected void handleCmbLocationShowing(Event event) {
		cbxLocation.getItems().clear();
		cbxLocation.getItems().add("Teste1");
		cbxLocation.getItems().add("Teste2");
		cbxLocation.getItems().add("Teste3");
		cbxLocation.getItems().add("Teste4");
	}
}
