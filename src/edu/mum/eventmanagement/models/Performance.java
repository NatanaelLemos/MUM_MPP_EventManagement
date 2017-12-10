package edu.mum.eventmanagement.models;
import javax.persistence.Entity;

@Entity
public class Performance extends Activity {
	Performance(){
		
	}
	
	public Performance(String name) {
		setName(name);
	}
}
