package edu.mum.eventmanagement.models;
import java.util.ArrayList;
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

	public void addSchedule(Schedule schedule) {
		if(schedules == null) {
			schedules = new ArrayList<Schedule>();
		}
		
		schedules.add(schedule);
	}
	
	public List<Schedule> getSchedules(){
		if(schedules == null) {
			return new ArrayList<Schedule>();
		}
		return this.schedules;
	}
}
