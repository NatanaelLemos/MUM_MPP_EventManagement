package edu.mum.eventmanagement.models;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Host extends UserRole {
	
	@OneToMany(
	        mappedBy = "host", 
	        cascade = CascadeType.ALL, 
	        orphanRemoval = true
	    )
	private List<Schedule> schedules;
	
}
