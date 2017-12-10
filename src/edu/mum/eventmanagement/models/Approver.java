package edu.mum.eventmanagement.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Approver extends UserRole {
	
	@OneToMany(         
        cascade = CascadeType.ALL, 
        orphanRemoval = true
    )
	List<Event> events; 
	public Approver() {		
	}
	
	public void approveEvent() {
		
	}
}
