package at.time.user.services;

import javax.inject.Inject;
import javax.inject.Named;

import at.time.base.rabbit.RabbitManager;
import at.time.user.dao.UserDao;
import at.time.user.model.User;

@Named
public class UserService {

	@Inject
	private RabbitManager rabbit;

	@Inject
	private UserDao dao;

	public UserService() {
		setDao(new UserDao());
		setRabbit(new RabbitManager());
	}

	public User createUser() {
		return new User();
	}

	public void saveUser(final User user) {
		dao.saveUser(user);
		rabbit.publish(user);
	}

	public void setDao(UserDao dao) {
		this.dao = dao;
	}

	public void setRabbit(RabbitManager rabbit) {
		this.rabbit = rabbit;
	}

}
