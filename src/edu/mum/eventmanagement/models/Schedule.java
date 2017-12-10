package edu.mum.eventmanagement.models;

import java.util.*;
import javax.persistence.*;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;

@Entity
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length=8)
	private String timeStart;

	/**
	 * @param timeStart the timeStart to set
	 */
	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	@Column(length=8)
	private String timeEnd;
	
	private ScheduleState state;
	
	@OneToMany(
	        orphanRemoval = true
	    )
	private List<Guest> votes;
	
    /**
	 * @return the votes
	 */
	public List<Guest> getVotes() {
		return votes;
	}
	/**
	 * @param votes the votes to set
	 */
	public void setVotes(List<Guest> votes) {
		this.votes = votes;
	}
	/**
	 * @return the state
	 */
	public ScheduleState getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(ScheduleState state) {
		this.state = state;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_id")
	private Activity activity;
	
    /**
	 * @return the activity
	 */
	public Activity getActivity() {
		return activity;
	}
	/**
	 * @param activity the activity to set
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
	private Event event;
	
    //private  SimpleObjectProperty<EditButton> editButton;
	Schedule() {
	}
	public Schedule(String timeStart, String timeEnd, Event event) {
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.event = event;
		//editButton = new SimpleObjectProperty(new EditButton(this.timeStart));
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getTimeStart() {
		return this.timeStart;
	}
	
	public String getTimeEnd() {
		return this.timeEnd;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
	private Host host;
	

}
