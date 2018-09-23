package db.dao;

import java.sql.ResultSet;

import domain.product.Product;

public interface ProductDao {

	public Product getProduct(Integer prodId);
	
	public Product updateProduct(Product prod);
	
	public Product buildProduct(ResultSet rs);
}
