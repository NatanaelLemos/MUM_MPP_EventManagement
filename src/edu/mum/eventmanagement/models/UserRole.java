package edu.mum.eventmanagement.models;
import javax.persistence.*;

@Entity
public abstract class UserRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
}
