package edu.mum.eventmanagement.models;

import java.util.*;
import javax.persistence.*;

import edu.mum.eventmanagement.services.CryptographyService;

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
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
	private Country country;
	
	@OneToMany(
        mappedBy = "user", 
	    cascade = CascadeType.ALL, 
	    orphanRemoval = true
	)
	private List<UserRole> roles;
	
	User() {
		roles = new ArrayList<UserRole>();
	}
	
	public User(String username, String email, String password, Country country) {
		this();
		this.username = username;
		this.email = email;
		this.password = CryptographyService.encrypt(password);
		this.country = country;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void addRole(UserRole role) {
		if(roles == null) {
			roles = new ArrayList<UserRole>();
		}
		
		role.setUser(this);
		roles.add(role);
	}
	
	public List<UserRole> getRoles(){
		if(this.roles == null) {
			return new ArrayList<UserRole>();
		}
		
		return this.roles;
	}
	
	public Country getCountry() {
		return this.country;
	}

	public <TType> boolean hasRole(Class<TType> classType) {
		for(UserRole role : getRoles()) {
			if(role.getClass().equals(classType)) {
				return true;
			}
		}
		
		return false;
	}
}
