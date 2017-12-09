package edu.mum.eventmanagement.event;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import edu.mum.eventmanagement.models.Event;
import edu.mum.eventmanagement.models.EventState;
import edu.mum.eventmanagement.repositories.EventRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ApproveEvent implements Initializable {
	@FXML private TableView<Event> tableView;
	@FXML private TableColumn<Event, String> name;
    //@FXML private TableColumn<Event, EventState> State;
	
	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
	    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub		
		
		ObservableList<Event> data = tableView.getItems();

		EventRepository er = new EventRepository();
		List <Event> events = er.getAll();
		for(Event e : events) {
			data.add(new Event(e.getName(), e.getDate(), e.getDueDate(), e.getLocation()));	
		}
	}
	
	@FXML
	public void ClickItem(MouseEvent event) {
		System.out.println("click 123");
	}
	
}
