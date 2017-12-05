package edu.mum.eventmanagement.models;

import java.util.*;

public abstract class Activity {
	private int id;
	private String name;
	
	private List<Schedule> schedules;
	
	public Activity() {
		schedules = new ArrayList<Schedule>();
	}
}
