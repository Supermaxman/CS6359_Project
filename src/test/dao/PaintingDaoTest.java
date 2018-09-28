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

public class PaintingDaoTest {

	private DbManager db = new DbManager();
	private PaintingDao testDao = new PaintingDaoImpl();
	private UserDao userDao = new UserDaoImpl();
	private ProductDao prodDao = new ProductDaoImpl();
	private Connection conn;
	private Painting test;
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
		
		test = new Painting();
		test.setName("Stary Night");
		test.setDescription("Pretty!");
		test.setSold(false);
		test.setPrice(1000.0);
		test.setCanvasType("Paper");
		test.setPaintType("Oil");
		test.setLength(15.0);
		test.setWidth(10.0);
		prodDao.create(conn, test);
		
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
		testDao.create(conn, test);
		
		Painting saved = testDao.retrieve(conn, test.getProdId());
		assertEqual(test, saved);
	}
	
	@Test
	public void testRetrieveAll() throws Exception {
		testDao.create(conn, test);
		
		List<Painting> saved = testDao.retrieveAll(conn);
		assertEquals(saved.size(), 1);
		assertEqual(test, saved.get(0));
	}
	
	private void assertEqual(Painting a, Painting b) throws Exception {
		assertEquals(a.getProdId(), b.getProdId());
		assertEquals(a.getName(), b.getName());
		assertEquals(a.getDescription(), b.getDescription());
		assertEquals(a.isSold(), b.isSold());
		assertEquals(a.getPrice(), b.getPrice(), 0.001);
		assertEquals(a.getPaintType(), b.getPaintType());
		assertEquals(a.getCanvasType(), b.getCanvasType());
		assertEquals(a.getLength(), b.getLength(), 0.001);
		assertEquals(a.getWidth(), b.getWidth(), 0.001);
	}

}
