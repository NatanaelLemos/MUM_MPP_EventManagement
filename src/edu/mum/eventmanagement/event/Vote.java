package edu.mum.eventmanagement.event;

import java.util.List;
import edu.mum.eventmanagement.*;

import edu.mum.eventmanagement.Window;
import edu.mum.eventmanagement.models.Activity;
import edu.mum.eventmanagement.models.Event;
import edu.mum.eventmanagement.models.Schedule;
import edu.mum.eventmanagement.repositories.ScheduleRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class Vote {
	@FXML private TableView<Schedule> tblScheduler;
	@FXML private TableColumn<Event, String> name;
	private Event selectedEvent;
	ObservableList<Event> data;
	
	@FXML protected TableColumn<Schedule, String> colTimeStart;
	@FXML protected TableColumn<Schedule, String> colTimeEnd;
	@FXML protected TableColumn<Schedule, String> colActivity;
	@FXML protected TableColumn<Schedule, String> colScheduleState;
	
	@FXML protected Button btnVote;
	
	@FXML protected ToggleGroup toggleGr1 = new ToggleGroup();
	@FXML protected void voteAction(ActionEvent event) {
		//System.out.println( toggleGr1.getSelectedToggle().getUserData().toString());
		//RadioButton selectedRadioButton = (RadioButton) toggleGr1.getSelectedToggle();
		//String toogleGroupValue = selectedRadioButton.getText();
		//System.out.println(toogleGroupValue);
		
		Window.alert("Event created", "Thanks for your Voted");
	}
	
	
	public void initialize() {
		
		colTimeStart.setCellValueFactory(new PropertyValueFactory<Schedule, String>("timeStart"));
		colTimeEnd.setCellValueFactory(new PropertyValueFactory<Schedule, String>("timeEnd"));
		colActivity.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getActivity().getName()));
		ScheduleRepository sr = new ScheduleRepository();
		List<Schedule> scs = sr.getAll();
		tblScheduler.getItems().setAll(scs);
		System.out.println(scs.size());
		
		toggleGr1.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> 
        System.out.println("Selected toggle changed from "+oldToggle+" to "+newToggle));
	}
}
