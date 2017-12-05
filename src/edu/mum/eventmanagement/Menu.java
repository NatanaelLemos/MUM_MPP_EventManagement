package edu.mum.eventmanagement;

import org.hibernate.Session;
import javafx.application.Application;

public class Menu extends WindowBase {

	public Menu() {
		super("Menu", "Event Management System", 300, 200, true);
	}
	
	public static void main(String[] args) {
		//Application.launch(Menu.class, args);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();

	    // Check database version
	    String sql = "select version()";

	    String result = (String) session.createNativeQuery(sql).getSingleResult();
	    System.out.println(result);

	    session.getTransaction().commit();
	    session.close();

	    
	    HibernateUtil.shutdown();
	}
}
