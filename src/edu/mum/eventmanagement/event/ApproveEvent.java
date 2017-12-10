package edu.mum.eventmanagement.event;


import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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

public class ApproveEvent implements Initializable {
	@FXML private TableView<Event> tblEvents;
	@FXML protected TableColumn<Event, String> colEventName;
	@FXML protected TableColumn<Event, String> colEventDate;
	@FXML protected TableColumn<Event, String> colEventDueDate;
	@FXML protected TableColumn<Event, String> colEventLocation;
	
	@FXML private TableView<Schedule> tableViewScheduler;
	@FXML private TableView<Activity> tableViewActivity;
	@FXML private TableColumn<Event, String> name;
	private Event selectedEvent;
	ObservableList<Event> data;
	
	@FXML protected TableColumn<edu.mum.eventmanagement.models.Schedule, String> colTimeStart;
	@FXML protected TableColumn<edu.mum.eventmanagement.models.Schedule, String> colTtimeEnd;
	@FXML protected TableColumn<edu.mum.eventmanagement.models.Schedule, String> colActivityName;

    //@FXML private TableColumn<Event, EventState> State;
	
	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
	    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub		
		
//		 data = tblEvents.getItems();
//
//		EventRepository er = new EventRepository();
//		List <Event> events = er.getAll();
//		for(Event e : events) {
//			data.add(new Event(e.getName(), e.getDate(), e.getDueDate(), e.getLocation()));	
//		}
		
		WindowUtils.setDateColumn(colEventDate);
		WindowUtils.setDateColumn(colEventDueDate);
        
		colEventName.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
        colEventDate.setCellValueFactory(new PropertyValueFactory<Event, String>("date"));
        colEventDueDate.setCellValueFactory(new PropertyValueFactory<Event, String>("dueDate"));
        colEventLocation.setCellValueFactory(new PropertyValueFactory<Event, String>("locationName"));
		
        EventRepository er = new EventRepository();
        List<Event> events = er.getAll();
		tblEvents.getItems().setAll(events);

//		for(Schedule s : Schedules) {
//			tableViewScheduler.getItems().add(new Schedule(s.getTimeStart(), s.getTimeEnd()));
//		}
		
		//ActivityRepository ar = new ActivityRepository();
		//ar.getAll();
		
		//ScheduleRepository sr = new ScheduleRepository();
		//sr.getAll();
		
		
		//tableView.getSelectionModel().selectedIndexProperty().addListener(
		//		new RowSelectChangeListener());
		
		tblEvents.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			selectedEvent = newSelection;
			loadSchedule(newSelection);
		});
	}
	
	private void loadSchedule(Event ev) {
		
		

//		colTimeStart.setCellValueFactory(new PropertyValueFactory<edu.mum.eventmanagement.models.Schedule, String>("timeStart"));
//		colTimeStart.setCellValueFactory(new PropertyValueFactory<edu.mum.eventmanagement.models.Schedule, String>("timeEnd"));
//
		List<edu.mum.eventmanagement.models.Schedule> ss = ev.getSchedules();
		System.out.println(ss.size());
//		tableViewScheduler.getItems().setAll(ss);
	}
	
	private class RowSelectChangeListener implements ChangeListener<Number> {

		@Override
		public void changed(ObservableValue<? extends Number> ov, 
				Number oldVal, Number newVal) {

			int ix = newVal.intValue();
			
			//System.out.print(ix);

			if ((ix < 0) || (ix >= data.size())) {
	
				return; // invalid data
			}

			Event ev = data.get(ix);
			System.out.println(ev.getName());
		}
	}
	
	
	
}
