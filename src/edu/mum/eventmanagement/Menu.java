package edu.mum.eventmanagement;

import edu.mum.eventmanagement.models.Host;
import edu.mum.eventmanagement.models.Approver;
import edu.mum.eventmanagement.models.Guest;
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
		EventMonitor.monitor();
		Window mainStage = new Window("menu", "Event Management System", 1000, 700 );
		stage.setMaximized(true);
		stage.setScene(mainStage.getScene());
		stage.setTitle(mainStage.getTitle());
		
		stage.setOnCloseRequest(r -> EventMonitor.finish());
		
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
    	
    	if(
    			(Session.getInstance().getUser() == null) || 
    			(!Session.getInstance().getUser().hasRole(Approver.class))
    		) {
        		Window.error("Denied", "You don't have permission to access this function");
        		return;
        	}
    	
    	Window createEvent = new Window("event/approveEvent", "Approve Event", 840, 550);	
    	createEvent.show();
    }

    @FXML protected void handleAdvertisementAction(ActionEvent event) {
    	Window advertisement = new Window("advertisement", "Advertisement", 960, 600);
    	advertisement.show();
    }
    
    @FXML protected void handleCreateScheduleAction(ActionEvent event) {
    	if(
			(Session.getInstance().getUser() == null) || 
			(!Session.getInstance().getUser().hasRole(Host.class))
		) {
    		Window.error("Denied", "You don't have permission to access this function");
    		return;
    	}
    	
    	Window schedule = new Window("createSchedule", "New Schedule", 960, 600);
    	schedule.show();
    }
    
    @FXML protected void handleRegisterHostAction(ActionEvent event) {
    	Window createHost = new Window("createHost", "New Host", 740, 410);
    	createHost.show();
    }
    
    @FXML protected void handleRegisterApproverAction(ActionEvent event) {
    	Window createApprover = new Window("createApprover", "New Approver", 740, 410);
    	createApprover.show();    	
    }
    
    @FXML protected void handleLoginAction(ActionEvent event) {
    	Window advertisement = new Window("login", "Login", 600, 300);
    	advertisement.show();
    }
    
    @FXML protected void handleLogoutAction(ActionEvent event) {
    	Session.getInstance().clear();
    }
    
    @FXML protected void handleInviteGuestAction(ActionEvent event) {
    	Window advertisement = new Window("event/InviteGuest", "Invite Guest", 800, 410);
    	advertisement.show();
    }
    
    @FXML protected void handleVoteAction(ActionEvent event) {
    	
    	if(
    			(Session.getInstance().getUser() == null) || 
    			(!Session.getInstance().getUser().hasRole(Guest.class))
    		) {
        		Window.error("Denied", "You don't have permission to access this function");
        		return;
        	}
        	
    	Window advertisement = new Window("event/Vote", "Vote for the best", 900, 500);
    	advertisement.show();
    	
    	
    }
    
    @FXML protected void handlePrizeAction(ActionEvent event) {
    	if(Session.getInstance().getUser() == null) {
    		Window.error("Denied", "You don't have permission to access this function");
    		return;
    	}
    	
    	boolean hasHostRole = Session.getInstance().getUser().hasRole(Host.class);
    	boolean hasApproverRole = Session.getInstance().getUser().hasRole(Approver.class);
    	
    	if(!hasHostRole && !hasApproverRole){
    		Window.error("Denied", "You don't have permission to access this function");
    		return;
    	}
        	
    	Window prize = new Window("prize", "Prize for the winner", 900, 500);
    	prize.show();
    }

	@Override
	public void onUserChange() {
		if(Session.getInstance().getUser() == null) {
			lblCurrentUserInstance.setText("");
		}else {
			lblCurrentUserInstance.setText(Session.getInstance().getUser().getUsername());
		}
	}
	
	@FXML protected void handleRegisterGuestAction(ActionEvent event) {
		Window advertisement = new Window("event/registerGuest", "Register Guest", 740, 410);
    	advertisement.show();
	}
}
