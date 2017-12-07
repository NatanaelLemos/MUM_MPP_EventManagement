package edu.mum.eventmanagement.repositories;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import edu.mum.eventmanagement.models.Location;

public class LocationRepository implements IRepository<Location>{

	EntityManager entityManager = HibernateUtil.getEntityManager();
	
	@Override
	public List<Location> getAll() {
		TypedQuery<Location> query = entityManager.createQuery("SELECT l from Location l", Location.class);
		return query.getResultList();
	}

}