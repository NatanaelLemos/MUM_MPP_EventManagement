package edu.mum.eventmanagement.event;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import edu.mum.eventmanagement.models.Event;
import edu.mum.eventmanagement.models.EventState;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ApproveEvent implements Initializable {
	@FXML private TableView<Event> EventableId;
	@FXML private TableColumn<Event, String> name;
    //@FXML private TableColumn<Event, EventState> State;
	    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		name.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
		//State.setCellValueFactory(new PropertyValueFactory<Event, EventState>("State"));
		
		List<Event> events = new ArrayList<Event>();
		events.add(new Event("asdf",new Date(),new Date()));
		System.out.print("ApproveEvent1");
		//EventableId.getItems().setAll(FXCollections.observableArrayList(events));
		
		//EventableId.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		//EventableId.getColumns().get(0).prefWidthProperty().bind(EventableId.widthProperty().multiply(0.33));    // 33% for id column size
		//EventableId.getColumns().get(1).prefWidthProperty().bind(EventableId.widthProperty().multiply(0.33));   // 33% for dt column size
//		  // 33% for cv column size
		//EventableId.getItems().setAll(events);
	//}
		
		System.out.print("ApproveEvent");
	}
//	public ApproveEvent() {
//		System.out.print("ApproveEvent");
//	}
}
