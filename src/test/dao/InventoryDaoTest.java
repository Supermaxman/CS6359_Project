package test.dao;

import java.sql.Connection;
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

	private DbManager db = DbManager.getInstance();
	private InventoryDao testDao = InventoryDaoImpl.getInstance();
	private UserDao userDao = UserDaoImpl.getInstance();
	private ProductDao prodDao = ProductDaoImpl.getInstance();
	private Connection conn;
	private Inventory testInvn;
	private User testUser;
	private Product testProd;
	
	@Before
	public void setUp() throws Exception {
		conn = db.getConnection();
		conn.setAutoCommit(false);

		testProd = TestUtils.generateProduct();
		prodDao.create(conn, testProd);
		
		testUser = TestUtils.generateUser();
		userDao.create(conn, testUser);
						
		testInvn = testUser.getInventory();
				
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
		for (Product prod : savedProds) {
			prod.setCategory(testProd.getCategory());
		}
		saved.setProducts(savedProds);
		TestUtils.assertEqual(testInvn, saved);
	}
		

}
