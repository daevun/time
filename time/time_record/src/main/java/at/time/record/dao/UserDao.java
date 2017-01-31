package at.time.record.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.google.common.collect.Lists;

import at.time.record.hibernate.HibernateUtil;
import at.time.record.model.User;

public class UserDao {

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

	public void saveUser(final User user) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		}
	}

}
