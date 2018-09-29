package test.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import db.DbManager;
import db.dao.CartDao;
import db.dao.ProductDao;
import db.dao.UserDao;
import db.dao.impl.CartDaoImpl;
import db.dao.impl.ProductDaoImpl;
import db.dao.impl.UserDaoImpl;
import domain.product.Product;
import domain.user.Cart;
import domain.user.User;

public class CartDaoTest {

	private DbManager db = new DbManager();
	private CartDao testDao = new CartDaoImpl();
	private UserDao userDao = new UserDaoImpl();
	private ProductDao prodDao = new ProductDaoImpl();
	private Connection conn;
	private Cart testCart;
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
				
		testCart = new Cart();
		testCart.setProducts(new ArrayList<Product>());
				
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
		testDao.create(conn, testCart, testUser.getUserId());
		Cart saved = testDao.retrieveByUser(conn, testUser.getUserId());
		assertEqual(testCart, saved);
	}
	
	@Test
	public void testUpdate() throws Exception {
		testDao.create(conn, testCart, testUser.getUserId());
		testCart.addProduct(testProd);
		int count = testDao.update(conn, testCart);
		assertEquals(count, 1);
		Cart saved = testDao.retrieveByUser(conn, testUser.getUserId());
		List<Product> prods = prodDao.retrieveByCart(conn, testCart.getCartId());
		saved.setProducts(prods);
		assertEqual(testCart, saved);
	}
		
	private void assertEqual(Cart a, Cart b) throws Exception {
		assertEquals(a.getCartId(), b.getCartId());
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
