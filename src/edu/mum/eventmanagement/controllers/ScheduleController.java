package edu.mum.eventmanagement.controllers;

import java.util.List;

import edu.mum.eventmanagement.models.*;
import edu.mum.eventmanagement.repositories.ActivityRepository;
import edu.mum.eventmanagement.repositories.EventRepository;
import edu.mum.eventmanagement.repositories.GiftRepository;
import edu.mum.eventmanagement.repositories.ScheduleRepository;

public class ScheduleController extends ControllerBase<Schedule> {

	private EventRepository eventRepository = new EventRepository();
	private ActivityRepository activityRepository = new ActivityRepository();
	private GiftRepository giftRepository = new GiftRepository();
	
	public ScheduleController() {
		super(new ScheduleRepository());
	}

	@Override
	public void create(Schedule schedule) {
		schedule.getEvent().addSchedule(schedule);
		schedule.getActivity().addSchedule(schedule);
		schedule.getHost().addSchedule(schedule);

		super.create(schedule);
	}
	
	@Override
	public void delete(Schedule entity) {
		entity.getEvent().getSchedules().remove(entity);
		entity.getActivity().getSchedules().remove(entity);
		entity.getHost().getSchedules().remove(entity);
		super.delete(entity);
	}
	
	public List<Event> getEvents(){
		return eventRepository.getAll();
	}
	
	public List<Activity> getActivities(){
		return activityRepository.getAll();
	}
	
	public List<Gift> getGifts(){
		return giftRepository.getAll();
	}
}
