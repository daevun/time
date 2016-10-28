package at.time.user.services;

import com.google.gson.Gson;

import at.time.base.rabbit.RabbitManager;
import at.time.user.dao.UserDao;
import at.time.user.model.User;

public class UserService {

	private final RabbitManager rabbit;
	private UserDao dao;

	public UserService() {
		setDao(new UserDao());
		rabbit = RabbitManager.getInstance();
	}

	public User createUser() {
		return new User();
	}

	public void saveUser(final User user) {
		dao.saveUser(user);
		rabbit.publishMessage(new Gson().toJson(user));
	}

	public User getUserByOid(final String oid) {
		return dao.getByOid(oid);
	}

	public void setDao(UserDao dao) {
		this.dao = dao;
	}

}
