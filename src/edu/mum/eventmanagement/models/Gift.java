package edu.mum.eventmanagement.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Gift {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 255)
	private String description;
	
    @OneToMany(
        mappedBy = "gift", 
        cascade = CascadeType.ALL, 
        orphanRemoval = true
    )
	private List<Event> events;
    
    public List<Event> getEvents(){
    	if(events == null) {
    		return new ArrayList<Event>();
    	}
    	
    	return this.events;
    }
    
    public void addEvent(Event event) {
    	if(events == null) {
    		events = new ArrayList<Event>();
    	}
    	
    	events.add(event);
    }
    
    public String getDescription() {
    	return description;
    }
    
    Gift(){
    	
    }
    
    public Gift(String description) {
    	this.description = description;
    }
}
