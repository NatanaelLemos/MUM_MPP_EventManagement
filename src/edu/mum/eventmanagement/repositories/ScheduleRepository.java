package edu.mum.eventmanagement.repositories;

import edu.mum.eventmanagement.models.Schedule;

public class ScheduleRepository extends RepositoryBase<Schedule> implements IRepository<Schedule>{
	public ScheduleRepository() {
		super(Schedule.class);
	}

}
