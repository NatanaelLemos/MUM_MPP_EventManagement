package edu.mum.eventmanagement.models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import edu.mum.eventmanagement.repositories.EventRepository;

@Entity
public class Event {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(length=50)
	private String name;
	
	private Date date;
	private Date dueDate;
	
	private EventState state;
	List<Event> events;
	
	
	Event(){
		//This constructor is used only because Hibernate need an empty constructor to build the objects
	}
	
	public Event(String name, Date date, Date dueDate) {
		this.name = name;
		this.date = date;
		this.dueDate = dueDate;
		this.state = EventState.pending;
	}
	
	public List<Event> getListEvent(){
		EventRepository er = new EventRepository();
		events = er.getAll();
		return events;
	}
}
