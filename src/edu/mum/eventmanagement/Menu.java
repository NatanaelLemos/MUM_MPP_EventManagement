package edu.mum.eventmanagement;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.application.Platform;

@SuppressWarnings("restriction")
public class Menu extends WindowBase {

	public static void main(String[] args) {
		Application.launch(Menu.class, args);
	}
	
	public Menu() {
		super("Menu", "Event Management System", 300, 200, true);
	}
	
    @FXML protected void handleCloseAction(ActionEvent event) {
    	Platform.exit();
    }
    
    @FXML protected void handleAboutAction(ActionEvent event) {
    	About about = new About();
    	about.show();
    }
}
