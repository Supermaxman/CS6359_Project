package domain.user;

import java.util.Iterator;
import java.util.List;

import domain.product.Product;

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

}
