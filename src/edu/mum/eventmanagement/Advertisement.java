package edu.mum.eventmanagement;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import edu.mum.eventmanagement.controllers.AdvertisementController;
import edu.mum.eventmanagement.models.AdvertisementType;
import edu.mum.eventmanagement.models.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class Advertisement {
	private AdvertisementController ctrl = new AdvertisementController();
	private Event selectedEvent;
	private edu.mum.eventmanagement.models.Advertisement selectedAd;
	
	@FXML protected TableView<Event> tblEvents;
	@FXML protected TableColumn<Event, String> colEventName;
	@FXML protected TableColumn<Event, String> colEventDate;
	@FXML protected TableColumn<Event, String> colEventDueDate;
	@FXML protected TableColumn<Event, String> colEventLocation;
	
	@FXML protected TableView<edu.mum.eventmanagement.models.Advertisement> tblAdvertisements;
	@FXML protected TableColumn<edu.mum.eventmanagement.models.Advertisement, String> colAdType;
	@FXML protected TableColumn<edu.mum.eventmanagement.models.Advertisement, String> colAdImgLocation;
	
	@FXML protected Pane pnlAdvertisement;
	@FXML protected Button btnNewAdvertisement;
	@FXML protected Button btnRemoveAdvertisement;
	
	@FXML protected TextField txtImageLocation;
	@FXML protected ComboBox<AdvertisementType> cbxType;
	
	@FXML public void initialize() {
		displayTblAdvertisements(false);
		displayPnlAdvertisement(false);
		
		loadCbxTypes();
		loadEvents();
	}
	
	private void displayTblAdvertisements(boolean isVisible) {
		tblAdvertisements.setVisible(isVisible);
		btnNewAdvertisement.setVisible(isVisible);
		btnRemoveAdvertisement.setVisible(isVisible);
	}
	
	private void displayPnlAdvertisement(boolean isVisible) {
		txtImageLocation.setText("");
		cbxType.setValue(null);
		pnlAdvertisement.setVisible(isVisible);
	}
	
	private void loadCbxTypes() {
		List<AdvertisementType> adTypes = Arrays.asList(AdvertisementType.values());
		WindowUtils.loadCombobox(cbxType, adTypes, i -> i.name());
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
		    loadAdvertisements(newSelection);
		});
	}
	
	private void loadAdvertisements(Event event) {
		colAdType.setCellValueFactory(new PropertyValueFactory<edu.mum.eventmanagement.models.Advertisement, String>("type"));
		colAdImgLocation.setCellValueFactory(new PropertyValueFactory<edu.mum.eventmanagement.models.Advertisement, String>("imgLocation"));
		
		List<edu.mum.eventmanagement.models.Advertisement> ads = event.getAdvertisements();
		tblAdvertisements.getItems().setAll(ads);

		tblAdvertisements.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    selectedAd = newSelection;
		});
		
		displayTblAdvertisements(true);
	}
		
	@FXML protected void handleNewAdvertisement(ActionEvent event) {
		displayPnlAdvertisement(true);
	}
	
	@FXML protected void handleDeleteAdvertisement(ActionEvent event) {
		if(selectedAd == null) return;
		
		Window.confirm("Sure?", "Do you really want to delete this advertisement?", () -> {
			ctrl.delete(selectedAd);
			loadAdvertisements(selectedEvent);
		}, null);
	}
	
	@FXML protected void handleSearchImageAction(ActionEvent event) {
		File file = Window.chooseFile("Select image file");
		txtImageLocation.setText(file.getAbsolutePath());
	}
	
	@FXML protected void handleSaveAction(ActionEvent event) {
		if(!isValid()) {
			return;
		}
		
		ctrl.create(new edu.mum.eventmanagement.models.Advertisement(cbxType.getValue(), txtImageLocation.getText(), selectedEvent));
		
		loadAdvertisements(selectedEvent);
		displayPnlAdvertisement(false);
	}
	
	private boolean isValid() {
		StringBuilder msg = new StringBuilder();
		
		if(txtImageLocation.getText().equals("")) {
			msg.append("Image location is empty");
		}
		
		if(cbxType.getValue().equals(null)) {
			if(msg.length() > 0) {
				msg.append("\r\n");
			}
			
			msg.append("Advertisement type is not defined");
		}
		
		if(selectedEvent == null) {
			if(msg.length() > 0) {
				msg.append("\r\n");
			}
			
			msg.append("No event selected");
		}
		
		if(msg.length() > 0) {
			Window.error("Validation", msg.toString());
			return false;
		}else {
			return true;
		}
	}
	
	@FXML protected void handleCancelAction(ActionEvent event) {
		displayPnlAdvertisement(false);
	}
}
