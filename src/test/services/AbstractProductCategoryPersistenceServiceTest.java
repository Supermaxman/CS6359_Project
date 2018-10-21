package test.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import db.services.ProductCategoryPersistenceService;
import db.services.UserPersistenceService;
import db.services.impl.UserPersistenceServiceImpl;
import domain.product.Product;
import domain.user.User;
import test.utils.TestUtils;

public abstract class AbstractProductCategoryPersistenceServiceTest<T extends Product> {

	private UserPersistenceService userService = new UserPersistenceServiceImpl();
	private ProductCategoryPersistenceService<T> prodCatService;
	private User testUser;
	private T testObj;
	
	public AbstractProductCategoryPersistenceServiceTest(ProductCategoryPersistenceService<T> prodCatService) {
		this.prodCatService = prodCatService;
	}
	
	protected abstract T generate() throws Exception;
	
	protected abstract void modify(T prod) throws Exception;
	
	@Before
	public void setUp() throws Exception {
		testUser = TestUtils.generateUser();
		testObj = generate();
		userService.register(testUser);
	}

	@After
	public void tearDown() throws Exception {
		//TODO add delete clean-up code when delete is implemented.
		//TODO for now just use DROP SCHEMA ARTKART; 
		//TODO and then re-create.
	}
	
	@Test
	public void testCreateRetrieve() throws Exception {
		prodCatService.create(testObj, testUser.getInventory().getInvnId());
		T saved = prodCatService.retrieve(testObj.getProdId());
		TestUtils.assertEqual(testObj, saved);
	}
	
	@Test
	public void testRetrieveAll() throws Exception {
		List<T> prevProdCats = prodCatService.retrieveAll();
		prodCatService.create(testObj, testUser.getInventory().getInvnId());
		List<T> prodCats = prodCatService.retrieveAll();
		
		assertEquals(prevProdCats.size() + 1, prodCats.size());
	}

	@Test
	public void testUpdate() throws Exception {
		prodCatService.create(testObj, testUser.getInventory().getInvnId());
		modify(testObj);
		int count = prodCatService.update(testObj);
		assertEquals(count, 1);
		T saved = prodCatService.retrieve(testObj.getProdId());
		TestUtils.assertEqual(testObj, saved);
	}
	

	@Test
	public void testDelete() throws Exception {
		prodCatService.create(testObj, testUser.getInventory().getInvnId());
		int count = prodCatService.delete(testObj);
		assertEquals(count, 1);
		T saved = prodCatService.retrieve(testObj.getProdId());
		assertNull(saved);
	}
	
}
