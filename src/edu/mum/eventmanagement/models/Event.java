package edu.mum.eventmanagement.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

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
	
	@ManyToOne
	private Location location;
	
	@OneToMany
	private List<Advertisement> advertisements;
	
	@OneToMany
	private List<Schedule> schedules;
	
	public String getName() {
		return this.name;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public Date getDueDate() {
		return this.dueDate;
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
}
