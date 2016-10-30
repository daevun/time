package at.time.user.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import at.time.user.hibernate.HibernateUtil;
import at.time.user.model.User;

public class UserDao {

	public User getByOid(final String oid) {
		User user = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			user = session.get(User.class, oid);
			Hibernate.initialize(user);
		}
		return user;
	}

	public void saveUser(final User user) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		}
	}

}
