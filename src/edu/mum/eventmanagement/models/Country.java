package edu.mum.eventmanagement.models;

import java.util.List;

import javax.persistence.*;

@Entity
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 255)
	private String name;
	
    @OneToMany(
        mappedBy = "country", 
        cascade = CascadeType.ALL, 
        orphanRemoval = true
    )
	private List<User> users;
    
    public String getName() {
    	return name;
    }
}
