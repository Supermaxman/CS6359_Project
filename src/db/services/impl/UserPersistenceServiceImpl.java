package db.services.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DbManager;
import db.dao.CartDao;
import db.dao.CreditCardDao;
import db.dao.DaoException;
import db.dao.InventoryDao;
import db.dao.ProductDao;
import db.dao.TransactionDao;
import db.dao.UserDao;
import db.dao.impl.CartDaoImpl;
import db.dao.impl.CreditCardDaoImpl;
import db.dao.impl.InventoryDaoImpl;
import db.dao.impl.ProductDaoImpl;
import db.dao.impl.TransactionDaoImpl;
import db.dao.impl.UserDaoImpl;
import db.services.UserPersistenceService;
import domain.product.Product;
import domain.user.Cart;
import domain.user.CreditCard;
import domain.user.Inventory;
import domain.user.Login;
import domain.user.User;
import src.domain.transaction.Transaction;

public class UserPersistenceServiceImpl implements UserPersistenceService {

	private DbManager db = new DbManager();
	private UserDao userDao = new UserDaoImpl();
	private CreditCardDao creditCardDao = new CreditCardDaoImpl();
	private InventoryDao inventoryDao = new InventoryDaoImpl();
	private CartDao cartDao = new CartDaoImpl();
	private TransactionDao trxnDao = new TransactionDaoImpl();
	private ProductDao prodDao = new ProductDaoImpl();
	
	@Override
	public void register(User user) throws SQLException, DaoException {
		if (user.getAddress() == null) 
		{
			throw new DaoException("Address cannot be null!");
		}
		Connection connection = db.getConnection();
		
		try 
		{
			connection.setAutoCommit(false);
			userDao.register(connection, user);
			Integer userId = user.getUserId();
			
			Inventory inventory = new Inventory();
			user.setInventory(inventory);
			inventoryDao.create(connection, inventory, userId);
			inventory.setProducts(new ArrayList<Product>());
			
			Cart cart = new Cart();
			user.setCart(cart);
			cartDao.create(connection, cart, userId);
			cart.setProducts(new ArrayList<Product>());
						
			CreditCard creditCard = user.getCreditCard();
			creditCardDao.create(connection, creditCard, userId);
			
			user.setTransactions(new ArrayList<Transaction>());
			
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
	public User retrieve(Integer id) throws SQLException, DaoException {

		Connection connection = db.getConnection();
		try 
		{
			connection.setAutoCommit(false);
			User user = userDao.retrieve(connection, id);

			buildUser(connection, user);
			
			connection.commit();
			return user;
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
	
	
	public User validate(Login login) throws SQLException, DaoException {
		Connection connection = db.getConnection();
		
		try 
		{
			connection.setAutoCommit(false);
			User user = userDao.validate(connection, login);
			
			buildUser(connection, user);
			
			connection.commit();
			return user;
		}
		catch (Exception ex) 
		{
			connection.rollback();
			return null;
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

	
	private void buildUser(Connection connection, User user) throws SQLException, DaoException
	{
		int userId = user.getUserId();
		Inventory inventory = inventoryDao.retrieveByUser(connection, userId);
		user.setInventory(inventory);
		
		Cart cart = cartDao.retrieveByUser(connection, userId);
		user.setCart(cart);
		
		CreditCard creditCard = creditCardDao.retrieveByUser(connection, userId);
		user.setCreditCard(creditCard);
		
		List<Transaction> transactions = trxnDao.retrieveByUser(connection, userId);
		for (Transaction trxn : transactions)
		{
			List<Product> trxnProds = prodDao.retrieveByTransaction(connection, trxn.getTrxnId());
			trxn.setProducts(trxnProds);
		}
		
		user.setTransactions(transactions);
		
	}
	
}
