package edu.mum.eventmanagement.models;

import javax.persistence.*;

@Entity
public class Advertisement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private AdvertisementType type;
}