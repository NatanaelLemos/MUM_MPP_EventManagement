package edu.mum.eventmanagement.repositories;

import edu.mum.eventmanagement.models.Event;
import edu.mum.eventmanagement.models.*;

public class ScheduleRepository extends RepositoryBase<Schedule> implements IRepository<Schedule> {
	public ScheduleRepository() {
		super(Schedule.class);
	}
}
