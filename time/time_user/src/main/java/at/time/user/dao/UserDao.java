package at.time.user.dao;

import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

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
			final Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("sozVers", sozVers));
			user = (User) criteria.uniqueResult();
		}
		return user;
	}

}
