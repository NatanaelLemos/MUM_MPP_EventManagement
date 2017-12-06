package edu.mum.eventmanagement.models;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class Approver extends UserRole {
	
	List<Event> events; 
	public Approver() {
		
	}
	
	public void approveEvent() {
		
	}
}
