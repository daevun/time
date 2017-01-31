package at.time.report.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.google.common.collect.Lists;

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

	@SuppressWarnings("unchecked")
	public List<User> getAll() {
		List<User> users = Lists.newArrayList();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			users = session.createCriteria(User.class).list();
		}
		return users;
	}

}
