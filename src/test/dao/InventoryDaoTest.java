package test.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import db.DbManager;
import db.dao.InventoryDao;
import db.dao.ProductDao;
import db.dao.UserDao;
import db.dao.impl.InventoryDaoImpl;
import db.dao.impl.ProductDaoImpl;
import db.dao.impl.UserDaoImpl;
import domain.product.Product;
import domain.user.Inventory;
import domain.user.User;
import test.utils.TestUtils;

public class InventoryDaoTest {

	private DbManager db = new DbManager();
	private InventoryDao testDao = new InventoryDaoImpl();
	private UserDao userDao = new UserDaoImpl();
	private ProductDao prodDao = new ProductDaoImpl();
	private Connection conn;
	private Inventory testInvn;
	private User testUser;
	private Product testProd;
	
	@Before
	public void setUp() throws Exception {
		conn = db.getConnection();
		conn.setAutoCommit(false);

		testProd = new Product();
		testProd.setName("Stary Night");
		testProd.setDescription("Pretty!");
		testProd.setSold(false);

		prodDao.create(conn, testProd);
		
		testUser = new User();
		testUser.setUsername("Max");
		testUser.setPassword("123");
		testUser.setName("Max Weinzierl");
		testUser.setAddress("1234 Fake Ln");
		
		userDao.register(conn, testUser);
				
		testInvn = new Inventory();
		testInvn.setProducts(new ArrayList<Product>());
				
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
		testDao.create(conn, testInvn, testUser.getUserId());
		Inventory saved = testDao.retrieveByUser(conn, testUser.getUserId());
		saved.setProducts(testInvn.getProducts());
		TestUtils.assertEqual(testInvn, saved);
	}

	@Test
	public void testAddProduct() throws Exception {
		testDao.create(conn, testInvn, testUser.getUserId());
		testInvn.addProduct(testProd);
		testDao.addProduct(conn, testProd.getProdId(), testInvn.getInvnId());
		Inventory saved = testDao.retrieveByUser(conn, testUser.getUserId());
		List<Product> savedProds = prodDao.retrieveByInventory(conn, testInvn.getInvnId());
		saved.setProducts(savedProds);
		TestUtils.assertEqual(testInvn, saved);
	}
		

}
