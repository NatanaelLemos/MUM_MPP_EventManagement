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
	
	@OneToMany
	private List<Schedule> schedules;
	
	public Activity() {
		schedules = new ArrayList<Schedule>();
	}
}
