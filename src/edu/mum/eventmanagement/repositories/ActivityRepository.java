package edu.mum.eventmanagement.repositories;

import edu.mum.eventmanagement.models.Activity;
import edu.mum.eventmanagement.models.Schedule;

public class ActivityRepository extends RepositoryBase<Activity> implements IRepository<Activity> {

	 public ActivityRepository() {
		super(Activity.class);
	}

}
