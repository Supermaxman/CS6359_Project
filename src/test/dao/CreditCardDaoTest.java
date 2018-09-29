package test.dao;

import java.sql.Connection;
import java.sql.Date;

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
	
	
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() throws Exception {
		conn = db.getConnection();
		conn.setAutoCommit(false);
		
		testUser = new User();
		testUser.setUsername("Max");
		testUser.setPassword("123");
		testUser.setName("Max Weinzierl");
		testUser.setAddress("1234 Fake Ln");
		
		userDao.register(conn, testUser);
		
		testCard = new CreditCard();
		testCard.setNumber("1234");
		testCard.setExpDate(new Date(2019, 1, 1));
		testCard.setCcv("111");
		
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
