package test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import db.DbManager;
import db.dao.ProductCategoryDao;
import db.dao.ProductDao;
import db.dao.UserDao;
import db.dao.impl.ProductDaoImpl;
import db.dao.impl.UserDaoImpl;
import domain.product.Product;
import domain.user.User;
import test.utils.TestUtils;

public abstract class AbstractProductCategoryDaoTest<T extends Product> {

	private DbManager db = DbManager.getInstance();
	private ProductCategoryDao<T> prodCatDao;
	private UserDao userDao = UserDaoImpl.getInstance();
	private ProductDao prodDao = ProductDaoImpl.getInstance();
	private Connection conn;
	private T testProdCat;
	private User testUser;
	int initialCount;
	
	public AbstractProductCategoryDaoTest(ProductCategoryDao<T> prodCatDao) {
		this.prodCatDao = prodCatDao;
	}

	protected abstract T generate() throws Exception;
	
	protected abstract void modify(T prod) throws Exception;
	
	@Before
	public void setUp() throws Exception {
		conn = db.getConnection();
		conn.setAutoCommit(false);
		
		testUser = TestUtils.generateUser();
		
		userDao.create(conn, testUser);
		
		testProdCat = generate();
		initialCount = prodCatDao.retrieveAll(conn).size();
		prodDao.create(conn, testProdCat);
		
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
		prodCatDao.create(conn, testProdCat);
		T saved = prodCatDao.retrieve(conn, testProdCat.getProdId());
		saved.setCategory(testProdCat.getCategory());
		TestUtils.assertEqual(testProdCat, saved);
	}
	
	@Test
	public void testRetrieveAll() throws Exception {
		prodCatDao.create(conn, testProdCat);
		
		List<T> saved = prodCatDao.retrieveAll(conn);
		assertEquals(saved.size(), initialCount + 1);
		int idx = TestUtils.Find(testProdCat, saved);
		assertTrue(idx != -1);
		
		saved.get(idx).setCategory(testProdCat.getCategory());
		TestUtils.assertEqual(testProdCat, saved.get(idx));
	}
	
	@Test
	public void testUpdate() throws Exception {
		prodCatDao.create(conn, testProdCat);
		modify(testProdCat);
		int count = prodCatDao.update(conn, testProdCat);
		assertEquals(count, 1);
		T saved = prodCatDao.retrieve(conn, testProdCat.getProdId());
		saved.setCategory(testProdCat.getCategory());
		TestUtils.assertEqual(testProdCat, saved);
	}

	@Test
	public void testDelete() throws Exception {
		prodCatDao.create(conn, testProdCat);
		int count = prodCatDao.delete(conn, testProdCat);
		assertEquals(count, 1);
		T saved = prodCatDao.retrieve(conn, testProdCat.getProdId());
		assertNull(saved);
	}
	
}
