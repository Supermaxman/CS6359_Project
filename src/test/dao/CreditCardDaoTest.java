package test.dao;

import static org.junit.Assert.*;

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

public class CreditCardDaoTest {

	private DbManager db = new DbManager();
	private CreditCardDao testDao = new CreditCardDaoImpl();
	private UserDao userDao = new UserDaoImpl();
	private Connection conn;
	private User testUser;
	private CreditCard test;
	
	
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
		
		test = new CreditCard();
		test.setNumber("1234");
		test.setExpDate(new Date(2019, 1, 1));
		test.setCcv("111");
		
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
		testDao.create(conn, test, testUser.getUserId());
		
		CreditCard saved = testDao.retrieve(conn, test.getCardId());
		assertEqual(test, saved);
	}

	@Test
	public void testRetrieveByUser() throws Exception {
		testDao.create(conn, test, testUser.getUserId());
		
		CreditCard saved = testDao.retrieveByUser(conn, testUser.getUserId());
		assertEqual(test, saved);
	}
	
	private void assertEqual(CreditCard a, CreditCard b)
	{
		assertEquals(a.getCardId(), b.getCardId());
		assertEquals(a.getExpDate(), b.getExpDate());
		assertEquals(a.getCcv(), b.getCcv());
	}

}
