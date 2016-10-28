package at.time.user.services;

import at.time.base.rabbit.RabbitManager;
import at.time.user.dao.UserDao;
import at.time.user.model.User;

public class UserService {

	private RabbitManager rabbit;
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

	public User getUserByOid(final String oid) {
		return dao.getByOid(oid);
	}

	public void setDao(UserDao dao) {
		this.dao = dao;
	}

	public void setRabbit(RabbitManager rabbit) {
		this.rabbit = rabbit;
	}

}
