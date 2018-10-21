package domain.user;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import domain.product.Product;
import domain.transaction.Transaction;

public class Cart {
	private Integer cartId;
	private List<Product> products;

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public void addProduct(Product product) {
		this.products.add(product);
	}
	
	public void removeProduct(int prodId)
	{
		Iterator<Product> prod = products.iterator();
		while (prod.hasNext()) {
		  Product product = prod.next();
		  if (product.getProdId().equals(prodId)) {
		   prod.remove();
		  }
		}
		
	}

	public Transaction checkout() {
		Transaction trxn = new Transaction();
		double totalPrice = 0.0;
		List<Product> trxnProds = new ArrayList<Product>();
		for (Product prod : this.products) {
			prod.setSold(true);
			trxnProds.add(prod);
			totalPrice += prod.getPrice();
		}
		trxn.setProducts(trxnProds);
		trxn.setPrice(totalPrice);
		trxn.setDate(new Date(System.currentTimeMillis()));
		this.products = new ArrayList<Product>();
		return trxn;
	}
	
}
