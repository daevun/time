package at.time.user.rest;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import at.time.user.dao.UserDao;

public class RestServiceTest {

    @Test
    public void testGetIt() {
    	assertNotNull(new UserDao().getBySozVers("99"));
    }

}
