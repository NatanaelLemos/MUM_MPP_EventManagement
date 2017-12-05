package edu.mum.eventmanagement.models;

import javax.persistence.*;

@Entity
public class Advertisement {
	
	@Id
	@GeneratedValue
	private int id;
	
	private AdvertisementType type;
}