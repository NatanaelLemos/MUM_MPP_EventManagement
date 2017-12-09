package edu.mum.eventmanagement.repositories;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class HibernateUtil {
	@PersistenceContext
    private static EntityManager entityManager; 
	
	public static EntityManager getEntityManager() {
		if(needNewInstance()){
		  synchronized (HibernateUtil.class) { //Double check if the sessionFactory is null (thread safe)
			  if(needNewInstance()){
				EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "MUM_MPP_EventManagement" );
				entityManager = emfactory.createEntityManager();
			  }
		  }
		}
		
		return entityManager;
	}
	
	private static boolean needNewInstance() {
		return  entityManager == null || (!entityManager.isOpen());
	}
}