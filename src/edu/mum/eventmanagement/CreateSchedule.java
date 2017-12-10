package edu.mum.eventmanagement;

import java.util.Arrays;
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

public class CreateSchedule {
	private ScheduleController ctrl = new ScheduleController();
	private Event selectedEvent;
	private Schedule selectedSchedule;
	
	@FXML protected Pane schedulePane;
	
	@FXML protected TableView<Event> tblEvents;
	@FXML protected TableColumn<Event, String> colEventName;
	@FXML protected TableColumn<Event, String> colEventDate;
	@FXML protected TableColumn<Event, String> colEventDueDate;
	@FXML protected TableColumn<Event, String> colEventLocation;
	
	@FXML protected TableView<Schedule> tblSchedules;
	@FXML protected TableColumn<Schedule, String> colScStart;
	@FXML protected TableColumn<Schedule, String> colScEnd;
	@FXML protected TableColumn<Schedule, String> colScActivityName;
	@FXML protected TableColumn<Schedule, String> colScActivityType;

	@FXML protected Button btnNewSchedule;
	@FXML protected Button btnRemoveSchedule;
	
	@FXML protected GridPane pnlSchedule;
	@FXML protected TextField txtTimeStart;
	@FXML protected TextField txtTimeEnd;
	@FXML protected ComboBox<Activity> cbxActivities;
	
    @FXML public void initialize() {
		displayTblSchedules(false);
		displayPnlSchedule(false);
		
		loadCbxActivities();
		loadEvents();
	}
	
	private void displayTblSchedules(boolean isVisible) {
		tblSchedules.setVisible(isVisible);
		btnNewSchedule.setVisible(isVisible);
		btnRemoveSchedule.setVisible(isVisible);
	}
	
	private void displayPnlSchedule(boolean isVisible) {
		pnlSchedule.setVisible(isVisible);
		txtTimeStart.setText("");
		txtTimeEnd.setText("");
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
		colScActivityType.setCellValueFactory(new PropertyValueFactory<Schedule, String>("activityType"));
		
		List<Schedule> scs = event.getSchedules();
		tblSchedules.getItems().setAll(scs);

		tblSchedules.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			selectedSchedule = newSelection;
		});
		
		displayTblSchedules(true);
	}
	
	@FXML protected void handleNewSchedule(ActionEvent event) {
		displayPnlSchedule(true);
	}
	
	@FXML protected void handleDeleteSchedule(ActionEvent event) {
		if(selectedSchedule == null) return;
		
		Window.confirm("Sure?", "Do you really want to delete this schedule?", () -> {
			ctrl.delete(selectedSchedule);
			loadSchedules(selectedEvent);
		}, null);
	}
	
	@FXML protected void handleNewActivityAction(ActionEvent event) {
		Window createActivity = new Window("createActivity", "New Activity", 710, 380);
		createActivity.setOnHiding(e -> loadCbxActivities());
		createActivity.showAndWait();
	}
	
	@FXML protected void handleSaveAction(ActionEvent event) {
		if(!isValid()) {
			return;
		}
		
		ctrl.create(new Schedule(txtTimeStart.getText(), txtTimeEnd.getText(), selectedEvent, cbxActivities.getValue(), Session.getInstance().getUser().getRole(Host.class)));
		
		loadSchedules(selectedEvent);
		displayPnlSchedule(false);
	}
	
	private boolean isValid() {
		StringBuilder msg = new StringBuilder();
		
		if(txtTimeStart.getText().equals("")) {
			msg.append("Time start is empty");
		}
		
		if(txtTimeEnd.getText().equals("")) {
			if(msg.length() > 0) {
				msg.append("\r\n");
			}
			
			msg.append("Time end is empty");
		}
		
		if(selectedEvent == null) {
			if(msg.length() > 0) {
				msg.append("\r\n");
			}
			
			msg.append("No event selected");
		}
		
		if(cbxActivities.getValue() == null) {
			if(msg.length() > 0) {
				msg.append("\r\n");
			}
			
			msg.append("No activity selected");
		}
		
		if(
			(Session.getInstance().getUser() == null) ||
			(!Session.getInstance().getUser().hasRole(Host.class))
		) {
			if(msg.length() > 0) {
				msg.append("\r\n");
			}
			
			msg.append("Current user has no permission to create schedule");
		}
		
		if(msg.length() > 0) {
			Window.error("Validation", msg.toString());
			return false;
		}else {
			return true;
		}
	}
	
	@FXML protected void handleCancelAction(ActionEvent event) {
		displayPnlSchedule(false);
	}

	@FXML protected void onTimeKeyReleased(KeyEvent event) {
		WindowUtils.validateTime(txtTimeStart);
		WindowUtils.validateTime(txtTimeEnd);
	}
}
