package at.time.user.dao;

import javax.inject.Named;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import at.time.user.hibernate.HibernateUtil;
import at.time.user.model.User;

@Named
public class UserDao {

	public void saveUser(final User user) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		}
	}

	public User getBySozVers(final String sozVers) {
		User user = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			final CriteriaBuilder builder = session.getCriteriaBuilder();
			final CriteriaQuery<User> criteria = builder.createQuery(User.class);
			final Root<User> root = criteria.from(User.class);
			criteria.select(root);
			criteria.where(builder.equal(root.get("sozVers"), sozVers));
			user = session.createQuery(criteria).getSingleResult();
		}
		return user;
	}

}
