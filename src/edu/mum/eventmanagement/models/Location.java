package edu.mum.eventmanagement.models;
import java.util.List;

import javax.persistence.*;

@Entity
public class Location {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(length =  255)
	private String name;
	
	@Column(length = 255)
	private String address;
	
	@OneToMany
	private List<Event> events;
}
