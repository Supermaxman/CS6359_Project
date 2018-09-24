package db.services.impl;

import java.sql.Connection;
import java.sql.SQLException;
import db.DbManager;
import db.dao.CartDao;
import db.dao.DaoException;
import db.dao.impl.CartDaoImpl;
import db.services.CartPersistenceService;
import domain.user.Cart;

public class CartPersistenceServiceImpl implements CartPersistenceService {

	private DbManager db = new DbManager();
	private CartDao cartDao = new CartDaoImpl();
	
	
	@Override
	public Cart retrieve(Integer userId) throws SQLException, DaoException {
		Connection connection = db.getConnection();
		
		try 
		{
			connection.setAutoCommit(false);
			Cart cart = cartDao.retrieveByUser(connection, userId);
			connection.commit();
			return cart;
		}
		catch (Exception ex) 
		{
			connection.rollback();
			throw ex;
		}
		finally 
		{
			if (connection != null) 
			{
				connection.setAutoCommit(true);
				if (!connection.isClosed())
				{
					connection.close();
				}
			}
		}
	}

	
	@Override
	public int update(Cart cart) throws SQLException, DaoException {
		Connection connection = db.getConnection();
		
		try 
		{
			connection.setAutoCommit(false);
			int updatedCount = cartDao.update(connection, cart);
			connection.commit();
			return updatedCount;
		}
		catch (Exception ex) 
		{
			connection.rollback();
			throw ex;
		}
		finally 
		{
			if (connection != null) 
			{
				connection.setAutoCommit(true);
				if (!connection.isClosed())
				{
					connection.close();
				}
			}
		}
	}

}
