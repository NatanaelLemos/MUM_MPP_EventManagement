package edu.mum.eventmanagement.models;

import javax.persistence.*;

@Entity
public class Gift {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 255)
	private String description;
}
