package edu.mum.eventmanagement.models;

import java.util.*;

public class User {
	private int id;
	
	private String username;
	private String email;
	private String password;
	
	private List<UserRole> roles;
	
	private int countryId;
	private Country country;
	
	public User() {
		roles = new ArrayList<UserRole>();
	}
}
