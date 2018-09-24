package db.services;

import java.sql.SQLException;
import java.util.List;

import db.dao.DaoException;
import domain.product.Product;

public interface ProductPersistenceService {

	public Product create(Product product) throws SQLException, DaoException;
	
	public Product retrieve(Integer id) throws SQLException, DaoException;
	
	public int update(Product product) throws SQLException, DaoException;
	
	public int delete(Integer id) throws SQLException, DaoException;
	
	public List<Product> retrieveBySeller(int category) throws SQLException, DaoException;
	
}
