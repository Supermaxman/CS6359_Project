package test.services;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import db.services.PaintingPersistenceService;
import db.services.UserPersistenceService;
import db.services.impl.PaintingPersistenceServiceImpl;
import db.services.impl.UserPersistenceServiceImpl;
import domain.product.Painting;
import domain.user.User;
import test.utils.TestUtils;

public class PaintingPersistenceServiceTest {

	private UserPersistenceService userService = new UserPersistenceServiceImpl();
	private PaintingPersistenceService paintService = new PaintingPersistenceServiceImpl();
	private User testUser;
	private Painting testPaint;
	
	@Before
	public void setUp() throws Exception {
		testUser = TestUtils.generateUser();
		testPaint = TestUtils.generatePainting();
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
		paintService.create(testPaint, testUser.getInventory().getInvnId());
		Painting saved = paintService.retrieve(testPaint.getProdId());
		TestUtils.assertEqual(testPaint, saved);
	}
	
	@Test
	public void testRetrieveAll() throws Exception {
		List<Painting> prevPaintings = paintService.retrieveAll();
		paintService.create(testPaint, testUser.getInventory().getInvnId());
		List<Painting> paintings = paintService.retrieveAll();
		
		assertEquals(prevPaintings.size() + 1, paintings.size());
	}
	
		
}
