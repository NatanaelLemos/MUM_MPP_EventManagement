package edu.mum.eventmanagement.repositories;

import edu.mum.eventmanagement.models.Location;

public class LocationRepository extends RepositoryBase<Location> implements IRepository<Location> {

	public LocationRepository() {
		super(Location.class);
	}
}