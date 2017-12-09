package edu.mum.eventmanagement.controllers;

import java.util.*;
import edu.mum.eventmanagement.models.*;
import edu.mum.eventmanagement.repositories.EventRepository;
import edu.mum.eventmanagement.repositories.LocationRepository;

public class EventController extends ControllerBase<Event> {
	private LocationRepository locationRepository = new LocationRepository();

	public EventController() {
		super(new EventRepository());
	}
	
	public List<Location> getLocations() {
		return locationRepository.getAll();
	}
}