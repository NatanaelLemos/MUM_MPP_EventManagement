package edu.mum.eventmanagement;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;

public class About {
    @FXML private Pane aboutGrid;
	
    @FXML protected void handleCloseAction(ActionEvent event) {
    	aboutGrid.getScene().getWindow().hide();
    }
}
