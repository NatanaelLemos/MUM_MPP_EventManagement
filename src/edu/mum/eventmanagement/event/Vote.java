package edu.mum.eventmanagement.event;

import java.util.List;
import edu.mum.eventmanagement.*;
import edu.mum.eventmanagement.controllers.UserController;
import edu.mum.eventmanagement.models.Activity;
import edu.mum.eventmanagement.models.Event;
import edu.mum.eventmanagement.models.Guest;
import edu.mum.eventmanagement.models.Schedule;
import edu.mum.eventmanagement.models.User;
import edu.mum.eventmanagement.repositories.EventRepository;
import edu.mum.eventmanagement.repositories.ScheduleRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class Vote {
	
	@FXML private TableView<Event> tblEvents;
	@FXML protected TableColumn<Event, String> colEventName;
	@FXML protected TableColumn<Event, String> colEventDate;
	@FXML protected TableColumn<Event, String> colEventDueDate;
	@FXML protected TableColumn<Event, String> colEventLocation;

	@FXML private TableView<Schedule> tblScheduler;
	@FXML private TableColumn<Event, String> name;
	private Event selectedEvent;
	ObservableList<Event> data;
	
	@FXML protected Label lblVote;
	
	@FXML protected TableColumn<Schedule, String> colTimeStart;
	@FXML protected TableColumn<Schedule, String> colTimeEnd;
	@FXML protected TableColumn<Schedule, String> colActivity;
	@FXML protected TableColumn<Schedule, String> colScheduleState;
	
	@FXML protected Button btnVote;
	
	@FXML protected ToggleGroup toggleGr1 = new ToggleGroup();
	@FXML protected void voteAction(ActionEvent event) {
		ScheduleRepository sr = new ScheduleRepository();
		Schedule sc = tblScheduler.getSelectionModel().getSelectedItem();
		Guest u = Session.getInstance().getUser().getRole(Guest.class);
		sc.getVotes().add(u);
		sr.update(sc);
		Window.alert("Vote", "Thanks for your Voted");
		tblScheduler.setSelectionModel(null);
		btnVote.setDisable(true);
	}
	
	
	public void initialize() {
		
		colTimeStart.setCellValueFactory(new PropertyValueFactory<Schedule, String>("timeStart"));
		colTimeEnd.setCellValueFactory(new PropertyValueFactory<Schedule, String>("timeEnd"));
		colActivity.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getActivity().getName()));
		ScheduleRepository sr = new ScheduleRepository();
		List<Schedule> scs = sr.getAll();
	
		WindowUtils.setDateColumn(colEventDate);
		WindowUtils.setDateColumn(colEventDueDate);
        
		colEventName.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
        colEventDate.setCellValueFactory(new PropertyValueFactory<Event, String>("date"));
        colEventDueDate.setCellValueFactory(new PropertyValueFactory<Event, String>("dueDate"));
        colEventLocation.setCellValueFactory(new PropertyValueFactory<Event, String>("locationName"));
		
        EventRepository er = new EventRepository();
        List<Event> events = er.getAll();
		tblEvents.getItems().setAll(events);

		tblEvents.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			selectedEvent = newSelection;
			loadSchedule(newSelection);
		});
		
		
	}
	private void loadSchedule(Event ev) {
		
		colTimeStart.setCellValueFactory(new PropertyValueFactory<Schedule, String>("timeStart"));
		colTimeEnd.setCellValueFactory(new PropertyValueFactory<Schedule, String>("timeEnd"));
		colActivity.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getActivity().getName()));
		//colScheduleState.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getState().toString()));

		List<edu.mum.eventmanagement.models.Schedule> ss = ev.getSchedules();
		System.out.println(ss.size());
		if(ss.size() > 0) {
			tblScheduler.getItems().setAll(ss);	
			
			List<Guest> votes = ev.getVotes();
			
			Guest guest = Session.getInstance().getUser().getRole(Guest.class);
			if(votes.contains(guest)) {
				btnVote.setVisible(false);
				lblVote.setVisible(true);
			}
		} else {
			
		}
		
	}
}
