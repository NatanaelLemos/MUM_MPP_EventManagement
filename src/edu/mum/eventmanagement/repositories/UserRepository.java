package edu.mum.eventmanagement.repositories;

import java.util.List;
import java.util.stream.Collectors;

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
			System.out.println(e);
			return null;
		}
	}

	public User getByLogin(String email, String cryptPassword) {
		EntityManager em = HibernateUtil.getEntityManager();
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email and u.password = :password",
				edu.mum.eventmanagement.models.User.class);
		query.setParameter("email", email);
		query.setParameter("password", cryptPassword);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println(e);
			return null;
		}
	}

	public List<User> getApprovers() {
		List<User> users = getAll();
		return users.stream()
				.filter(u -> u.hasRole(Approver.class))
				.collect(Collectors.toList());
	}
}
