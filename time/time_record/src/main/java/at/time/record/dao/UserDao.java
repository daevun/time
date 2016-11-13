package at.time.record.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.google.common.collect.Lists;

import at.time.record.hibernate.HibernateUtil;
import at.time.record.model.User;

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

	public List<User> getAll() {
		List<User> users = Lists.newArrayList();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			final CriteriaQuery<User> query = session.getCriteriaBuilder().createQuery(User.class);
			final Root<User> root = query.from(User.class);
			query.select(root);
			users = session.createQuery(query).getResultList();
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
