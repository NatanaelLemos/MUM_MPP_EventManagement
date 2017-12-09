package edu.mum.eventmanagement.models;

import java.time.Period;
import java.util.*;
import javax.persistence.*;

@Entity
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Period timeStart;
	public Period getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Period timeStart) {
		this.timeStart = timeStart;
	}

	private Period timeEnd;
	
	public Period getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Period timeEnd) {
		this.timeEnd = timeEnd;
	}

	@OneToMany
	private List<Activity> activities;
	
	@ManyToOne
	private Event event;
	
	public Schedule() {
		activities = new ArrayList<Activity>();
	}
	
	public Schedule(Period timeStart, Period timeEnd) {
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
	}
}
