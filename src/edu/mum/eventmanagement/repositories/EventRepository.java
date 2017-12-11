package edu.mum.eventmanagement.repositories;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import edu.mum.eventmanagement.models.Event;
import edu.mum.eventmanagement.models.EventState;
import edu.mum.eventmanagement.models.Location;

public class EventRepository extends RepositoryBase<Event> implements IRepository<Event> {

	public EventRepository() {
		super(Event.class);
	}	

	public List<Event> getExpiredDueDate(){
		EntityManager em = HibernateUtil.getEntityManager();
		
		TypedQuery<Event> query = em.createQuery("SELECT e FROM Event e WHERE e.state = :state and e.dueDate < :duedate", Event.class);
		query.setParameter("state", EventState.pending);
		query.setParameter("duedate", new Date());
		
		return query.getResultList();
	}
	
	public List<Event> getEventsWithoutPrize(){
		List<Event> events = getAll();
		
		return events.stream()
				.filter(e -> e.getGift() == null)
				.collect(Collectors.toList());
	}
}
