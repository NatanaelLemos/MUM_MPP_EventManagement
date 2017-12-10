package edu.mum.eventmanagement.models;
import javax.persistence.*;

@Entity
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length=255)
	private String name;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
	private Food food;
}
