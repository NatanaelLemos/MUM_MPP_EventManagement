package edu.mum.eventmanagement.controllers;

import java.util.List;

import edu.mum.eventmanagement.models.*;
import edu.mum.eventmanagement.repositories.EventRepository;
import edu.mum.eventmanagement.repositories.IRepository;

public class ScheduleController extends ControllerBase<Schedule> {

	private IRepository<Event> eventRepository = IRepository.getRepository("Event");
	private IRepository<Activity> activityRepository = IRepository.getRepository("Activity");
	private IRepository<Gift> giftRepository = IRepository.getRepository("Gift");
	
	@Override
	public void create(Schedule schedule) {
		schedule.getEvent().addSchedule(schedule);
		schedule.getActivity().addSchedule(schedule);
		schedule.getHost().addSchedule(schedule);

		super.create(schedule);
		eventRepository.update(schedule.getEvent());
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
	
	public List<Event> getEventsWithoutPrize(){
		return ((EventRepository)eventRepository).getEventsWithoutPrize();
	}
	
	public List<Activity> getActivities(){
		return activityRepository.getAll();
	}
	
	public List<Gift> getGifts(){
		return giftRepository.getAll();
	}
	
	public void updateEvent(Event event) {
		eventRepository.update(event);
	}
}
