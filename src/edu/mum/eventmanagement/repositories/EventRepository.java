package edu.mum.eventmanagement.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import edu.mum.eventmanagement.models.Event;
import edu.mum.eventmanagement.models.Location;

public class EventRepository extends RepositoryBase<Event> implements IRepository<Event> {

	public EventRepository() {
		super(Event.class);
	}	
}
