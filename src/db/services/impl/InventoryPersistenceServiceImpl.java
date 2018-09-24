package db.services.impl;

import java.sql.Connection;
import java.sql.SQLException;
import db.DbManager;
import db.dao.DaoException;
import db.dao.InventoryDao;
import db.dao.impl.InventoryDaoImpl;
import db.services.InventoryPersistenceService;
import domain.user.Inventory;

public class InventoryPersistenceServiceImpl implements InventoryPersistenceService {

	private DbManager db = new DbManager();
	private InventoryDao inventoryDao = new InventoryDaoImpl();
	
	
	@Override
	public Inventory retrieve(Integer userId) throws SQLException, DaoException {
		Connection connection = db.getConnection();
		
		try 
		{
			connection.setAutoCommit(false);
			Inventory invn = inventoryDao.retrieveByUser(connection, userId);
			connection.commit();
			return invn;
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
	public int update(Inventory inventory) throws SQLException, DaoException {
		Connection connection = db.getConnection();
		
		try 
		{
			connection.setAutoCommit(false);
			int updatedCount = inventoryDao.update(connection, inventory);
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
