package edu.mum.eventmanagement;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @FXML public void initialize() {
		loadCbxLocation();
		
		ctrl.getLocations();
	}
	
	@FXML protected void onDateKeyReleased(KeyEvent event) {
		WindowUtils.validateDate(txtDate);
		WindowUtils.validateDate(txtDueDate);
	}
	
	private void loadCbxLocation() {
		WindowUtils.loadCombobox(cbxLocation, ctrl.getLocations(), i -> i.getName());
	}
	
	@FXML protected void handleNewLocation(javafx.event.Event event) {
		Window createLocation = new Window("createLocation", "New Location", 710, 380);
		createLocation.setOnHiding(e -> loadCbxLocation());
		createLocation.showAndWait();
	}

	@FXML protected void handleSaveAction(javafx.event.Event event) {
		try {
			if(!isValid()) {
				return;
			}
		
			Location location = cbxLocation.getValue();
			ctrl.create(
				new edu.mum.eventmanagement.models.Event(
					txtEventName.getText(), 
					dateFormat.parse(txtDate.getText()),
					dateFormat.parse(txtDueDate.getText()), 
					location
				)
			);

			Window.alert("Event created", "Event created");
			Window.close(eventPane);
		} catch (ParseException p) {
			Window.error("Date parsing", "Date parsing problem, try again");
		}
	}
	
	private boolean isValid() throws ParseException {
		StringBuilder msg = new StringBuilder();
		
		if(txtEventName.getText().equals("")) {
			msg.append("Event name is empty");
		}
		
		if(txtDate.getText().length() < 10) {
			if(msg.length() > 0) {
				msg.append("\r\n");
			}
			msg.append("Event date is not valid");
		}
		
		if(txtDueDate.getText().length() < 10) {
			if(msg.length() > 0) {
				msg.append("\r\n");
			}
			msg.append("Event due date is not valid");
		}
		
		if((txtDate.getText().length() == 10) && (txtDueDate.getText().length() == 10)) {
			Date date = dateFormat.parse(txtDate.getText());
			Date dueDate = dateFormat.parse(txtDueDate.getText());
			
			if(dueDate.compareTo(date) >= 0) {
				if(msg.length() > 0) {
					msg.append("\r\n");
				}
				msg.append("Due date should be before the event date");
			}
			
			if((new Date()).compareTo(date) >= 0) {
				if(msg.length() > 0) {
					msg.append("\r\n");
				}
				msg.append("Event date should be after today");
			}
			
			if((new Date()).compareTo(dueDate) >= 0) {
				if(msg.length() > 0) {
					msg.append("\r\n");
				}
				msg.append("Due date should be after today");
			}
		}

		Location location = cbxLocation.getValue(); 		
		if(location == null) {
			if(msg.length() > 0) {
				msg.append("\r\n");
			}
			msg.append("Location is empty");
		}

		if (location != null && (!txtDate.getText().equals(""))) {
			Date date = dateFormat.parse(txtDate.getText());
			if (ctrl.hasEventInSameDate(location, date)) {
				if (msg.length() > 0) {
					msg.append("\r\n");
				}
				msg.append(location.getName() + " already has an event in " + txtDate.getText());
			}
		}
		
		if(msg.length() > 0) {
			Window.error("Validation", msg.toString());
			return false;
		}else {
			return true;
		}
	}
}
