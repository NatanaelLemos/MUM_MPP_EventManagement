package edu.mum.eventmanagement;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;

public class About {
    @FXML private Pane aboutPane;
	
    @FXML protected void handleCloseAction(ActionEvent event) {
    	aboutPane.getScene().getWindow().hide();
    }
}
