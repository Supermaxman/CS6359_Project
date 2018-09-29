package test.dao;

import static org.junit.Assert.*;

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

public class InventoryDaoTest {

	private DbManager db = new DbManager();
	private InventoryDao testDao = new InventoryDaoImpl();
	private UserDao userDao = new UserDaoImpl();
	private ProductDao prodDao = new ProductDaoImpl();
	private Connection conn;
	private Inventory test;
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
				
		test = new Inventory();
		test.setProducts(new ArrayList<Product>());
				
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
		Inventory saved = testDao.retrieveByUser(conn, testUser.getUserId());
		assertEqual(test, saved);
	}

	@Test
	public void testAddProduct() throws Exception {
		testDao.create(conn, test, testUser.getUserId());
		test.addProduct(testProd);
		testDao.addProduct(conn, testProd.getProdId(), test.getInvnId());
		Inventory saved = testDao.retrieveByUser(conn, testUser.getUserId());
		List<Product> savedProds = prodDao.retrieveByInventory(conn, test.getInvnId());
		saved.setProducts(savedProds);
		assertEqual(test, saved);
	}
	
	private void assertEqual(Inventory a, Inventory b) throws Exception {
		assertEquals(a.getInvnId(), b.getInvnId());
		List<Product> aProds = a.getProducts();
		List<Product> bProds = b.getProducts();
		for (int i = 0; i < aProds.size(); i++) {
			assertEqual(aProds.get(i), bProds.get(i));
		}
	}
	
	private void assertEqual(Product a, Product b) throws Exception {
		assertEquals(a.getProdId(), b.getProdId());
		assertEquals(a.getName(), b.getName());
		assertEquals(a.getDescription(), b.getDescription());
		assertEquals(a.isSold(), b.isSold());
		assertEquals(a.getPrice(), b.getPrice(), 0.001);
	}

}
