package test.services;

import static org.junit.Assert.*;

import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import db.services.UserPersistenceService;
import db.services.impl.UserPersistenceServiceImpl;
import domain.user.Login;
import domain.user.User;
import test.utils.TestUtils;

public class UserPersistenceServiceTest {

	private UserPersistenceService testService = new UserPersistenceServiceImpl();
	private User testUser;
	
	@Before
	public void setUp() throws Exception {
		testUser = TestUtils.generateUser();
	}

	@After
	public void tearDown() throws Exception {
		//TODO add delete clean-up code when delete is implemented.
		//TODO for now just use DROP SCHEMA ARTKART; 
		//TODO and then re-create.
	}
	
	@Test
	public void testRegisterRetrieve() throws Exception {
		testService.register(testUser);
		User saved = testService.retrieve(testUser.getUserId());
		TestUtils.assertEqual(testUser, saved);
	}

	@Test
	public void testLogin() throws Exception {
		testService.register(testUser);
		Login login = new Login(testUser.getUsername(), testUser.getPassword());
		User saved = testService.validate(login);
		TestUtils.assertEqual(testUser, saved);
	}
	
	@Test
	public void testFailedLogin() throws Exception {
		testService.register(testUser);
		Login login = new Login(testUser.getUsername(), testUser.getPassword().substring(0, 1));
		User saved = testService.validate(login);
		assertNull(saved);
	}
	
	@Test(expected = SQLException.class)
	public void testFailedRegister() throws Exception {
		testService.register(testUser);
		testUser.setUserId(null);
		testService.register(testUser);
	}
	

}
