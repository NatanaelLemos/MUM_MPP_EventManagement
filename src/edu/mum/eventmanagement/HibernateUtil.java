package edu.mum.eventmanagement;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//import org.hibernate.SessionFactory;
//import org.hibernate.boot.Metadata;
//import org.hibernate.boot.MetadataSources;
//import org.hibernate.boot.registry.StandardServiceRegistry;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.cfg.Configuration;

public class HibernateUtil {
//	  private static SessionFactory sessionFactory;
//
//	  public static SessionFactory getSessionFactory() {
//		  if(sessionFactory == null){
//			  synchronized (HibernateUtil.class) { //Double check if the sessionFactory is null (thread safe)
//				  if(sessionFactory == null){
//					  Configuration configuration = new Configuration()
//							  							.configure("/META-INF/hibernate.cfg.xml");
//					  sessionFactory = configuration
//							  			.buildSessionFactory();
//				  }
//			  }
//		  }
//		  
//		  return sessionFactory;
//	  }
	  
	  public static void updateDatabase() {
		  
		    EntityManagerFactory factory = Persistence.createEntityManagerFactory("Database"); //name of persistence unit here
		    EntityManager entityManager = factory.createEntityManager();
	  }
}
