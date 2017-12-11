package edu.mum.eventmanagement.event;

import java.util.ArrayList;
import java.util.List;

import edu.mum.eventmanagement.Window;
import edu.mum.eventmanagement.WindowUtils;
import edu.mum.eventmanagement.controllers.UserController;
import edu.mum.eventmanagement.models.Activity;
import edu.mum.eventmanagement.models.Event;
import edu.mum.eventmanagement.models.Schedule;
import edu.mum.eventmanagement.models.ScheduleState;
import edu.mum.eventmanagement.models.User;
import edu.mum.eventmanagement.repositories.EventRepository;
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
	
	@FXML private TableView<Event> tblEvents;
	@FXML protected TableColumn<Event, String> colEventName;
	@FXML protected TableColumn<Event, String> colEventDate;
	@FXML protected TableColumn<Event, String> colEventDueDate;
	@FXML protected TableColumn<Event, String> colEventLocation;
	@FXML protected TableColumn<Event, String> colEventState;
	
	//private UserController ctrl = new UserController();
	EventRepository eveRepo;
	public void initialize() {
		txtEmail.setFocusTraversable(true);
		eveRepo = new EventRepository();
		WindowUtils.setDateColumn(colEventDate);
		WindowUtils.setDateColumn(colEventDueDate);
        
		colEventName.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
        colEventDate.setCellValueFactory(new PropertyValueFactory<Event, String>("date"));
        colEventDueDate.setCellValueFactory(new PropertyValueFactory<Event, String>("dueDate"));
        colEventLocation.setCellValueFactory(new PropertyValueFactory<Event, String>("locationName"));
        
        List<Event> events = eveRepo.getAll();
		tblEvents.getItems().setAll(events);
		if(events.size()> 0 ) {
			tblEvents.getSelectionModel().selectFirst();
		}
	}

	@FXML private TableView<String> tblGuest;
	@FXML protected TableColumn<String, String> colEmail;
	@FXML Button btnInvite;
	@FXML TextField txtEmail;
	
	@FXML protected void inviteGuestAction(ActionEvent event) {

		System.out.println(txtEmail.getText());
		
		if(txtEmail.getLength()> 0 ) {
			Event newEv = tblEvents.getSelectionModel().getSelectedItem();
			EmailService.inviteGuest(newEv, txtEmail.getText());
			Window.alert("Event created", "Invited");
			txtEmail.clear();
			txtEmail.setFocusTraversable(true);
		} else {
			Window.error("Event created", "Please input the email");
		}
	}
}
