package db.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


import domain.product.Product;
import domain.user.User;

public interface ProductDao {

	public void create(Connection connection, Product product) throws SQLException, DaoException;
	
	public Product retrieve(Connection connection, Integer id) throws SQLException, DaoException;
	
	public int update(Connection connection, Product product) throws SQLException, DaoException;
	
	public List<Product> retrieveByTransaction(Connection connection, Integer trxnId) throws SQLException, DaoException;
	
	public List<Product> retrieveBySeller(Connection connection, Integer sellerId) throws SQLException, DaoException;

	public User retrieveSeller(Connection connection, Product product) throws SQLException, DaoException;
	
}
