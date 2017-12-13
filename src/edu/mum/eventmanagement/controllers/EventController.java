package edu.mum.eventmanagement.controllers;

import java.util.*;
import edu.mum.eventmanagement.models.*;
import edu.mum.eventmanagement.repositories.IRepository;
import edu.mum.eventmanagement.repositories.EventRepository;

public class EventController extends ControllerBase<Event> {
	private IRepository<Location> locationRepository = IRepository.getRepository("Location");

	public List<Location> getLocations() {
		return locationRepository.getAll();
	}
	
	@Override
	public void create(Event event) {
		event.getLocation().addEvent(event);
		super.create(event);
	}
	
	public boolean hasEventInSameDate(Location location, Date date) {
		for(Event event : location.getEvents()) {
			if(event.getDate().equals(date)) {
				return true;
			}
		}
		
		return  false;
	}
	
	public List<Event> getExpiredDueDate(){
		return ((EventRepository)getRepository()).getExpiredDueDate();
	}
}