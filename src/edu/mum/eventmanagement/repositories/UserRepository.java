package edu.mum.eventmanagement.repositories;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import edu.mum.eventmanagement.models.*;

public class UserRepository extends RepositoryBase<User> implements IRepository<User> {

	public UserRepository() {
		super(User.class);
	}

	public User getByEmail(String email) {
		EntityManager em = HibernateUtil.getEntityManager();
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email",
				edu.mum.eventmanagement.models.User.class);
		query.setParameter("email", email);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
