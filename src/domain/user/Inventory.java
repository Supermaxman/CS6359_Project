package domain.user;

import java.util.ArrayList;

import domain.product.Product;

public class Inventory {
	private Integer invnId;
	private User user;
	private ArrayList<Product> products;
	
	public Integer getInvnId() {
		return invnId;
	}
	public void setInvnId(Integer invnId) {
		this.invnId = invnId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ArrayList<Product> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	
	public void addProduct(Product product) {
		this.products.add(product);
	}
	
	
}
