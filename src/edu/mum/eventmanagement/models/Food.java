package edu.mum.eventmanagement.models;

import java.util.*;
import javax.persistence.*;

@Entity
public class Food extends Activity {
	@Column(length = 255)
	private String recipe;
	
    @OneToMany(
        mappedBy = "food", 
        cascade = CascadeType.ALL, 
        orphanRemoval = true
    )
	private List<Ingredient> ingredients;
	
    Food(){
    	
    }
    
	public Food(String name) {
		ingredients = new ArrayList<Ingredient>();
		setName(name);
	}
}
