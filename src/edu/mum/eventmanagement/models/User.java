package edu.mum.eventmanagement.models;

import java.util.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
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
	
	private List<UserRole> roles;
	
	private int countryId;
	private Country country;
	
	public User() {
		roles = new ArrayList<UserRole>();
	}
}
