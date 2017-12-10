package edu.mum.eventmanagement.models;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Location {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length =  255)
	private String name;
	
	@Column(length = 255)
	private String address;
	
    @OneToMany(
        mappedBy = "location", 
        cascade = CascadeType.ALL, 
        orphanRemoval = true
    )
	private List<Event> events;
	
	public String getName() {
		return this.name;
	}
	
	public void addEvent(Event event) {
		if(events == null) {
			events = new ArrayList<Event>();
		}
		
		events.add(event);
	}
	
	public List<Event> getEvents(){
		if(this.events == null) { //Avoid null check in controllers
			return new ArrayList<Event>();
		}
		
		return this.events;
	}
	
	Location(){
	}
	
	public Location(String name, String address) {
		this.name = name;
		this.address = address;
	}
}
