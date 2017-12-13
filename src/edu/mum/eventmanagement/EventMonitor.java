package edu.mum.eventmanagement;

import java.util.List;

import edu.mum.eventmanagement.controllers.EventController;
import edu.mum.eventmanagement.models.Event;
import edu.mum.eventmanagement.models.EventState;
import edu.mum.eventmanagement.services.EmailService;

public class EventMonitor extends Thread {
	
	private static EventMonitor instance;
	private static Thread monitorThread;
	
	private boolean running;
	
	private EventMonitor() {
		
	}
	
	private static void instanciate() {
		if(instance == null) {
			synchronized(EventMonitor.class) { //Double check if the instance is null (thread safe)
				if(instance == null) {
					instance = new EventMonitor();
				}
			}
		}
	}
	
	public static void monitor() {
		instanciate();
		
		if(monitorThread == null) {
			synchronized(EventMonitor.class) { //Double check if the instance is null (thread safe)
				if(monitorThread == null) {
					monitorThread = new Thread(instance);
					monitorThread.start();
				}
			}
		}
		
		instance.running = true;
	}
	
	public static void finish() {
		instance.running = false;
	}
	
	public static void trigger() {
		instanciate();
		instance.checkEvents();
	}
	
	@Override
    public void run() {
		while(instance.running) {
			try {
				checkEvents();
				Thread.sleep(5000);
			}catch(Exception e) {
				System.out.println(e);
			}
		}
    }
	
	private void checkEvents() {
		EventController ctrl = new EventController();
		List<Event> events = ctrl.getExpiredDueDate();
		
		for(Event event : events) {
			EmailService.sendExpiredDueDate(event);
			event.setState(EventState.locked);
			ctrl.update(event);
		}
	}
}
