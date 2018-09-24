package db.services.impl;

import java.sql.Connection;
import java.sql.SQLException;

import db.DbManager;
import db.dao.DaoException;
import db.dao.InventoryDao;
import db.dao.PaintingDao;
import db.dao.ProductDao;
import db.dao.impl.InventoryDaoImpl;
import db.dao.impl.PaintingDaoImpl;
import db.dao.impl.ProductDaoImpl;
import db.services.PaintingPersistenceService;
import domain.product.Painting;

public class PaintingPersistenceServiceImpl implements PaintingPersistenceService {

	private DbManager db = new DbManager();
	private InventoryDao inventoryDao = new InventoryDaoImpl();
	private ProductDao prodDao = new ProductDaoImpl();
	private PaintingDao paintDao = new PaintingDaoImpl();
	
	@Override
	public void create(Painting painting, Integer invnId) throws SQLException, DaoException {
		Connection connection = db.getConnection();
		
		try 
		{
			connection.setAutoCommit(false);
			
			prodDao.create(connection, painting);
			paintDao.create(connection, painting);
			inventoryDao.addProduct(connection, painting.getProdId(), invnId);
			
			connection.commit();
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
