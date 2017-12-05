package edu.mum.eventmanagement.models;

import java.time.Period;
import java.util.*;

public class Schedule {
	private int id;
	private Period timeStart;
	private Period timeEnd;
	
	private List<Activity> activities;
	
	private int eventId;
	private Event event;
	
	public Schedule() {
		activities = new ArrayList<Activity>();
	}
}
