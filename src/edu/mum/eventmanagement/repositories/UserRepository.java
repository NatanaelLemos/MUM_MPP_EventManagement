package edu.mum.eventmanagement.repositories;

import edu.mum.eventmanagement.models.*;
import edu.mum.eventmanagement.repositories.*;

public class UserRepository extends RepositoryBase<User> implements IRepository<User> {

	public UserRepository() {
		super(User.class);
	}

}
