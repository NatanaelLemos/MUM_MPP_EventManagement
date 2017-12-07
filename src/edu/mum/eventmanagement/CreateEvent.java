package edu.mum.eventmanagement;

import edu.mum.eventmanagement.models.*;
import edu.mum.eventmanagement.controllers.EventController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;

public class CreateEvent {

	private EventController ctrl = new EventController();
	
	@FXML
	private TextField txtDate;
	@FXML
	private TextField txtDueDate;

	@FXML
	private ComboBox<Location> cbxLocation;

	@FXML
	protected void onDateKeyReleased(KeyEvent event) {
		WindowUtils.validateDate(txtDate);
		WindowUtils.validateDate(txtDueDate);
	}

	@FXML
	protected void handleCmbLocationShowing(Event event) {
		cbxLocation.getItems().clear();
		cbxLocation.setConverter(new StringConverter<Location>() {

		    @Override
		    public String toString(Location object) {
		        return object.getName();
		    }

		    @Override
		    public Location fromString(String string) {
		    	return cbxLocation.getItems().stream().filter(i -> i.getName().equals(string)).findFirst().orElse(null);
		    }
		});

		for(Location l : ctrl.getLocations()) {
			cbxLocation.getItems().add(l);	
		}
	}
}
