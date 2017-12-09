package edu.mum.eventmanagement;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import edu.mum.eventmanagement.controllers.EventController;
import edu.mum.eventmanagement.models.Location;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;

public class CreateEvent {

	private EventController ctrl = new EventController();
	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);

	@FXML private TextField txtEventName;
	@FXML private TextField txtDate;
	@FXML private TextField txtDueDate;
	@FXML private ComboBox<Location> cbxLocation;
	@FXML private Pane eventPane;

	@FXML
	protected void onDateKeyReleased(KeyEvent event) {
		WindowUtils.validateDate(txtDate);
		WindowUtils.validateDate(txtDueDate);
	}

	@FXML
	protected void handleCmbLocationShowing(javafx.event.Event event) {
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

		for (Location l : ctrl.getLocations()) {
			cbxLocation.getItems().add(l);
		}
	}

	@FXML
	protected void handleNewLocation(javafx.event.Event event) {
		Window createLocation = new Window("createLocation", "New Location", 710, 380);
		createLocation.show();
	}

	@FXML
	protected void handleSaveAction(javafx.event.Event event) {
		if(!isValid()) {
			return;
		}
		
		try {
			Location location = cbxLocation.getValue();
			ctrl.create(
				new edu.mum.eventmanagement.models.Event(
					txtEventName.getText(), 
					dateFormat.parse(txtDate.getText()),
					dateFormat.parse(txtDueDate.getText()), 
					location
				)
			);
		} catch (ParseException p) {
			Window.Error("Date parsing", "Date parsing problem, try again");
		}
		
		Window.Alert("Event created", "Event created");
		Window.close(eventPane);
	}
	
	private boolean isValid() {
		StringBuilder msg = new StringBuilder();
		
		if(txtEventName.getText().equals("")) {
			msg.append("Event name is empty");
		}
		
		if(txtDate.getText().equals("")) {
			if(msg.length() > 0) {
				msg.append("\r\n");
			}
			msg.append("Event date is empty");
		}
		
		if(txtDueDate.getText().equals("")) {
			if(msg.length() > 0) {
				msg.append("\r\n");
			}
			msg.append("Event due date is empty");
		}

		if(cbxLocation.getValue() == null) {
			if(msg.length() > 0) {
				msg.append("\r\n");
			}
			msg.append("Location is empty");
		}
		
		if(msg.length() > 0) {
			Window.Error("Validation", msg.toString());
			return false;
		}else {
			return true;
		}
	}
}
