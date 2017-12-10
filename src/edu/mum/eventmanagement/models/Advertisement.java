package edu.mum.eventmanagement.models;

import javax.persistence.*;

@Entity
public class Advertisement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//can't use field named type
	private AdvertisementType adType;
	
	private String imgLocation;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
	private Event event;
	
	Advertisement(){
		
	}
	
	public Advertisement(AdvertisementType type, String imgLocation, Event event) {
		this.adType = type;
		this.imgLocation = imgLocation;
		this.event = event;
	}
	
	public int getId() {
		return id;
	}
	
	public AdvertisementType getType() {
		return this.adType;
	}
	
	public String getImgLocation() {
		return this.imgLocation;
	}
	
	public Event getEvent() {
		return this.event;
	}
}