package db.dao;

import java.sql.Connection;
import java.sql.SQLException;

import domain.product.Product;

public interface ProductCategoryDao<T extends Product>  {

	public void create(Connection connection, T product) throws SQLException, DaoException;
	
	public T retrieve(Connection connection, Integer prodId) throws SQLException, DaoException;
	
}
