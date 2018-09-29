package test.utils;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import domain.product.Painting;
import domain.product.Product;
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
		testProd.setDescription("Pretty!");
		testProd.setSold(false);
		return testProd;
	}

	@SuppressWarnings("deprecation")
	public static Transaction generateTransaction() throws Exception {

		Transaction testTrxn = new Transaction();
		testTrxn.setDate(new Date(2016, 1, 1));
		testTrxn.setPrice(100.0);
		testTrxn.setProducts(new ArrayList<Product>());
		return testTrxn;
	}
	
	public static User generateUser() throws Exception {
		User testUser = new User();
		testUser.setUsername(UUID.randomUUID().toString().substring(0, 16));
		testUser.setPassword(UUID.randomUUID().toString().substring(0, 10));
		testUser.setName(UUID.randomUUID().toString().substring(0, 10));
		testUser.setAddress(UUID.randomUUID().toString().substring(0, 10));
		testUser.setTransactions(new ArrayList<Transaction>());
		
		CreditCard testCard = generateCreditCard();
		testUser.setCreditCard(testCard);
		
		Product testProd = generateProduct();
		
		Inventory testInvn = new Inventory();
		testInvn.setProducts(new ArrayList<Product>());
		testInvn.addProduct(testProd);
		testUser.setInventory(testInvn);
		
		Cart testCart = new Cart();
		testCart.setProducts(new ArrayList<Product>());
		testCart.addProduct(testProd);
		testUser.setCart(testCart);
		
		Transaction testTrxn = generateTransaction();
		testTrxn.addProduct(testProd);
		testUser.addTransaction(testTrxn);
		
		return testUser;
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

		for (int i = 0; i < aTrxns.size(); i++) {
			assertEqual(aTrxns.get(i), bTrxns.get(i));
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
	
		assertEquals(aProds.size(), bProds.size());
		for (int i = 0; i < aProds.size(); i++) {
			assertEqual(aProds.get(i), bProds.get(i));
		}
	}
	
	public static void assertEqual(CreditCard a, CreditCard b)
	{
		assertEquals(a.getCardId(), b.getCardId());
		assertEquals(a.getExpDate(), b.getExpDate());
		assertEquals(a.getCcv(), b.getCcv());
	}

	public static Painting generatePainting() throws Exception {
		Painting testPaint = new Painting();
		testPaint.setName("Stary Night");
		testPaint.setDescription("Pretty!");
		testPaint.setSold(false);
		testPaint.setPrice(1000.0);
		testPaint.setCanvasType("Paper");
		testPaint.setPaintType("Oil");
		testPaint.setLength(15.0);
		testPaint.setWidth(10.0);
		return testPaint;
	}
}
