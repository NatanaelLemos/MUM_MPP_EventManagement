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
import edu.mum.eventmanagement.models.EventState;
import edu.mum.eventmanagement.models.Schedule;
import edu.mum.eventmanagement.models.ScheduleState;
import edu.mum.eventmanagement.repositories.ActivityRepository;
import edu.mum.eventmanagement.repositories.EventRepository;
import edu.mum.eventmanagement.repositories.ScheduleRepository;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ApproveEvent {
	@FXML private TableView<Event> tblEvents;
	@FXML protected TableColumn<Event, String> colEventName;
	@FXML protected TableColumn<Event, String> colEventDate;
	@FXML protected TableColumn<Event, String> colEventDueDate;
	@FXML protected TableColumn<Event, String> colEventLocation;
	@FXML protected TableColumn<Event, String> colEventState;
	
	@FXML private TableView<Schedule> tblScheduler;
	@FXML private TableView<Activity> tableViewActivity;
	@FXML private TableColumn<Event, String> name;
	private Event selectedEvent;
	ObservableList<Event> data;
	
	EventRepository eveRepo;
	
	@FXML protected TableColumn<Schedule, String> colTimeStart;
	@FXML protected TableColumn<Schedule, String> colTimeEnd;
	@FXML protected TableColumn<Schedule, String> colActivity;
	@FXML protected TableColumn<Schedule, String> colScheduleState;
	
	
	@FXML protected Button btnApprove;
	@FXML protected Button btnDecline;
	
	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
	    
	public void initialize() {
		
		eveRepo = new EventRepository();
		// TODO Auto-generated method stub		
		displayTblSchedule(false);
		btnApprove.setVisible(false);
		btnDecline.setVisible(false);
			
		WindowUtils.setDateColumn(colEventDate);
		WindowUtils.setDateColumn(colEventDueDate);
        
		colEventName.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
        colEventDate.setCellValueFactory(new PropertyValueFactory<Event, String>("date"));
        colEventDueDate.setCellValueFactory(new PropertyValueFactory<Event, String>("dueDate"));
        colEventLocation.setCellValueFactory(new PropertyValueFactory<Event, String>("locationName"));
        colEventState.setCellValueFactory(new PropertyValueFactory<Event, String>("stateName"));
        
        List<Event> events = eveRepo.getWaitingForApproval();
		tblEvents.getItems().setAll(events);

		tblEvents.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			selectedEvent = newSelection;
			loadSchedule(newSelection);
		});
	}
	
	private void displayTblSchedule(boolean isVisible) {
		tblScheduler.setVisible(isVisible);
		
	}
	
	private void loadSchedule(Event ev) {
		
		colTimeStart.setCellValueFactory(new PropertyValueFactory<Schedule, String>("timeStart"));
		colTimeEnd.setCellValueFactory(new PropertyValueFactory<Schedule, String>("timeEnd"));
		colActivity.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getActivity().getName()));
		colScheduleState.setCellValueFactory(new PropertyValueFactory<Schedule, String>("StateName"));
		
		List<edu.mum.eventmanagement.models.Schedule> ss = ev.getSchedules();
		System.out.println(ss.size());
		if(ss.size() > 0) {
		tblScheduler.getItems().setAll(ss);
		displayTblSchedule(true);
		
		tblScheduler.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			btnApprove.setVisible(true);
			btnDecline.setVisible(true);	
		});

		} else {
			displayTblSchedule(false);
			
			btnApprove.setVisible(false);
			btnDecline.setVisible(false);
		}
	}
	
	@FXML protected void ApproveSchedule(ActionEvent event) {
		Schedule sc = tblScheduler.getSelectionModel().getSelectedItem();
		sc.setState(ScheduleState.approved);
		ScheduleRepository er = new ScheduleRepository();
        er.update(sc);
        		
        updateEventnScheduleGrid();
	}
	
	
	@FXML protected void DeclineSchedule(ActionEvent event) {
		Schedule sc = tblScheduler.getSelectionModel().getSelectedItem();
		sc.setState(ScheduleState.notApproved);
		ScheduleRepository er = new ScheduleRepository();
        er.update(sc);
        
        updateEventnScheduleGrid();

	}
	
	void updateEventnScheduleGrid() {
		// update again the schedule grid
		Event newEv = tblEvents.getSelectionModel().getSelectedItem();
		List<edu.mum.eventmanagement.models.Schedule> ss = newEv.getSchedules();
		System.out.println(ss.size());
		if(ss.size() > 0) {
		tblScheduler.getItems().setAll(ss);
		}
		
		int approveCount = 0;
 		int notApproveCount = 0;
 		for(Schedule ssc : ss){
 			if(ssc.getState() == ScheduleState.approved ) {
 				approveCount ++;
 			} else if (ssc.getState() == ScheduleState.notApproved) {
 				notApproveCount++;
 			}
 		}
 		
 		if(approveCount == ss.size()) {
 			newEv.setState(EventState.approved);
 		} else if(notApproveCount == ss.size()) {
 			newEv.setState(EventState.notApproved);
 		}
 		// update event
 		eveRepo.update(newEv);
 		
 		try {
 			List<Event> events = eveRepo.getWaitingForApproval();
 			tblEvents.getItems().setAll(events);
 		}catch(Exception e){/*some weird error occurring here*/}
	}
	
	
		
}
