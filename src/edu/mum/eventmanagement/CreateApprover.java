package edu.mum.eventmanagement;

import edu.mum.eventmanagement.models.Approver;
import edu.mum.eventmanagement.models.Country;
import edu.mum.eventmanagement.models.User;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class CreateApprover extends CreateUser {

	@FXML protected Pane hostPane;
	
	@Override
	protected void save(String username, String email, String password, Country country) {
		getController().createApprover(new User(username, email, password, country));
	}

	@Override
	protected Pane getWindowPane() {
		return hostPane;
	}

	@Override
	protected boolean validateUserRole(User user) {
		return user.hasRole(Approver.class);
	}
	
}
