package test.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import db.services.InventoryPersistenceService;
import db.services.PaintingPersistenceService;
import db.services.UserPersistenceService;
import db.services.impl.InventoryPersistenceServiceImpl;
import db.services.impl.PaintingPersistenceServiceImpl;
import db.services.impl.UserPersistenceServiceImpl;
import domain.product.Painting;
import domain.user.Inventory;
import domain.user.User;
import test.utils.TestUtils;

public class InventoryPersistenceServiceTest {

	private UserPersistenceService userService = UserPersistenceServiceImpl.getInstance();
	private InventoryPersistenceService invnService = InventoryPersistenceServiceImpl.getInstance();
	private PaintingPersistenceService paintService = PaintingPersistenceServiceImpl.getInstance();
	private User testUser;
	private Inventory testInvn;
	private Painting testPaint;
	
	@Before
	public void setUp() throws Exception {
		testUser = TestUtils.generateUser();
		userService.create(testUser);
		testInvn = testUser.getInventory();
		testPaint = TestUtils.generatePainting();
	}

	@After
	public void tearDown() throws Exception {
		//TODO add delete clean-up code when delete is implemented.
		//TODO for now just use DROP SCHEMA ARTKART; 
		//TODO and then re-create.
	}
	
	@Test
	public void testAddRetrieve() throws Exception {
		testInvn.addProduct(testPaint);
		paintService.create(testPaint, testInvn.getInvnId());
		
		Inventory saved = invnService.retrieve(testUser.getUserId());
		TestUtils.assertEqual(testUser.getInventory(), saved);
	}
		
}
