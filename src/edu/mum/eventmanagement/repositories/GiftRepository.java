package edu.mum.eventmanagement.repositories;

import edu.mum.eventmanagement.models.Gift;

public class GiftRepository extends RepositoryBase<Gift> implements IRepository<Gift> {

	public GiftRepository() {
		super(Gift.class);
	}
}
