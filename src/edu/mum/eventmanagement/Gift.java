package edu.mum.eventmanagement;

import java.util.List;

import edu.mum.eventmanagement.controllers.ScheduleController;
import edu.mum.eventmanagement.models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Gift {
	private ScheduleController ctrl = new ScheduleController();
	private Event selectedEvent;
	
	@FXML protected Pane scPane;
	
	@FXML protected TableView<Event> tblEvents;
	@FXML protected TableColumn<Event, String> colEventName;
	@FXML protected TableColumn<Event, String> colEventDate;
	@FXML protected TableColumn<Event, String> colEventDueDate;
	@FXML protected TableColumn<Event, String> colEventLocation;
	
	@FXML protected TableView<Schedule> tblSchedules;
	@FXML protected TableColumn<Schedule, String> colScStart;
	@FXML protected TableColumn<Schedule, String> colScEnd;
	@FXML protected TableColumn<Schedule, String> colScActivityName;
	@FXML protected TableColumn<Schedule, String> colScVotes;

	@FXML protected GridPane pnlSchedule;
	@FXML protected ComboBox<Activity> cbxActivities;
	
    @FXML public void initialize() {
		displayTblSchedules(false);		
		loadCbxActivities();
		loadEvents();
	}
	
	private void displayTblSchedules(boolean isVisible) {
		tblSchedules.setVisible(isVisible);
		pnlSchedule.setVisible(isVisible);
		cbxActivities.setValue(null);
	}
	
	private void loadCbxActivities() {
		List<Activity> activities = ctrl.getActivities();
		WindowUtils.loadCombobox(cbxActivities, activities, i -> i.getDescription());
	}
	
	private void loadEvents() {
		WindowUtils.setDateColumn(colEventDate);
		WindowUtils.setDateColumn(colEventDueDate);
        
		colEventName.setCellValueFactory(new PropertyValueFactory<Event, String>("name"));
        colEventDate.setCellValueFactory(new PropertyValueFactory<Event, String>("date"));
        colEventDueDate.setCellValueFactory(new PropertyValueFactory<Event, String>("dueDate"));
        colEventLocation.setCellValueFactory(new PropertyValueFactory<Event, String>("locationName"));
        		
		List<Event> events = ctrl.getEvents();
		tblEvents.getItems().setAll(events);
		
		tblEvents.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			selectedEvent = newSelection;
		    loadSchedules(newSelection);
		});
	}
	
	private void loadSchedules(Event event) {
		colScStart.setCellValueFactory(new PropertyValueFactory<Schedule, String>("timeStart"));
		colScEnd.setCellValueFactory(new PropertyValueFactory<Schedule, String>("timeEnd"));
		colScActivityName.setCellValueFactory(new PropertyValueFactory<Schedule, String>("activityName"));
		colScVotes.setCellValueFactory(new PropertyValueFactory<Schedule, String>("votesQty"));
		
		List<Schedule> scs = event.getSchedules();
		tblSchedules.getItems().setAll(scs);

		displayTblSchedules(true);
	}
	
	@FXML protected void handleSaveAction(ActionEvent event) {
		if(!isValid()) {
			return;
		}
	}
	
	private boolean isValid() {
		StringBuilder msg = new StringBuilder();
		
		return false;
	}
}
