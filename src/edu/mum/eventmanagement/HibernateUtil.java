package edu.mum.eventmanagement;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public class HibernateUtil {
    private static HibernateUtil instance;
	
	@PersistenceUnit
    private EntityManagerFactory emf;
	private EntityManager entityManager;
	
	private HibernateUtil() {
		entityManager = emf.createEntityManager();
	}
	
	public static EntityManager getEntityManager() {
		if(instance == null){
		  synchronized (HibernateUtil.class) { //Double check if the sessionFactory is null (thread safe)
			  if(instance == null){
				  instance = new HibernateUtil();
			  }
		  }
		}
		
		return instance.entityManager;
	}
}