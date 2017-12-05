package edu.mum.eventmanagement.models;
import javax.persistence.*;

@Entity
public class Ingredient {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(length=255)
	private String name;
}