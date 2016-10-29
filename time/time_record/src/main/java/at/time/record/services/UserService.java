package at.time.record.services;

import java.util.List;

import at.time.record.dao.UserDao;
import at.time.record.model.User;

public class UserService {

	private UserDao dao;

	public UserService() {
		setDao(new UserDao());
	}

	public List<User> getAllUser() {
		return dao.getAll();
	}

	public void setDao(final UserDao dao) {
		this.dao = dao;
	}

	public User getByOid(final String oid) {
		return dao.getByOid(oid);
	}

}
