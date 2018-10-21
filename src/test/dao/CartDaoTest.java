package test.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
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
import test.utils.TestUtils;

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

		testProd = TestUtils.generateProduct();
		
		prodDao.create(conn, testProd);
		
		testUser = TestUtils.generateUser();
		
		userDao.register(conn, testUser);
				
		testCart = testUser.getCart();
				
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
		saved.setProducts(testCart.getProducts());
		TestUtils.assertEqual(testCart, saved);
	}
	
	@Test
	public void testUpdate() throws Exception {
		testDao.create(conn, testCart, testUser.getUserId());
		testCart.addProduct(testProd);
		int count = testDao.update(conn, testCart);
		assertEquals(count, 1);
		Cart saved = testDao.retrieveByUser(conn, testUser.getUserId());
		List<Product> prods = prodDao.retrieveByCart(conn, testCart.getCartId());
		for (Product prod : prods) {
			prod.setCategory(testProd.getCategory());
		}
		saved.setProducts(prods);
		TestUtils.assertEqual(testCart, saved);
	}

}
