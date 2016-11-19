package at.time.report.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import at.time.report.hibernate.HibernateUtil;
import at.time.report.model.User;

public class UserDao {

	public void saveUser(final User user) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		}
	}

	public User getByOid(final Long oid) {
		User user = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			user = session.get(User.class, oid);
			Hibernate.initialize(user);
		}
		return user;
	}

}
