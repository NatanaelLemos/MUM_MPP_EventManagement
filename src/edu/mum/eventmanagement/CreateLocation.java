package edu.mum.eventmanagement;

import edu.mum.eventmanagement.controllers.LocationController;
import edu.mum.eventmanagement.models.Location;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class CreateLocation {

	private LocationController ctrl = new LocationController();

	@FXML
	private Pane locationPane;

	@FXML
	private TextField txtLocationName;
	@FXML
	private TextField txtAddress;

	@FXML
	protected void handleSaveAction(Event event) {
		if (!isValid()) {
			return;
		}

		ctrl.create(new Location(txtLocationName.getText(), txtAddress.getText()));
		Window.close(locationPane);
	}

	private boolean isValid() {
		StringBuilder msg = new StringBuilder();
		
		if(txtLocationName.getText().equals("")) {
			msg.append("Location name is empty");
		}
		
		if(txtAddress.getText().equals("")) {
			if(msg.length() > 0) {
				msg.append("\r\n");
			}
			
			msg.append("Address is empty");
		}
		
		if(msg.length() > 0) {
			Window.error("Validation", msg.toString());
			return false;
		}else {
			return true;
		}
	}
}
