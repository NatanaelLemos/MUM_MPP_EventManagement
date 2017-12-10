package edu.mum.eventmanagement;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.application.Platform;

public class Menu extends Application implements ISessionObserver {

	public static void main(String[] args) {
		Application.launch(Menu.class, args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Session.getInstance().subscribe(this);
		
		Window mainStage = new Window("menu", "Event Management System", 1000, 700 );
		stage.setMaximized(true);
		stage.setScene(mainStage.getScene());
		stage.setTitle(mainStage.getTitle());
		stage.show();
	}
	
	@FXML protected Label lblCurrentUser;

	//made this because lblCurrentUser is null when onUserChange() is called
	private static Label lblCurrentUserInstance;		
    
	@FXML public void initialize() {
		lblCurrentUserInstance = lblCurrentUser;
	}
	
    @FXML protected void handleCloseAction(ActionEvent event) {
    	Platform.exit();
    }
    
    @FXML protected void handleAboutAction(ActionEvent event) {
    	Window about = new Window("about", "About", 400, 500);
    	about.show();
    }
    
    @FXML protected void handleCreateEventAction(ActionEvent event) {
    	Window createEvent = new Window("createEvent", "New Event", 740, 410);
    	createEvent.show();
    }
    

    @FXML protected void handleApproveEventAction(ActionEvent eveent) {
    	
    	Window createEvent = new Window("event/approveEvent", "Approve Event", 840, 500);
    	
    	createEvent.show();
    }

    @FXML protected void handleAdvertisementAction(ActionEvent event) {
    	Window advertisement = new Window("advertisement", "Advertisement", 960, 600);
    	advertisement.show();
    }
    
    @FXML protected void handleRegisterHostAction(ActionEvent event) {
    	Window createHost = new Window("createHost", "New Host", 740, 410);
    	createHost.show();
    }
    
    @FXML protected void handleLoginAction(ActionEvent event) {
    	Window advertisement = new Window("login", "Login", 600, 300);
    	advertisement.show();
    }
    
    @FXML protected void handleLogoutAction(ActionEvent event) {
    	
    }
    
    @FXML protected void handleInviteGuestAction(ActionEvent event) {
    	Window advertisement = new Window("event/InviteGuest", "Invite Guest", 300, 400);
    	advertisement.show();
    }
    
    @FXML protected void handleVoteAction(ActionEvent event) {
    	Window advertisement = new Window("event/Vote", "Vote for the best", 700, 350);
    	advertisement.show();
    }

	@Override
	public void onUserChange() {
		lblCurrentUserInstance.setText(Session.getInstance().getUser().getUsername());
	}
    
}
