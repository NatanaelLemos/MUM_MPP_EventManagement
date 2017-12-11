package edu.mum.eventmanagement;

import edu.mum.eventmanagement.controllers.GiftController;
import edu.mum.eventmanagement.controllers.LocationController;
import edu.mum.eventmanagement.models.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class CreateGift {

	private GiftController ctrl = new GiftController();

	@FXML private Pane giftPane;
	@FXML private TextField txtGiftName;

	@FXML
	protected void handleSaveAction(Event event) {
		if (!isValid()) {
			return;
		}

		ctrl.create(new Gift(txtGiftName.getText()));
		Window.close(giftPane);
	}

	private boolean isValid() {
		StringBuilder msg = new StringBuilder();
		
		if(txtGiftName.getText().equals("")) {
			msg.append("Gift name is empty");
		}
		
		if(msg.length() > 0) {
			Window.error("Validation", msg.toString());
			return false;
		}else {
			return true;
		}
	}
}
