package at.time.amendment.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import at.time.amendment.dao.UserDao;
import at.time.amendment.model.User;

@Named
public class UserService {

	@Inject
	private UserDao dao;

	public List<User> getAllUser() {
		return dao.getAll();
	}

}
