package edu.mum.eventmanagement.event;

import edu.mum.eventmanagement.Window;
import edu.mum.eventmanagement.WindowUtils;
import edu.mum.eventmanagement.controllers.UserController;
import edu.mum.eventmanagement.models.Country;
import edu.mum.eventmanagement.models.Guest;
import edu.mum.eventmanagement.models.Host;
import edu.mum.eventmanagement.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class registerGuest {
	private UserController ctrl = new UserController();

	@FXML protected Pane hostPane;
	@FXML protected TextField txtEmail;
	@FXML protected TextField txtUsername;
	@FXML protected PasswordField txtPassword;
	@FXML protected ComboBox<Country> cbxCountry;

    @FXML
    public void initialize() {
    	setEmailEvent();
    	loadCbxCountry();
    }
    
    private void setEmailEvent() {
    	txtEmail.focusedProperty().addListener((ov, oldV, newV)->{
    		if(newV) {
    			return;
    		}
    		
    		//focus lost
			User user = ctrl.getUserByEmail(txtEmail.getText());
			if(user == null) {
				return;
			}
			
			txtUsername.setText(user.getUsername());
			cbxCountry.setValue(user.getCountry());
    	});
    }
    
    private void loadCbxCountry() {
    	WindowUtils.loadCombobox(cbxCountry, ctrl.getCountries(), c -> c.getName());
    }
    
	@FXML
	protected void handleSaveAction(ActionEvent event) {
		if (!isValid()) {
			return;
		}

		ctrl.createGuest(new User(txtUsername.getText(), txtEmail.getText(), txtPassword.getText(), cbxCountry.getValue()));
		Window.alert("Success", "Guest created");
		Window.close(hostPane);
	}

	private boolean isValid() {
		StringBuilder msg = new StringBuilder();
		
		if(txtEmail.getText().equals("")) {
			msg.append("Email is empty");
		}
		
		if(txtUsername.getText().equals("")) {
			if(msg.length() > 0) {
				msg.append("\r\n");
			}
			msg.append("Username is empty");
		}
		
		if(txtPassword.getText().equals("")) {
			if(msg.length() > 0) {
				msg.append("\r\n");
			}
			msg.append("Password is empty");
		}
		
		if(cbxCountry.getValue() == null) {
			if(msg.length() > 0) {
				msg.append("\r\n");
			}
			msg.append("Country is empty");
		}

		User user = ctrl.getUserByEmail(txtEmail.getText());
		if(user != null && user.hasRole(Guest.class)) {
			if(msg.length() > 0) {
				msg.append("\r\n");
			}
			msg.append("This email is already related to a Host user");
		}

		if (msg.length() > 0) {
			Window.error("Validation", msg.toString());
			return false;
		} else {
			return true;
		}
	}
}
