package src.domain.transaction;

import java.util.ArrayList;
import java.util.Date;

import domain.product.Product;
import domain.user.User;

public class Transaction {
	private Integer trxnId;
	private User userId;
	private Date date;
	private double price;
	private ArrayList<Product> products;
	
	public Integer getTrxnId() {
		return trxnId;
	}
	public void setTrxnId(Integer trxnId) {
		this.trxnId = trxnId;
	}
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
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
