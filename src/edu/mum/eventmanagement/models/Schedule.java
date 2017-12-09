package edu.mum.eventmanagement.models;

import java.util.*;
import javax.persistence.*;

@Entity
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length=8)
	private String timeStart;

	@Column(length=8)
	private String timeEnd;
	
	@ManyToOne
	private Activity activity;
	
	@ManyToOne
	private Event event;
	
	Schedule() {
	}
	
	public Schedule(String timeStart, String timeEnd, Event event) {
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.event = event;
	}
}
