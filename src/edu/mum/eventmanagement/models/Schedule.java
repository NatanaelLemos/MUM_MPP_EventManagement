package edu.mum.eventmanagement.models;

import java.util.*;
import javax.persistence.*;

@Entity
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String timeStart;
	private String timeEnd;
	
	private ScheduleState state;
	
	@ManyToOne
	private Activity activity;
	
	@ManyToOne
	private Event event;
	
	public Event getEvent() {
		return event;
	}

	Schedule() {
	}
	
	public Schedule(String timeStart, String timeEnd, Event event) {
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.event = event;
	}
	
	
}
