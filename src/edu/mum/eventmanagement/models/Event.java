package edu.mum.eventmanagement.models;

import java.util.Date;

import javax.persistence.*;

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
	
	Event(){
		//This constructor is used only because Hibernate need an empty constructor to build the objects
	}
	
	public Event(String name, Date date, Date dueDate) {
		this.name = name;
		this.date = date;
		this.dueDate = dueDate;
		this.state = EventState.pending;
	}
}
