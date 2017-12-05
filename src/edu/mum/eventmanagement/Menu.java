package edu.mum.eventmanagement;

import javafx.application.Application;

public class Menu extends WindowBase {

	public Menu() {
		super("Menu", "Event Management System", 300, 200, true);
	}
	
	public static void main(String[] args) {
		Application.launch(Menu.class, args);
	}
}
