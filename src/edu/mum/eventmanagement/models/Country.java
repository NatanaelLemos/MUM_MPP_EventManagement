package edu.mum.eventmanagement.models;

import javax.persistence.*;

@Entity
public class Country {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(length = 255)
	private String name;
}
