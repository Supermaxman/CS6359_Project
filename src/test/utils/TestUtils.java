package test.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import db.services.impl.CartPersistenceServiceImpl;
import db.services.impl.CategoryPersistenceServiceImpl;
import db.services.impl.CraftPersistenceServiceImpl;
import db.services.impl.InventoryPersistenceServiceImpl;
import db.services.impl.PaintingPersistenceServiceImpl;
import db.services.impl.SculpturePersistenceServiceImpl;
import db.services.impl.TransactionPersistenceServiceImpl;
import db.services.impl.UserPersistenceServiceImpl;
import domain.product.Category;
import domain.product.Craft;
import domain.product.Painting;
import domain.product.Product;
import domain.product.Sculpture;
import domain.transaction.Transaction;
import domain.user.Cart;
import domain.user.CreditCard;
import domain.user.Inventory;
import domain.user.User;

public class TestUtils {
		
	@SuppressWarnings("deprecation")
	public static CreditCard generateCreditCard() throws Exception {
		CreditCard testCard = new CreditCard();
		testCard.setNumber("1234");
		testCard.setExpDate(new Date(2019, 1, 1));
		testCard.setCcv("111");
		return testCard;
	}
	
	public static Product generateProduct() throws Exception {
		Product testProd = new Product();
		testProd.setName("Stary Night");
		Category testCat = CategoryPersistenceServiceImpl.getInstance().getCategory();
		testCat.setCatId(1);
		testCat.setName("Painting");
		testCat.setDescription("");
		testProd.setCategory(testCat);
		testProd.setDescription("Pretty!");
		testProd.setSold(false);
		BufferedImage bufimage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		testProd.setImage(bufimage);
		return testProd;
	}

	@SuppressWarnings("deprecation")
	public static Transaction generateTransaction() throws Exception {

		Transaction testTrxn = TransactionPersistenceServiceImpl.getInstance().getTransaction();
		testTrxn.setDate(new Date(2016, 1, 1));
		testTrxn.setPrice(100.0);
		testTrxn.setProducts(new ArrayList<Product>());
		return testTrxn;
	}

	public static Painting generatePainting() throws Exception {
		Painting test = PaintingPersistenceServiceImpl.getInstance().getProd();
		test.setName("Stary Night");
		test.setDescription("Pretty!");
		test.setSold(false);
		test.setPrice(1000.0);
		test.setCanvasType("Paper");
		test.setPaintType("Oil");
		test.setLength(15.0);
		test.setWidth(10.0);
		Category testCat = CategoryPersistenceServiceImpl.getInstance().getCategory();
		testCat.setCatId(1);
		testCat.setName("Painting");
		testCat.setDescription("");
		test.setCategory(testCat);
		BufferedImage bufimage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		test.setImage(bufimage);
		return test;
	}
	
	public static Sculpture generateSculpture() throws Exception {
		Sculpture test = SculpturePersistenceServiceImpl.getInstance().getProd();
		test.setName("David");
		test.setDescription("Tall!");
		test.setSold(false);
		test.setPrice(2000.0);
		test.setHeight(36.0);
		test.setLength(12.0);
		test.setWidth(24.0);
		test.setMaterial("Stone");
		test.setWeight(1000.0);
		Category testCat = CategoryPersistenceServiceImpl.getInstance().getCategory();
		testCat.setCatId(2);
		testCat.setName("Sculpture");
		testCat.setDescription("");
		test.setCategory(testCat);
		BufferedImage bufimage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		test.setImage(bufimage);
		return test;
	}

	public static Craft generateCraft() throws Exception {
		Craft test = CraftPersistenceServiceImpl.getInstance().getProd();
		test.setName("Bracelet");
		test.setDescription("Cool!");
		test.setSold(false);
		test.setPrice(30.0);
		test.setHeight(5.0);
		test.setLength(6.0);
		test.setWidth(4.0);
		test.setUsage("Wearable");
		Category testCat = CategoryPersistenceServiceImpl.getInstance().getCategory();
		testCat.setCatId(3);
		testCat.setName("Craft");
		testCat.setDescription("");
		test.setCategory(testCat);
		BufferedImage bufimage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		test.setImage(bufimage);
		return test;
	}
	
	public static User generateUser() throws Exception {
		User testUser = UserPersistenceServiceImpl.getInstance().getUser();
		testUser.setUsername(UUID.randomUUID().toString().substring(0, 16));
		testUser.setPassword(UUID.randomUUID().toString().substring(0, 10));
		testUser.setName(UUID.randomUUID().toString().substring(0, 10));
		testUser.setAddress(UUID.randomUUID().toString().substring(0, 10));
		testUser.setDescription(UUID.randomUUID().toString());
		testUser.setTransactions(new ArrayList<Transaction>());
		
		CreditCard testCard = generateCreditCard();
		testUser.setCreditCard(testCard);
		
		
		Inventory testInvn = InventoryPersistenceServiceImpl.getInstance().getInventory();
		testInvn.setProducts(new ArrayList<Product>());
		testUser.setInventory(testInvn);
		
		Cart testCart = CartPersistenceServiceImpl.getInstance().getCart();
		testCart.setProducts(new ArrayList<Product>());
		testUser.setCart(testCart);
				
		return testUser;
	}
	
