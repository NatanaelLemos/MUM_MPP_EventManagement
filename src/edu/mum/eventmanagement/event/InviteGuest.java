package edu.mum.eventmanagement.event;

import java.util.ArrayList;
import java.util.List;

import edu.mum.eventmanagement.Window;
import edu.mum.eventmanagement.controllers.UserController;
import edu.mum.eventmanagement.models.Activity;
import edu.mum.eventmanagement.models.Event;
import edu.mum.eventmanagement.models.Schedule;
import edu.mum.eventmanagement.models.ScheduleState;
import edu.mum.eventmanagement.models.User;
import edu.mum.eventmanagement.repositories.ScheduleRepository;
import edu.mum.eventmanagement.services.EmailService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class InviteGuest {
	
	private UserController ctrl = new UserController();

	@FXML private TableView<String> tblGuest;
	@FXML protected TableColumn<String, String> colEmail;
	@FXML Button btnInvite;
	@FXML TextField txtEmail;
	
	@FXML protected void inviteGuestAction(ActionEvent event) {

		System.out.println(txtEmail.getText());
		
		if(txtEmail.getLength()> 0 ) {
			EmailService.inviteGuest(txtEmail.getText());
			Window.alert("Event created", "Invited");
		} else {
			Window.error("Event created", "Please input the email");
		}
	}
}
