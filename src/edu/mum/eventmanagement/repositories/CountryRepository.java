package edu.mum.eventmanagement.repositories;

import edu.mum.eventmanagement.models.Country;

public class CountryRepository extends RepositoryBase<Country> implements IRepository<Country> {

	public CountryRepository() {
		super(Country.class);
	}

}
