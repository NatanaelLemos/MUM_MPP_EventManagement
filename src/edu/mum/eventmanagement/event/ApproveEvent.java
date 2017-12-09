package edu.mum.eventmanagement.event;


import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


import edu.mum.eventmanagement.models.Event;
import edu.mum.eventmanagement.models.Schedule;
import edu.mum.eventmanagement.repositories.EventRepository;
import edu.mum.eventmanagement.repositories.LocationRepository;
import edu.mum.eventmanagement.repositories.ScheduleRepository;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ApproveEvent implements Initializable {
	@FXML private TableView<Event> tableView;
	@FXML private TableView<Schedule> tableViewScheduler;
	@FXML private TableColumn<Event, String> name;
	ObservableList<Event> data;
    //@FXML private TableColumn<Event, EventState> State;
	
	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
	    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub		
		
		 data = tableView.getItems();

		EventRepository er = new EventRepository();
		List <Event> events = er.getAll();
		for(Event e : events) {
			data.add(new Event(e.getName(), e.getDate(), e.getDueDate(), e.getLocation()));	
		}
		
		ScheduleRepository sr = new ScheduleRepository();
		//List<Schedule> Schedules = sr.getAll();
//		for(Schedule s : Schedules) {
//			tableViewScheduler.getItems().add(new Schedule(s.getTimeStart(), s.getTimeEnd()));
//		}
		
		LocationRepository lr = new LocationRepository();
		lr.getAll();
		
		tableView.getSelectionModel().selectedIndexProperty().addListener(
				new RowSelectChangeListener());
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
