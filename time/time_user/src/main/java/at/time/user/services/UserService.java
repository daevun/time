package at.time.user.services;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import at.time.user.dao.UserDao;
import at.time.user.model.User;
import at.time.user.rabbit.RabbitManager;

@Dependent
public class UserService {

	@Inject
	private RabbitManager rabbit;

	@Inject
	private UserDao dao;

	public User createUser() {
		return new User();
	}

	public void saveUser(final User user) {
		dao.saveUser(user);
		rabbit.publish(user);
	}

}
