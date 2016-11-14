package at.time.record.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import at.time.record.dao.UserDao;
import at.time.record.model.User;

@Named
public class UserService {

	@Inject
	private UserDao dao;

	public List<User> getAllUser() {
		return dao.getAll();
	}

}
