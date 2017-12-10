package edu.mum.eventmanagement.models;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 255)
	private String username;
	
	@Column(length = 255, unique = true)
	private String email;

	@Column(length = 255)
	private String password;
	
	@OneToMany(
        mappedBy = "user", 
	    cascade = CascadeType.ALL, 
	    orphanRemoval = true
	)
	private List<UserRole> roles;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
	private Country country;
	
	public User() {
		roles = new ArrayList<UserRole>();
	}
	
	public String getEmail() {
		return this.email;
	}
}
