package com.avengers.bus.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class UserCountsDaoImpl implements UserCountsDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public int bookedCount(int userId) {
		String jpql = "SELECT COUNT(*) FROM Ticket t WHERE t.user_id = :userId";

		Long bookedCount = em.createQuery(jpql, Long.class).setParameter("userId", userId).getSingleResult();
		return bookedCount.intValue();
	}

	@Override
	public int travelCount(int userId) {
		String status = "confirmed";
		String jpql = "SELECT COUNT(*) FROM Ticket t WHERE t.user_id = :userId AND t.status =: status";
		Long travelcount = em.createQuery(jpql, Long.class).setParameter("userId", userId)
				.setParameter("status", status).getSingleResult();
		return travelcount.intValue();
	}

	@Override
	public double getWallet(int userId) {
		String jpql = "SELECT u.wallet FROM User u WHERE u.user_id = :userId";
		Double wallet = em.createQuery(jpql, Double.class).setParameter("userId", userId).getSingleResult();
		return wallet;
	}

	@Override
	public String getName(int userId) {
		String jpql = "SELECT u.full_name FROM User u WHERE u.user_id = :userId";
		String name = em.createQuery(jpql, String.class).setParameter("userId", userId).getSingleResult();
		return name.toString();
	}

}
