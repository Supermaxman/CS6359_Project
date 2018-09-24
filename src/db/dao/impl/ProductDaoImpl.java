package db.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import db.dao.DaoException;
import db.dao.ProductDao;
import domain.product.Product;
import domain.user.User;

public class ProductDaoImpl implements ProductDao {

	@Override
	public void create(Connection connection, Product product) throws SQLException, DaoException {

	}

	@Override
	public Product retrieve(Connection connection, Integer id) throws SQLException, DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User retrieveSeller(Connection connection, Product product) throws SQLException, DaoException {
		return null;
	}

	@Override
	public int update(Connection connection, Product product) throws SQLException, DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Product> retrieveByTransaction(Connection connection, Integer trxnId) throws SQLException, DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> retrieveBySeller(Connection connection, Integer sellerId) throws SQLException, DaoException {
		// TODO Auto-generated method stub
		return null;
	}


	

}
