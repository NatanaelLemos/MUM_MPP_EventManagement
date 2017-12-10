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
	
	private ScheduleState state;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id")
	private Activity activity;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
	private Event event;
	
	Schedule() {
	}
	
	public Schedule(String timeStart, String timeEnd, Event event) {
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.event = event;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getTimeStart() {
		return this.timeStart;
	}
	
	public String getTimeEnd() {
		return this.timeEnd;
	}
}
