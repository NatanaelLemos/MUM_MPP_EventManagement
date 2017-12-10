package edu.mum.eventmanagement.event;


import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import edu.mum.eventmanagement.WindowUtils;
import edu.mum.eventmanagement.models.Activity;
import edu.mum.eventmanagement.models.Event;
import edu.mum.eventmanagement.models.Schedule;
import edu.mum.eventmanagement.repositories.ActivityRepository;
import edu.mum.eventmanagement.repositories.EventRepository;
import edu.mum.eventmanagement.repositories.ScheduleRepository;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ApproveEvent {
	@FXML private TableView<Event> tblEvents;
	@FXML protected TableColumn<Event, String> colEventName;
	@FXML protected TableColumn<Event, String> colEventDate;
	@FXML protected TableColumn<Event, String> colEventDueDate;
	@FXML protected TableColumn<Event, String> colEventLocation;
	
	@FXML private TableView<Schedule> tblScheduler;
	@FXML private TableView<Activity> tableViewActivity;
	@FXML private TableColumn<Event, String> name;
	private Event selectedEvent;
	ObservableList<Event> data;
	
	@FXML protected TableColumn<Schedule, String> colTimeStart;
	@FXML protected TableColumn<Schedule, String> colTimeEnd;

	
	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
	    
	public void initialize() {
		// TODO Auto-generated method stub		
		displayTblAdvertisements(false);
			
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
	
	private void displayTblAdvertisements(boolean isVisible) {
		tblScheduler.setVisible(isVisible);
	}
	
	private void loadSchedule(Event ev) {
		
		colTimeStart.setCellValueFactory(new PropertyValueFactory<Schedule, String>("timeStart"));
		colTimeEnd.setCellValueFactory(new PropertyValueFactory<Schedule, String>("timeEnd"));

		List<edu.mum.eventmanagement.models.Schedule> ss = ev.getSchedules();
		System.out.println(ss.size());
		if(ss.size() > 0) {
		tblScheduler.getItems().setAll(ss);
		displayTblAdvertisements(true);
		} else {
			displayTblAdvertisements(false);
		}
	}
	

	
	
	
}
