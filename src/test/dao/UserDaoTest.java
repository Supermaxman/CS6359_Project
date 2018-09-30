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
import test.utils.TestUtils;

public class UserDaoTest {

	private DbManager db = new DbManager();
	private UserDao testDao = new UserDaoImpl();
	private Connection conn;
	private User testUser;
	
	@Before
	public void setUp() throws Exception {
		conn = db.getConnection();
		conn.setAutoCommit(false);

		testUser = TestUtils.generateUser();
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
		testDao.register(conn, testUser);
		User savedUser = testDao.retrieve(conn, testUser.getUserId());		
		savedUser.setCreditCard(testUser.getCreditCard());
		savedUser.setTransactions(testUser.getTransactions());
		savedUser.setCart(testUser.getCart());
		savedUser.setInventory(testUser.getInventory());
		TestUtils.assertEqual(testUser, savedUser);
	}
	
	@Test
	public void testUpdate() throws Exception {
		testDao.register(conn, testUser);
				
		testUser.setName("Maxwell");
		
		int count = testDao.update(conn, testUser);
		assertEquals(count, 1);
		
		User savedUser = testDao.retrieve(conn, testUser.getUserId());
		savedUser.setCreditCard(testUser.getCreditCard());
		savedUser.setTransactions(testUser.getTransactions());
		savedUser.setCart(testUser.getCart());
		savedUser.setInventory(testUser.getInventory());
		TestUtils.assertEqual(testUser, savedUser);
	}
	

}
