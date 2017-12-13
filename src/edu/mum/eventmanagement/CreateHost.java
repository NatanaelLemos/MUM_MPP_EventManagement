package edu.mum.eventmanagement;

import edu.mum.eventmanagement.models.Country;
import edu.mum.eventmanagement.models.Host;
import edu.mum.eventmanagement.models.User;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class CreateHost extends CreateUser {

	@FXML protected Pane hostPane;
	
	@Override
	protected void save(String username, String email, String password, Country country) {
		getController().createHost(new User(username, email, password, country));
	}

	@Override
	protected Pane getWindowPane() {
		return hostPane;
	}

	@Override
	protected boolean validateUserRole(User user) {
		return user.hasRole(Host.class);
	}
}
