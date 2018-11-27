package test.services;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import db.services.PaintingPersistenceService;
import db.services.TransactionPersistenceService;
import db.services.UserPersistenceService;
import db.services.impl.PaintingPersistenceServiceImpl;
import db.services.impl.TransactionPersistenceServiceImpl;
import db.services.impl.UserPersistenceServiceImpl;
import domain.product.Painting;
import domain.transaction.Transaction;
import domain.user.User;
import test.utils.TestUtils;

public class TransactionPersistenceServiceTest {

	private UserPersistenceService userService = UserPersistenceServiceImpl.getInstance();
	private TransactionPersistenceService trxnService = TransactionPersistenceServiceImpl.getInstance();
	private PaintingPersistenceService paintService = PaintingPersistenceServiceImpl.getInstance();
	private User testUser;
	private Transaction testTrxn;
	private Painting testPaint;
	
	@Before
	public void setUp() throws Exception {
		testUser = TestUtils.generateUser();
		testTrxn = TestUtils.generateTransaction();
		userService.create(testUser);
		testPaint = TestUtils.generatePainting();
		paintService.create(testPaint, testUser.getInventory().getInvnId());
		testTrxn.addProduct(testPaint);
	}

	@After
	public void tearDown() throws Exception {
		//TODO add delete clean-up code when delete is implemented.
		//TODO for now just use DROP SCHEMA ARTKART; 
		//TODO and then re-create.
	}
	
	@Test
	public void testCreateRetrieve() throws Exception {
		trxnService.create(testTrxn, testUser.getUserId());
		
		Transaction saved = trxnService.retrieve(testTrxn.getTrxnId());
		
		TestUtils.assertEqual(testTrxn, saved);
	}

	@Test
	public void testRetrieveByUser() throws Exception {
		trxnService.create(testTrxn, testUser.getUserId());
		
		List<Transaction> saved = trxnService.retrieveByUser(testUser.getUserId());
		
		assertEquals(saved.size(), 1);
		
		TestUtils.assertEqual(testTrxn, saved.get(0));
	}
}
