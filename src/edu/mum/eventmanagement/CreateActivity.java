package edu.mum.eventmanagement;

import edu.mum.eventmanagement.controllers.ActivityController;
import edu.mum.eventmanagement.models.Food;
import edu.mum.eventmanagement.models.Performance;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class CreateActivity {
	private ActivityController ctrl = new ActivityController();
	
	@FXML protected Pane activityPane;
	@FXML protected TextField txtActivityName;
	@FXML protected RadioButton rbtFood;
	@FXML protected RadioButton rbtPerformance; 
	
	@FXML protected void handleSaveAction(ActionEvent event) {
		
		if(!isValid()) {
			return;
		}
		
		if(rbtPerformance.isSelected()) {
			ctrl.create(new Performance(txtActivityName.getText()));
		}else {
			ctrl.create(new Food(txtActivityName.getText()));
		}
		
		Window.close(activityPane);
	}
	
	private boolean isValid() {
		StringBuilder msg = new StringBuilder();
		
		if(txtActivityName.getText().equals("")) {
			msg.append("Activity name is empty");
		}
		
		if((!rbtFood.isSelected()) && (!rbtPerformance.isSelected())) {
			if(msg.length() > 0) {
				msg.append("\r\n");
			}
			
			msg.append("No activity type selected");
		}
		
		if(msg.length() > 0) {
			Window.error("Validation", msg.toString());
			return false;
		}else {
			return true;
		}
	}
}
