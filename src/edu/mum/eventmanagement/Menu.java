package edu.mum.eventmanagement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import edu.mum.eventmanagement.models.User;

public class Menu extends WindowBase {

	public Menu() {
		super("Menu", "Event Management System", 300, 200, true);
	}
	
	public static void main(String[] args) {
//Application.launch(Menu.class, args);
	
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "MUM_MPP_EventManagement" );
		EntityManager entitymanager = emfactory.createEntityManager();

		//Scalar function
		Query query = entitymanager.createQuery("Select count(u) from User u");
		System.out.println( Integer.toString(query.getFirstResult()));
	}
}
