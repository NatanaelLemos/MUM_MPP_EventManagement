package edu.mum.eventmanagement.models;

import java.time.Period;
import java.util.*;
import javax.persistence.*;

@Entity
public class Schedule {
	@Id
	@GeneratedValue
	private int id;
	private Period timeStart;
	private Period timeEnd;
	
	@OneToMany
	private List<Activity> activities;
	
	@ManyToOne
	private Event event;
	
	public Schedule() {
		activities = new ArrayList<Activity>();
	}
}
