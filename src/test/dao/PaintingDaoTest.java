package test.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import db.DbManager;
import db.dao.PaintingDao;
import db.dao.ProductDao;
import db.dao.UserDao;
import db.dao.impl.PaintingDaoImpl;
import db.dao.impl.ProductDaoImpl;
import db.dao.impl.UserDaoImpl;
import domain.product.Painting;
import domain.user.User;
import test.utils.TestUtils;

public class PaintingDaoTest {

	private DbManager db = new DbManager();
	private PaintingDao testDao = new PaintingDaoImpl();
	private UserDao userDao = new UserDaoImpl();
	private ProductDao prodDao = new ProductDaoImpl();
	private Connection conn;
	private Painting testPaint;
	private User testUser;
	
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
		
		testPaint = new Painting();
		testPaint.setName("Stary Night");
		testPaint.setDescription("Pretty!");
		testPaint.setSold(false);
		testPaint.setPrice(1000.0);
		testPaint.setCanvasType("Paper");
		testPaint.setPaintType("Oil");
		testPaint.setLength(15.0);
		testPaint.setWidth(10.0);
		prodDao.create(conn, testPaint);
		
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
		testDao.create(conn, testPaint);
		
		Painting saved = testDao.retrieve(conn, testPaint.getProdId());
		TestUtils.assertEqual(testPaint, saved);
	}
	
	@Test
	public void testRetrieveAll() throws Exception {
		testDao.create(conn, testPaint);
		
		List<Painting> saved = testDao.retrieveAll(conn);
		assertEquals(saved.size(), 1);
		TestUtils.assertEqual(testPaint, saved.get(0));
	}
	

}
