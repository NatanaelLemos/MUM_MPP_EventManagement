package edu.mum.eventmanagement.models;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(length = 255)
	private String username;
	
	@Column(length = 255, unique = true)
	private String email;

	@Column(length = 255)
	private String password;
	
	@OneToMany
	private List<UserRole> roles;
	
	@ManyToOne
	private Country country;
	
	public User() {
		roles = new ArrayList<UserRole>();
	}
}
