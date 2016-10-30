package at.time.user.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import at.time.base.rabbit.RabbitManager;
import at.time.user.dao.UserDao;
import at.time.user.model.User;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	private UserDao dao;

	@Mock
	private RabbitManager rabbit;

	@InjectMocks
	private UserService service;

	@Test
	public void testSaveUser() {
		final User user = createUser();
		service.saveUser(user);
		Mockito.verify(dao).saveUser(user);
		Mockito.verify(rabbit).publish(user);
	}

	private User createUser() {
		final User user = service.createUser();
		user.setOid("1111");
		user.setName("Test");
		user.setSurname("Tester");
		return user;
	}

}
