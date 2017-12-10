package edu.mum.eventmanagement;

import edu.mum.eventmanagement.controllers.UserController;
import edu.mum.eventmanagement.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class Login {
	private UserController ctrl = new UserController();
	
	@FXML protected TextField txtEmail;
	@FXML protected PasswordField txtPassword;
	@FXML protected Pane loginPane;
	
	@FXML protected void handleLoginAction(ActionEvent event) {
		
		if(!isValid()) {
			return;
		}
		
		User user = ctrl.getUserByLogin(txtEmail.getText(), txtPassword.getText());
		
		if(user == null) {
			Window.error("Not found", "User not found");
			return;
		}

		Session.getInstance().setUser(user);
		Window.close(loginPane);
	}
	
	private boolean isValid() {
		StringBuilder msg = new StringBuilder();
		
		if(txtEmail.getText().equals("")) {
			msg.append("Email is empty");
		}
		
		if(txtPassword.getText().equals("")) {
			if(msg.length() > 0) {
				msg.append("\r\n");
			}
			
			msg.append("Password is empty");
		}
		
		if(msg.length() > 0) {
			Window.error("Validation", msg.toString());
			return false;
		}else{
			return true;
		}
	}
}
