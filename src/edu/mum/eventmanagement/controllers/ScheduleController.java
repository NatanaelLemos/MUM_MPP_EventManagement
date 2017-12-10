package edu.mum.eventmanagement.controllers;

import java.util.List;

import edu.mum.eventmanagement.models.*;
import edu.mum.eventmanagement.repositories.ActivityRepository;
import edu.mum.eventmanagement.repositories.EventRepository;
import edu.mum.eventmanagement.repositories.ScheduleRepository;

public class ScheduleController extends ControllerBase<Schedule> {

	private EventRepository eventRepository = new EventRepository();
	private ActivityRepository activityRepository = new ActivityRepository();
	
	public ScheduleController() {
		super(new ScheduleRepository());
	}
	
	public List<Event> getEvents(){
		return eventRepository.getAll();
	}
	
	public List<Activity> getActivities(){
		return activityRepository.getAll();
	}
}
