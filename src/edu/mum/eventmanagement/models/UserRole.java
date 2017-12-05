package edu.mum.eventmanagement.models;
import javax.persistence.*;

@Entity
public abstract class UserRole {
	@Id
	@GeneratedValue
	private int id;
}
