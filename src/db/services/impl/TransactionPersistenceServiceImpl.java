package db.services.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import db.DbManager;
import db.dao.DaoException;
import db.dao.ProductDao;
import db.dao.TransactionDao;
import db.dao.impl.ProductDaoImpl;
import db.dao.impl.TransactionDaoImpl;
import db.services.TransactionPersistenceService;
import domain.product.Product;
import domain.transaction.Transaction;

public class TransactionPersistenceServiceImpl implements TransactionPersistenceService {

	private DbManager db = new DbManager();
	private TransactionDao trxnDao = new TransactionDaoImpl();
	private ProductDao prodDao = new ProductDaoImpl();
	
	
	@Override
	public void create(Transaction trxn, Integer userId) throws SQLException, DaoException {
		Connection connection = db.getConnection();
		try 
		{
			connection.setAutoCommit(false);
			trxnDao.create(connection, trxn, userId);
			
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

	@Override
	public Transaction retrieve(Integer trxnId) throws SQLException, DaoException {
		Connection connection = db.getConnection();
		try 
		{
			connection.setAutoCommit(false);
			Transaction trxn = trxnDao.retrieve(connection, trxnId);
			
			List<Product> prods = prodDao.retrieveByTransaction(connection, trxnId);
			trxn.setProducts(prods);
			
			connection.commit();
			return trxn;
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
	public List<Transaction> retrieveByUser(Integer userId) throws SQLException, DaoException {
		Connection connection = db.getConnection();
		try 
		{
			connection.setAutoCommit(false);
			List<Transaction> trxns = trxnDao.retrieveByUser(connection, userId);
			
			for (Transaction trxn : trxns)
			{
				List<Product> prods = prodDao.retrieveByTransaction(connection, trxn.getTrxnId());
				trxn.setProducts(prods);
			}
						
			connection.commit();
			return trxns;
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
