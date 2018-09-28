package test.dao;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import db.DbManager;
import db.dao.UserDao;
import db.dao.impl.UserDaoImpl;
import domain.user.User;

public class UserDaoTest {

	private DbManager db = new DbManager();
	private UserDao testDao = new UserDaoImpl();
	private Connection conn;
	private User test;
	
	@Before
	public void setUp() throws Exception {
		conn = db.getConnection();
		conn.setAutoCommit(false);

		test = new User();
		test.setUsername("Max");
		test.setPassword("123");
		test.setName("Max Weinzierl");
		test.setAddress("1234 Fake Ln");
	}

	@After
	public void tearDown() throws Exception {
		if (conn != null) 
		{
			conn.rollback();
		}
	}

	@Test
	public void testRegisterRetrieve() throws Exception {
		testDao.register(conn, test);
		User savedUser = testDao.retrieve(conn, test.getUserId());
		
		assertEqual(test, savedUser);
	}
	
	@Test
	public void testUpdate() throws Exception {
		testDao.register(conn, test);
				
		test.setName("Maxwell");
		
		int count = testDao.update(conn, test);
		assertEquals(count, 1);
		
		User saved = testDao.retrieve(conn, test.getUserId());
		assertEqual(test, saved);
	}
	
	private void assertEqual(User a, User b)
	{
		assertEquals(a.getUserId(), b.getUserId());
		assertEquals(a.getUsername(), b.getUsername());
		assertEquals(a.getPassword(), b.getPassword());
		assertEquals(a.getName(), b.getName());
		assertEquals(a.getAddress(), b.getAddress());
	}

}
