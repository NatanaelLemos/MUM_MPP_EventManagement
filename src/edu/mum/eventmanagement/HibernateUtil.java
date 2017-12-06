package edu.mum.eventmanagement;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

public class HibernateUtil {
    private static EntityManager entityManager; 
	
	public static EntityManager getEntityManager() {
		if(entityManager == null){
		  synchronized (HibernateUtil.class) { //Double check if the sessionFactory is null (thread safe)
			  if(entityManager == null){
				EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "MUM_MPP_EventManagement" );
				entityManager = emfactory.createEntityManager();
			  }
		  }
		}
		
		return entityManager;
	}

//	//Scalar function
//	Query query = entitymanager.createQuery("Select count(u) from User u");
//	System.out.println( Integer.toString(query.getFirstResult()));
}