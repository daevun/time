package at.time.user.dao;

import org.hibernate.Session;

import at.time.user.hibernate.HibernateUtil;
import at.time.user.model.User;

public class UserDao {

	public void saveUser(final User user) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		}
	}

}
