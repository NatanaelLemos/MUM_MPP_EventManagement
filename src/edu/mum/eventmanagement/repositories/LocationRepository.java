package edu.mum.eventmanagement.repositories;

import java.util.List;
import javax.persistence.EntityManager;
import edu.mum.eventmanagement.models.Location;

public class LocationRepository implements IRepository<Location>{

	EntityManager entityManager = HibernateUtil.getEntityManager();
	
	@Override
	public List<Location> getAll() {
		return entityManager.createQuery("SELECT l from Location", Location.class).getResultList();
	}

}


/*
Database: mpp1
Username: mpp1
Email: s2412010@mvrht.net
 */