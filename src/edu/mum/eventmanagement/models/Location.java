package edu.mum.eventmanagement.models;
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
}
