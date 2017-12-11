package edu.mum.eventmanagement.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import edu.mum.eventmanagement.repositories.EventRepository;

@Entity
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length=50)
	private String name;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Temporal(TemporalType.DATE)
	private Date dueDate;
	
	private EventState state;	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
	private Location location;
	
    @OneToMany(
        mappedBy = "event", 
        cascade = CascadeType.ALL, 
        orphanRemoval = true
    )
	private List<Advertisement> advertisements;
	
    @OneToMany(
        mappedBy = "event", 
        cascade = CascadeType.ALL, 
        orphanRemoval = true
    )
	private List<Schedule> schedules;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gift_id")
    private Gift gift;
	
	public Date getDate() {
		return this.date;
	}
	
	public Location getLocation() {
		return this.location;
	}
	
	public String getLocationName() {
		Location location = getLocation();
		if(location == null) return "";
		return location.getName();
	}
	
	public List<Advertisement> getAdvertisements(){
		if(advertisements == null) {
			return new ArrayList<Advertisement>();
		}
		
		return this.advertisements;
	}
	
	public void addAdvertisement(Advertisement advertisement) {
		if(advertisements == null) {
			advertisements = new ArrayList<Advertisement>();
		}
		
		advertisements.add(advertisement);
	}
	
	public List<Schedule> getSchedules(){
		if(schedules == null) {
			return new ArrayList<Schedule>();
		}
		return this.schedules;
	}
	
	public void addSchedule(Schedule schedule) {
		if(schedules == null) {
			schedules = new ArrayList<Schedule>();
		}
		
		schedules.add(schedule);
	}
	
	public Gift getGift() {
		return this.gift;
	}
	
	public void setGift(Gift gift) {
		this.gift = gift;
	}
	
	Event(){
		//This constructor is used only because Hibernate need an empty constructor to build the objects
	}
	
	public Event(String name, Date date, Date dueDate, Location location) {
		this.name = name;
		this.date = date;
		this.dueDate = dueDate;
		this.location = location;
		this.state = EventState.pending;
	}
	
	public String getName(){
		return this.name;
	}
	
	public Date getDueDate() {
		return this.dueDate;
	}
	
	public EventState getState() {
		return this.state;
	}
	
	public List<Event> getListEvent(){
		EventRepository er = new EventRepository();
		//DON'T STORE VARIABLES INSIDE THE MODELS, OTHERWISE THE JPA WILL CREATE A COLUMN FOR IT IN THE DATABASE
		//events = er.getAll();
		return er.getAll();
	}

	public void setState(EventState eventState) {
		this.state = eventState;
	}
	
	public List<Guest> getVotes(){
		List<Guest> guests = new ArrayList<Guest>();
		
		for(Schedule s : schedules) {
			for(Guest g : s.getVotes()) {
				guests.add(g);
			}
		}
		
		
		return guests;
	}
}
