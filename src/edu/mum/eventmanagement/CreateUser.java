package edu.mum.eventmanagement;

import edu.mum.eventmanagement.controllers.UserController;
import edu.mum.eventmanagement.models.Approver;
import edu.mum.eventmanagement.models.Country;
import edu.mum.eventmanagement.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public abstract class CreateUser {
	private UserController ctrl = new UserController();

	@FXML protected TextField txtEmail;
	@FXML protected TextField txtUsername;
	@FXML protected PasswordField txtPassword;
	@FXML protected ComboBox<Country> cbxCountry;

    @FXML
    public void initialize() {
    	setEmailEvent();
    	loadCbxCountry();
    }

    protected UserController getController() {
    	return ctrl;
    }
    
	protected abstract void save(String username, String email, String password, Country country);
	protected abstract Pane getWindowPane();
	protected abstract boolean validateUserRole(User user);
    
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
		
		save(txtUsername.getText(), txtEmail.getText(), txtPassword.getText(), cbxCountry.getValue());
		Window.alert("Success", "User created");
		Window.close(getWindowPane());
	}
	
	protected boolean isValid() {
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
		if(user != null && validateUserRole(user)) {
			if(msg.length() > 0) {
				msg.append("\r\n");
			}
			msg.append("This email is already related to a user with this role");
		}

		if (msg.length() > 0) {
			Window.error("Validation", msg.toString());
			return false;
		} else {
			return true;
		}
	}
}
