package edu.mum.eventmanagement.models;

import java.util.*;

public class Food extends Activity {
	private String recipe;
	private List<Ingredient> ingredients;
	
	public Food() {
		ingredients = new ArrayList<Ingredient>();
	}
}
