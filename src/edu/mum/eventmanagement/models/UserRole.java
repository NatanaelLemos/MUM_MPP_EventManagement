package edu.mum.eventmanagement.models;
import javax.persistence.*;

@Entity
public abstract class UserRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
	private User user;
    
    public void setUser(User user) {
    	this.user = user;
    }
}
