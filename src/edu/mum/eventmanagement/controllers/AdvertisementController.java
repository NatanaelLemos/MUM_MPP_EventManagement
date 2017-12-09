package edu.mum.eventmanagement.controllers;

import java.util.List;

import edu.mum.eventmanagement.models.*;
import edu.mum.eventmanagement.repositories.*;

public class AdvertisementController extends ControllerBase<Advertisement> {
	
	private EventRepository eventRepository = new EventRepository();
	
	public AdvertisementController() {
		super(new AdvertisementRepository());
	}
	
	public List<Event> getEvents(){
		return eventRepository.getAll();
	}
	
	@Override
	public void create(Advertisement ad) {
		ad.getEvent().addAdvertisement(ad);
		super.create(ad);
	}
}