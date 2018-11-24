package test.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import db.services.CartPersistenceService;
import db.services.PaintingPersistenceService;
import db.services.UserPersistenceService;
import db.services.impl.CartPersistenceServiceImpl;
import db.services.impl.PaintingPersistenceServiceImpl;
import db.services.impl.UserPersistenceServiceImpl;
import domain.product.Painting;
import domain.user.Cart;
import domain.user.User;
import test.utils.TestUtils;

public class CartPersistenceServiceTest {

	private UserPersistenceService userService = UserPersistenceServiceImpl.getInstance();
	private CartPersistenceService cartService = CartPersistenceServiceImpl.getInstance();
	private PaintingPersistenceService paintService = PaintingPersistenceServiceImpl.getInstance();
	private User testUser;
	private Cart testCart;
	private Painting testPaint;
	
	@Before
	public void setUp() throws Exception {
		testUser = TestUtils.generateUser();
		userService.create(testUser);
		testCart = testUser.getCart();
		testPaint = TestUtils.generatePainting();
		paintService.create(testPaint, testUser.getInventory().getInvnId());
	}

	@After
	public void tearDown() throws Exception {
		//TODO add delete clean-up code when delete is implemented.
		//TODO for now just use DROP SCHEMA ARTKART; 
		//TODO and then re-create.
	}
	
	@Test
	public void testAddRetrieve() throws Exception {
		testCart.addProduct(testPaint);
		cartService.update(testCart);
		
		Cart saved = cartService.retrieve(testUser.getUserId());
		
		TestUtils.assertEqual(testUser.getCart(), saved);
	}
		
}
