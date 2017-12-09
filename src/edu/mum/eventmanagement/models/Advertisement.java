package edu.mum.eventmanagement.models;

import javax.persistence.*;

@Entity
public class Advertisement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private AdvertisementType type;
	
	private String imgLocation;
	
	private Event event;
	
	Advertisement(){
		
	}
	
	public Advertisement(AdvertisementType type, String imgLocation, Event event) {
		this.type = type;
		this.imgLocation = imgLocation;
		this.event = event;
	}
	
	public int getId() {
		return id;
	}
	
	public AdvertisementType getType() {
		return this.type;
	}
	
	public String getImgLocation() {
		return this.imgLocation;
	}
	
	public Event getEvent() {
		return this.event;
	}
}