package at.time.user.services;

import javax.enterprise.context.Dependent;

import at.time.user.dao.UserDao;
import at.time.user.model.User;
import at.time.user.rabbit.RabbitManager;

@Dependent
public class UserService {

	public User createUser() {
		return new User();
	}

	public void saveUser(final User user) {
		new UserDao().saveUser(user);
		new RabbitManager().publish(user);
	}

}
