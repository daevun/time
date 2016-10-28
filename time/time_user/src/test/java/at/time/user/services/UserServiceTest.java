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
	}

	@Test
	public void testGetByOid() {
		final User user = createUser();
		final String oid = user.getOid();
		Mockito.when(dao.getByOid(oid)).thenReturn(user);
		service.getUserByOid(oid);
		Mockito.verify(dao).getByOid(oid);
	}

	private User createUser() {
		final User user = new User();
		user.setOid("1111");
		user.setName("Test");
		user.setSurname("Tester");
		return user;
	}

}
