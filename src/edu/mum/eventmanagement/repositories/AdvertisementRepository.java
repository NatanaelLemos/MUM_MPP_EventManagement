package edu.mum.eventmanagement.repositories;

import javax.persistence.EntityManager;
import edu.mum.eventmanagement.models.*;

public class AdvertisementRepository extends RepositoryBase<Advertisement> implements IRepository<Advertisement> {

	public AdvertisementRepository() {
		super(Advertisement.class);
	}	
	
	@Override
	public void delete(Advertisement entity) {
		EntityManager entityManager = HibernateUtil.getEntityManager();

		entityManager.getTransaction().begin();
		entity.getEvent().getAdvertisements().remove(entity);
	    entityManager.getTransaction().commit();
	    entityManager.close();
	}
}
