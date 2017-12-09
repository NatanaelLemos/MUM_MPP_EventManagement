package edu.mum.eventmanagement.models;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length=50)
	private String name;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Temporal(TemporalType.DATE)
	private Date dueDate;
	
	private EventState state;
	
	@ManyToOne
	private Location location;
	
	public Date getDate() {
		return this.date;
	}
	
	public Location getLocation() {
		return this.location;
	}
	
	Event(){
		//This constructor is used only because Hibernate need an empty constructor to build the objects
	}
	
	public Event(String name, Date date, Date dueDate, Location location) {
		this.name = name;
		this.date = date;
		this.dueDate = dueDate;
		this.location = location;
		this.state = EventState.pending;
	}
}
