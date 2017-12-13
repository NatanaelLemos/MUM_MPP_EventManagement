package edu.mum.eventmanagement.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String dateStr = df.format(date);
		
		for(Event event : location.getEvents()) {
			String evDateStr = df.format(event.getDate());
			if(dateStr.equals(evDateStr)) {
				return true;
			}
		}
		
		return  false;
	}
	
	public List<Event> getExpiredDueDate(){
		return ((EventRepository)getRepository()).getExpiredDueDate();
	}
}