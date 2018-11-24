package test.dao;

import java.sql.Connection;
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
import domain.transaction.Transaction;
import domain.user.User;
import test.utils.TestUtils;

public class TransactionDaoTest {

	private DbManager db = DbManager.getInstance();
	private TransactionDao testDao = TransactionDaoImpl.getInstance();
	private UserDao userDao = UserDaoImpl.getInstance();
	private Connection conn;
	private Transaction testTrxn;
	private List<Transaction> testList;
	private User testUser;
	
	@Before
	public void setUp() throws Exception {
		conn = db.getConnection();
		conn.setAutoCommit(false);

		testUser = TestUtils.generateUser();
		
		userDao.create(conn, testUser);
		
		testTrxn = TestUtils.generateTransaction();
		
		testList = new ArrayList<Transaction>();
		testList.add(testTrxn);
		Transaction secondTest = TestUtils.generateTransaction();
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
		testDao.create(conn, testTrxn, testUser.getUserId());
		
		Transaction saved = testDao.retrieve(conn, testTrxn.getTrxnId());
		TestUtils.assertEqual(testTrxn, saved);
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
