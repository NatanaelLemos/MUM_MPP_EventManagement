package edu.mum.eventmanagement.models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import edu.mum.eventmanagement.repositories.EventRepository;

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
	List<Event> events;
	
	
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
	
	public String getName(){
		return this.name;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public Date getDueDate() {
		return this.dueDate;
	}
	
	public EventState getState() {
		return this.state;
	}
	
	public List<Event> getListEvent(){
		EventRepository er = new EventRepository();
		events = er.getAll();
		return events;
	}
}
