package edu.mum.eventmanagement.models;

import java.util.*;
import javax.persistence.*;

@Entity
public class Food extends Activity {
	@Column(length = 255)
	private String recipe;
	
	@OneToMany
	private List<Ingredient> ingredients;
	
	public Food() {
		ingredients = new ArrayList<Ingredient>();
	}
}