	public static boolean assertReferenceCheck(Object a, Object b) throws Exception 
	{
		if (a == null || b == null) {
			assertTrue(a == b);
			return false;
		}
		else {
			return true;
		}
	}
	
	
	public static void assertEqual(User a, User b) throws Exception
	{
		assertEquals(a.getUserId(), b.getUserId());
		assertEquals(a.getUsername(), b.getUsername());
		assertEquals(a.getPassword(), b.getPassword());
		assertEquals(a.getName(), b.getName());
		assertEquals(a.getAddress(), b.getAddress());

		assertEqual(a.getCart(), b.getCart());
		assertEqual(a.getInventory(), b.getInventory());
		assertEqual(a.getCreditCard(), b.getCreditCard());
		
		List<Transaction> aTrxns = a.getTransactions();
		List<Transaction> bTrxns = b.getTransactions();
		
		if (assertReferenceCheck(aTrxns, bTrxns)) {
			assertEquals(aTrxns.size(), bTrxns.size());
			for (int i = 0; i < aTrxns.size(); i++) {
				assertEqual(aTrxns.get(i), bTrxns.get(i));
			}
		}
		
	}

	public static void assertEqual(Painting a, Painting b) throws Exception {
		assertEquals(a.getProdId(), b.getProdId());
		assertEquals(a.getName(), b.getName());
		assertEquals(a.getDescription(), b.getDescription());
		assertEquals(a.isSold(), b.isSold());
		assertEquals(a.getPrice(), b.getPrice(), 0.001);
		assertEquals(a.getPaintType(), b.getPaintType());
		assertEquals(a.getCanvasType(), b.getCanvasType());
		assertEquals(a.getLength(), b.getLength(), 0.001);
		assertEquals(a.getWidth(), b.getWidth(), 0.001);
	}
	
	public static void assertEqual(Product a, Product b) throws Exception {
		assertEquals(a.getProdId(), b.getProdId());
		if (assertReferenceCheck(a.getCategory(), b.getCategory())) {
			assertEqual(a.getCategory(), b.getCategory());
		}
		assertEquals(a.getName(), b.getName());
		assertEquals(a.getDescription(), b.getDescription());
		assertEquals(a.isSold(), b.isSold());
		assertEquals(a.getPrice(), b.getPrice(), 0.001);
	}
	
	public static void assertEqual(Transaction a, Transaction b)
	{
		assertEquals(a.getTrxnId(), b.getTrxnId());
		assertEquals(a.getDate(), b.getDate());
		assertEquals(a.getPrice(), b.getPrice(), 0.001);
	}

	public static void assertEqual(Inventory a, Inventory b) throws Exception {
		assertEquals(a.getInvnId(), b.getInvnId());
		List<Product> aProds = a.getProducts();
		List<Product> bProds = b.getProducts();
		
		assertEquals(aProds.size(), bProds.size());
		for (int i = 0; i < aProds.size(); i++) {
			assertEqual(aProds.get(i), bProds.get(i));
		}
	}

	public static void assertEqual(Cart a, Cart b) throws Exception {
		assertEquals(a.getCartId(), b.getCartId());
		List<Product> aProds = a.getProducts();
		List<Product> bProds = b.getProducts();
		if (assertReferenceCheck(aProds, bProds)) {
			assertEquals(aProds.size(), bProds.size());
			for (int i = 0; i < aProds.size(); i++) {
				assertEqual(aProds.get(i), bProds.get(i));
			}
		}
	}
	
	public static void assertEqual(CreditCard a, CreditCard b)
	{
		assertEquals(a.getCardId(), b.getCardId());
		assertEquals(a.getExpDate(), b.getExpDate());
		assertEquals(a.getCcv(), b.getCcv());
	}
	
	public static void assertEqual(Category a, Category b)
	{
		assertEquals(a.getCatId(), b.getCatId());
		assertEquals(a.getName(), b.getName());
		assertEquals(a.getDescription(), b.getDescription());
	}
	
	public static int Find(Product prod, List<? extends Product> prods) {
		int idx = 0;
		for (Product p : prods) {
			if (p.getProdId().equals(prod.getProdId())) {
				return idx;
			}
			idx++;
		}
		return -1;
	}
	
}
