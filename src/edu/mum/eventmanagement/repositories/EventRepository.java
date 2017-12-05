package edu.mum.eventmanagement.repositories;

import java.util.List;

import edu.mum.eventmanagement.models.Event;

public class EventRepository implements IRepository<Event>{

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getAll() {
		return (List<Event>)HibernateUtil.getEntityManager().createQuery("SELECT * FROM Event").getResultList();
	}
	
}
