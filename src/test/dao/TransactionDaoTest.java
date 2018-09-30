package test.dao;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import db.DbManager;
import db.dao.TransactionDao;
import db.dao.UserDao;
import db.dao.impl.TransactionDaoImpl;
import db.dao.impl.UserDaoImpl;
import domain.product.Product;
import domain.transaction.Transaction;
import domain.user.User;
import test.utils.TestUtils;

public class TransactionDaoTest {

	private DbManager db = new DbManager();
	private TransactionDao testDao = new TransactionDaoImpl();
	private UserDao userDao = new UserDaoImpl();
	private Connection conn;
	private Transaction testProd;
	private List<Transaction> testList;
	private User testUser;
	
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
		
		testProd = new Transaction();
		testProd.setDate(new Date(2016, 1, 1));
		testProd.setPrice(50.0);
		testProd.setProducts(new ArrayList<Product>());
		
		testList = new ArrayList<Transaction>();
		testList.add(testProd);
		Transaction secondTest = new Transaction();
		secondTest.setDate(new Date(2015, 1, 1));
		secondTest.setPrice(100.0);
		secondTest.setProducts(new ArrayList<Product>());
		testList.add(secondTest);
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
		testDao.create(conn, testProd, testUser.getUserId());
		
		Transaction saved = testDao.retrieve(conn, testProd.getTrxnId());
		TestUtils.assertEqual(testProd, saved);
	}

	@Test
	public void testRetrieveByUser() throws Exception {
		
		for (Transaction trxn : testList) {
			testDao.create(conn, trxn, testUser.getUserId());
		}
		
		List<Transaction> saved = testDao.retrieveByUser(conn, testUser.getUserId());
		int idx = 0;
		for (Transaction s : saved) {
			TestUtils.assertEqual(s, testList.get(idx));
			idx++;
		}
		
	}
	
}
