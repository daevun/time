package at.time.user.services;

import at.time.user.dao.UserDao;
import at.time.user.model.User;

public class UserService {

	private final UserDao dao;

	public UserService() {
		dao = new UserDao();
	}

	public UserService(UserDao dao) {
		this.dao = dao;
	}

	public User createUser() {
		return new User();
	}

	public void saveUser(final User user) {
		dao.saveUser(user);
	}

	public User getUserByOid(final String oid) {
		return dao.getByOid(oid);
	}

}
