package test.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import db.DbManager;
import db.dao.CartDao;
import db.dao.InventoryDao;
import db.dao.ProductDao;
import db.dao.TransactionDao;
import db.dao.UserDao;
import db.dao.impl.CartDaoImpl;
import db.dao.impl.InventoryDaoImpl;
import db.dao.impl.ProductDaoImpl;
import db.dao.impl.TransactionDaoImpl;
import db.dao.impl.UserDaoImpl;
import domain.product.Product;
import domain.transaction.Transaction;
import domain.user.Cart;
import domain.user.Inventory;
import domain.user.User;
import test.utils.TestUtils;

public class ProductDaoTest {

	private DbManager db = new DbManager();
	private ProductDao testDao = new ProductDaoImpl();
	private UserDao userDao = new UserDaoImpl();
	private InventoryDao invnDao = new InventoryDaoImpl();
	private CartDao cartDao = new CartDaoImpl();
	private TransactionDao trxnDao = new TransactionDaoImpl();
	private Connection conn;
	private Product testProd;
	private User testUser;
	private Inventory testInvn;
	private Cart testCart;
	private Transaction testTrxn;
	private int initialCount;
	private int initialCartCount;
	private int initialInvnCount;
	private int initialSellerCount;
	
	@Before
	public void setUp() throws Exception {
		conn = db.getConnection();
		conn.setAutoCommit(false);

		initialCount = testDao.retrieveAll(conn).size();
		testProd = TestUtils.generateProduct();
		testDao.create(conn, testProd);
		
		testUser = TestUtils.generateUser();
		userDao.register(conn, testUser);
		testInvn = testUser.getInventory();
		invnDao.create(conn, testInvn, testUser.getUserId());
		testUser.getInventory().addProduct(testProd);
		initialSellerCount = testDao.retrieveBySeller(conn, testUser.getUserId()).size();
		initialInvnCount = testDao.retrieveByInventory(conn, testInvn.getInvnId()).size();
		invnDao.addProduct(conn, testProd.getProdId(), testInvn.getInvnId());
		
		testCart = testUser.getCart();
		cartDao.create(conn, testCart, testUser.getUserId());
		testUser.getCart().addProduct(testProd);
		initialCartCount = testDao.retrieveByCart(conn, testCart.getCartId()).size();
		cartDao.update(conn, testCart);
		
		testTrxn = TestUtils.generateTransaction();
		testUser.addTransaction(testTrxn);
		testTrxn.addProduct(testProd);	
		trxnDao.create(conn, testTrxn, testUser.getUserId());
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
		Product saved = testDao.retrieve(conn, testProd.getProdId());
		saved.setCategory(testProd.getCategory());
		TestUtils.assertEqual(testProd, saved);
	}
	
	@Test
	public void testRetrieveAll() throws Exception {
		List<Product> saved = testDao.retrieveAll(conn);
		
		assertEquals(saved.size(), initialCount + 1);
		int idx = TestUtils.Find(testProd, saved);
		assertTrue(idx != -1);
		
		saved.get(idx).setCategory(testProd.getCategory());
		TestUtils.assertEqual(testProd, saved.get(idx));
	}
	
	@Test
	public void testUpdate() throws Exception {
		testProd.setName("Very Stary Night");
				
		int count = testDao.update(conn, testProd);
		assertEquals(count, 1);
		
		Product saved = testDao.retrieve(conn, testProd.getProdId());
		saved.setCategory(testProd.getCategory());
		TestUtils.assertEqual(testProd, saved);
	}
	
	@Test
	public void testRetrieveByCart() throws Exception {
		List<Product> saved = testDao.retrieveByCart(conn, testCart.getCartId());

		assertEquals(saved.size(), initialCartCount + 1);
		int idx = TestUtils.Find(testProd, saved);
		assertTrue(idx != -1);
		
		saved.get(idx).setCategory(testProd.getCategory());
		TestUtils.assertEqual(testProd, saved.get(idx));
	}

	@Test
	public void testRetrieveByInventory() throws Exception {
		List<Product> saved = testDao.retrieveByInventory(conn, testInvn.getInvnId());
		
		assertEquals(saved.size(), initialInvnCount + 1);
		int idx = TestUtils.Find(testProd, saved);
		assertTrue(idx != -1);
		
		saved.get(idx).setCategory(testProd.getCategory());
		TestUtils.assertEqual(testProd, saved.get(idx));
	}

	@Test
	public void testRetrieveBySeller() throws Exception {
		List<Product> saved = testDao.retrieveBySeller(conn, testUser.getUserId());

		assertEquals(saved.size(), initialSellerCount + 1);
		int idx = TestUtils.Find(testProd, saved);
		assertTrue(idx != -1);
		
		saved.get(idx).setCategory(testProd.getCategory());
		TestUtils.assertEqual(testProd, saved.get(idx));
	}

	@Test
	public void testRetrieveByTransaction() throws Exception {
		List<Product> saved = testDao.retrieveByTransaction(conn, testTrxn.getTrxnId());
		
		assertEquals(saved.size(), 1);
		int idx = TestUtils.Find(testProd, saved);
		assertTrue(idx != -1);
		
		saved.get(idx).setCategory(testProd.getCategory());
		
		TestUtils.assertEqual(testProd, saved.get(idx));
	}
	
}
