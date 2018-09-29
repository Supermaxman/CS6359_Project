package test.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
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

public class ProductDaoTest {

	private DbManager db = new DbManager();
	private ProductDao testDao = new ProductDaoImpl();
	private UserDao userDao = new UserDaoImpl();
	private InventoryDao invnDao = new InventoryDaoImpl();
	private CartDao cartDao = new CartDaoImpl();
	private TransactionDao trxnDao = new TransactionDaoImpl();
	private Connection conn;
	private Product test;
	private User testUser;
	private Inventory testInvn;
	private Cart testCart;
	private Transaction testTrxn;
	
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() throws Exception {
		conn = db.getConnection();
		conn.setAutoCommit(false);

		test = new Product();
		test.setName("Stary Night");
		test.setDescription("Pretty!");
		test.setSold(false);

		testDao.create(conn, test);
		
		testUser = new User();
		testUser.setUsername("Max");
		testUser.setPassword("123");
		testUser.setName("Max Weinzierl");
		testUser.setAddress("1234 Fake Ln");
		
		userDao.register(conn, testUser);
		
		testInvn = new Inventory();
		testInvn.setProducts(new ArrayList<Product>());
		invnDao.create(conn, testInvn, testUser.getUserId());
		testInvn.addProduct(test);
		invnDao.addProduct(conn, test.getProdId(), testInvn.getInvnId());
		testUser.setInventory(testInvn);
		
		testCart = new Cart();
		testCart.setProducts(new ArrayList<Product>());
		
		cartDao.create(conn, testCart, testUser.getUserId());
		testCart.addProduct(test);
		cartDao.update(conn, testCart);
		testUser.setCart(testCart);
		
		testTrxn = new Transaction();
		testTrxn.setDate(new Date(2016, 1, 1));
		testTrxn.setPrice(100.0);
		testTrxn.setProducts(new ArrayList<Product>());
		testUser.setTransactions(new ArrayList<Transaction>());
		testUser.addTransaction(testTrxn);
		
		testTrxn.addProduct(test);
		
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
		Product saved = testDao.retrieve(conn, test.getProdId());
		assertEqual(test, saved);
	}
	
	@Test
	public void testRetrieveAll() throws Exception {
		List<Product> saved = testDao.retrieveAll(conn);
		assertEquals(saved.size(), 1);
		assertEqual(test, saved.get(0));
	}
	
	@Test
	public void testUpdate() throws Exception {
		test.setName("Very Stary Night");
				
		int count = testDao.update(conn, test);
		assertEquals(count, 1);
		
		Product saved = testDao.retrieve(conn, test.getProdId());
		assertEqual(test, saved);
	}
	
	@Test
	public void testRetrieveByCart() throws Exception {
		List<Product> saved = testDao.retrieveByCart(conn, testCart.getCartId());

		assertEquals(saved.size(), 1);
		assertEqual(test, saved.get(0));
	}

	@Test
	public void testRetrieveByInventory() throws Exception {
		List<Product> saved = testDao.retrieveByInventory(conn, testInvn.getInvnId());
		
		assertEquals(saved.size(), 1);
		assertEqual(test, saved.get(0));
	}

	@Test
	public void testRetrieveBySeller() throws Exception {
		List<Product> saved = testDao.retrieveBySeller(conn, testUser.getUserId());
		
		assertEquals(saved.size(), 1);
		assertEqual(test, saved.get(0));
	}

	@Test
	public void testRetrieveByTransaction() throws Exception {
		List<Product> saved = testDao.retrieveByTransaction(conn, testTrxn.getTrxnId());
		
		assertEquals(saved.size(), 1);
		assertEqual(test, saved.get(0));
	}
	
	private void assertEqual(Product a, Product b) throws Exception {
		assertEquals(a.getProdId(), b.getProdId());
		assertEquals(a.getName(), b.getName());
		assertEquals(a.getDescription(), b.getDescription());
		assertEquals(a.isSold(), b.isSold());
		assertEquals(a.getPrice(), b.getPrice(), 0.001);
	}
}
