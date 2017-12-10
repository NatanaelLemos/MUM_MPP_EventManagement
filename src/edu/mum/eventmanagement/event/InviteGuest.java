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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class InviteGuest {
	
	private UserController ctrl = new UserController();
//	@FXML private TableView<Event> tblEvents;
//	@FXML protected TableColumn<Event, String> colEventName;
//	@FXML protected TableColumn<Event, String> colEventDate;
//	@FXML protected TableColumn<Event, String> colEventDueDate;
//	@FXML protected TableColumn<Event, String> colEventLocation;
//	
//	@FXML private TableView<Schedule> tblScheduler;
//	@FXML private TableView<Activity> tableViewActivity;
//	@FXML private TableColumn<Event, String> name;
//	private Event selectedEvent;
//	ObservableList<Event> data;
//	
//	@FXML protected TableColumn<Schedule, String> colTimeStart;
//	@FXML protected TableColumn<Schedule, String> colTimeEnd;
//	@FXML protected TableColumn<Schedule, String> colActivity;
//	@FXML protected TableColumn<Schedule, String> colScheduleState;
	
	@FXML private TableView<String> tblGuest;
	@FXML protected TableColumn<Event, String> colEmail;
	@FXML Button btnInvite;
	@FXML TextField txtEmail;
	
	@FXML protected void inviteGuestAction(ActionEvent event) {
		ctrl.InviteGuest(new User(null, txtEmail.getText(), null, null));
		//Window.alert("Success", "Host created");
		//System.out.println(txtEmail.getText());
		ArrayList<String> data = new ArrayList<String>();
		data.add("asdf");
		tblGuest.getItems().setAll(data);
	}
}
