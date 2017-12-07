package edu.mum.eventmanagement.controllers;

import java.util.*;
import edu.mum.eventmanagement.models.*;
import edu.mum.eventmanagement.repositories.EventRepository;
import edu.mum.eventmanagement.repositories.LocationRepository;

public class EventController {
	private EventRepository eventRepository = new EventRepository();
	private LocationRepository locationRepository = new LocationRepository();

	public List<Location> getLocations() {
		return locationRepository.getAll();
	}
}
