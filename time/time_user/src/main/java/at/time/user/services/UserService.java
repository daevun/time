package at.time.user.services;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import at.time.user.dao.UserDao;
import at.time.user.model.User;
import at.time.user.rabbit.RabbitManager;

@Dependent
public class UserService {

	@Inject
	private UserDao userDao;

	@Inject
	private RabbitManager rabbit;

	public User createUser() {
		return new User();
	}

	public void saveUser(final User user) {
		userDao.saveUser(user);
		rabbit.publish(user);
	}

}
