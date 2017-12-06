package edu.mum.eventmanagement.models;

import javax.persistence.*;

@Entity
public class Event {
	@Id
	@GeneratedValue
	@Column()
	private int id;
}
