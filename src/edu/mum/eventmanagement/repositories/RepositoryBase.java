package edu.mum.eventmanagement.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RepositoryBase<TEntity>  implements IRepository<TEntity> {
	private Class<TEntity> type;
	
	RepositoryBase(Class<TEntity> type){
		this.type = type;
	}
	
	@Override
	public TEntity get(int id) {
		EntityManager entityManager = HibernateUtil.getEntityManager();
		return entityManager.find(type, id);	
	}
	
	@Override
	public List<TEntity> getAll() {
		EntityManager entityManager = HibernateUtil.getEntityManager();
		
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TEntity> cq = cb.createQuery(type);
        Root<TEntity> rootEntry = cq.from(type);
        CriteriaQuery<TEntity> all = cq.select(rootEntry);
        TypedQuery<TEntity> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
	}
	
	@Override
	public void add(TEntity entity) {
		EntityManager entityManager = HibernateUtil.getEntityManager();
	    entityManager.getTransaction().begin();
	    entityManager.persist(entity);
	    entityManager.getTransaction().commit();
	    entityManager.close();
	}


	
	@Override
	public void delete(TEntity entity) {
		EntityManager entityManager = HibernateUtil.getEntityManager();
		
		if(!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}
		
	    entityManager.remove(entity);
	    entityManager.getTransaction().commit();
	    entityManager.close();
	}

}
