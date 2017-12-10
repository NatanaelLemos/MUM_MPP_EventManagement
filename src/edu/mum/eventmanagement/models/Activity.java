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
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@OneToMany
	private List<Schedule> schedules;
	
	public Activity() {
		schedules = new ArrayList<Schedule>();
	}
}
