package edu.mum.eventmanagement.event;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import edu.mum.eventmanagement.models.Event;
import edu.mum.eventmanagement.models.EventState;
import edu.mum.eventmanagement.repositories.EventRepository;
import fxmltableview.Person;
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
	    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub		
		
		ObservableList<Event> data = tableView.getItems();
		data.add(new Event("event 1", new Date(), new Date()));	
		System.out.print("ApproveEvent");
		
		EventRepository er = new EventRepository();
		List<Event> qwe = er.getAll();
		//System.out.println(qwe);
	}
}
