package edu.mum.eventmanagement.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import edu.mum.eventmanagement.models.Event;

public class EventRepository implements IRepository<Event>{

	EntityManager entityManager = HibernateUtil.getEntityManager();
	
	@Override
	public List<Event> getAll() {
		return (List<Event>)entityManager.createQuery("SELECT e from Event e", Event.class).getResultList();
	}

	@Override
	public void add(Event entity) {
		// TODO Auto-generated method stub
		
	}
	
}
