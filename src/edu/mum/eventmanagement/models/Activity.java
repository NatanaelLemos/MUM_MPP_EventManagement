package edu.mum.eventmanagement.models;

import java.util.*;
import javax.persistence.*;

@Entity
public abstract class Activity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length=255)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany
	private List<Schedule> schedules;
	
	public Activity() {
		schedules = new ArrayList<Schedule>();
	}
	
	public String getDescription() {
		return getName() + " (" + getClass().getSimpleName() + ")";
	}
	
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
