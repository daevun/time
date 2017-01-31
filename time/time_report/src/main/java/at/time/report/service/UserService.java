package at.time.report.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import at.time.report.dao.UserDao;
import at.time.report.model.User;

@Named
public class UserService {

	@Inject
	private UserDao dao;

	public List<User> getAllUser() {
		return dao.getAll();
	}

}
