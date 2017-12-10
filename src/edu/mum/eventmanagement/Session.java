package edu.mum.eventmanagement;

import java.util.ArrayList;
import java.util.List;

import edu.mum.eventmanagement.models.User;

public class Session {
	private static Session instance;
	
	private Session() {
		
	}
	
	public static Session getInstance() {
		
		if(instance == null) {
			synchronized(Session.class) { //Double check if the instance is null (thread safe)
				if(instance == null) {
					instance = new Session();
				}
			}
		}
		
		return instance;
	}
	
	private User currentUser;
	private List<ISessionObserver> observers = new ArrayList<ISessionObserver>();
	
	public User getUser() {
		return currentUser;
	}
	
	public void setUser(User user) {
		currentUser = user;
		
		for(ISessionObserver ob : observers) {
			ob.onUserChange();
		}
	}
	
	public void subscribe(ISessionObserver observer) {
		observers.add(observer);
	}
}
