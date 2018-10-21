package test.dao;

import java.sql.Connection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import db.DbManager;
import db.dao.CreditCardDao;
import db.dao.UserDao;
import db.dao.impl.CreditCardDaoImpl;
import db.dao.impl.UserDaoImpl;
import domain.user.CreditCard;
import domain.user.User;
import test.utils.TestUtils;

public class CreditCardDaoTest {

	private DbManager db = new DbManager();
	private CreditCardDao testDao = new CreditCardDaoImpl();
	private UserDao userDao = new UserDaoImpl();
	private Connection conn;
	private User testUser;
	private CreditCard testCard;
		
	@Before
	public void setUp() throws Exception {
		conn = db.getConnection();
		conn.setAutoCommit(false);
		
		testUser = TestUtils.generateUser();
		userDao.register(conn, testUser);
		
		testCard = testUser.getCreditCard();
		
	}

	@After
	public void tearDown() throws Exception {
		if (conn != null) 
		{
			conn.rollback();
		}
	}

	@Test
	public void testCreateRetrieve() throws Exception {
		testDao.create(conn, testCard, testUser.getUserId());
		
		CreditCard saved = testDao.retrieve(conn, testCard.getCardId());
		TestUtils.assertEqual(testCard, saved);
	}

	@Test
	public void testRetrieveByUser() throws Exception {
		testDao.create(conn, testCard, testUser.getUserId());
		
		CreditCard saved = testDao.retrieveByUser(conn, testUser.getUserId());
		TestUtils.assertEqual(testCard, saved);
	}
	
	
}
