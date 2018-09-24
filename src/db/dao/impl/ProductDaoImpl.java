package db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.dao.DaoException;
import db.dao.ProductDao;
import domain.product.Product;

public class ProductDaoImpl implements ProductDao {

	private static final String createQuery = 
			"INSERT INTO "
			+ "PRODUCT (NAME, DESCRIPTION, PRICE, ISSOLD) "
			+ "VALUES (?, ?, ?, ?)";
		
	private static final String retrieveQuery = 
			"SELECT "
			+ "p.PRODID, p.NAME, p.DESCRIPTION, p.PRICE, p.ISSOLD "
			+ "FROM PRODUCT p "
			+ "WHERE p.PRODID = ? ";

	private static final String retrieveByTransactionQuery = 
			"SELECT "
			+ "p.PRODID, p.NAME, p.DESCRIPTION, p.PRICE, p.ISSOLD "
			+ "FROM PRODUCT p "
			+ "JOIN TRANSACTIONPRODUCT tp ON p.PRODID = tp.PRODID "
			+ "WHERE tp.TRXNID = ? ";
	
	private static final String retrieveBySellerQuery = 
			"SELECT "
			+ "p.PRODID, p.NAME, p.DESCRIPTION, p.PRICE, p.ISSOLD "
			+ "FROM PRODUCT p "
			+ "JOIN INVENTORYPRODUCT ip ON p.PRODID = ip.PRODID "
			+ "JOIN INVENTORY i ON ip.INVNID = i.INVNID "
			+ "WHERE i.USERID = ? ";
	
	@Override
	public void create(Connection connection, Product product) throws SQLException, DaoException {
		if(product.getProdId() != null) {
			throw new DaoException("ProdId must be null!");
		}
		
		PreparedStatement statement = null;
		ResultSet rs = null;
		try 
		{	
			statement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, product.getName());
			statement.setString(2, product.getDescription());
			statement.setDouble(3, product.getPrice());
			statement.setBoolean(4, product.isSold());
			statement.executeUpdate();
			rs = statement.getGeneratedKeys();
			rs.next();
			product.setProdId(rs.getInt(1));
		}
		finally
		{
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (statement != null && !statement.isClosed()) {
				statement.close();
			}
		}
	}

	@Override
	public Product retrieve(Connection connection, Integer prodId) throws SQLException, DaoException {
		if(prodId == null)
		{
			throw new DaoException("ProdId cannot be null!");
		}
		PreparedStatement statement = null;
		ResultSet rs = null;
		try 
		{	
			statement = connection.prepareStatement(retrieveQuery);
			statement.setInt(1, prodId);
			rs = statement.executeQuery();
			boolean found = rs.next();
			if(!found)
			{
				return null;
			}
			Product product = buildProduct(rs);
			return product;
		}
		finally
		{
			if (statement != null && !statement.isClosed()) 
			{
				statement.close();
			}
			if (rs != null && !rs.isClosed()) 
			{
				rs.close();
			}
		}
	}

	@Override
	public List<Product> retrieveByTransaction(Connection connection, Integer trxnId) throws SQLException, DaoException {
		if(trxnId == null)
		{
			throw new DaoException("TrxnId cannot be null!");
		}
		PreparedStatement statement = null;
		ResultSet rs = null;
		try 
		{	
			statement = connection.prepareStatement(retrieveByTransactionQuery);
			statement.setInt(1, trxnId);
			rs = statement.executeQuery();
			ArrayList<Product> products = new ArrayList<Product>();
			while (rs.next())
			{
				Product product = buildProduct(rs);
				products.add(product);
			}
			return products;
		}
		finally
		{
			if (statement != null && !statement.isClosed()) 
			{
				statement.close();
			}
			if (rs != null && !rs.isClosed()) 
			{
				rs.close();
			}
		}
	}

	@Override
	public List<Product> retrieveBySeller(Connection connection, Integer sellerId) throws SQLException, DaoException {
		if(sellerId == null)
		{
			throw new DaoException("SellerId cannot be null!");
		}
		PreparedStatement statement = null;
		ResultSet rs = null;
		try 
		{	
			statement = connection.prepareStatement(retrieveBySellerQuery);
			statement.setInt(1, sellerId);
			rs = statement.executeQuery();
			ArrayList<Product> products = new ArrayList<Product>();
			while (rs.next())
			{
				Product product = buildProduct(rs);
				products.add(product);
			}
			return products;
		}
		finally
		{
			if (statement != null && !statement.isClosed()) 
			{
				statement.close();
			}
			if (rs != null && !rs.isClosed()) 
			{
				rs.close();
			}
		}
	}


	private static Product buildProduct(ResultSet rs) throws SQLException {
		//p.PRODID, p.NAME, p.DESCRIPTION, p.PRICE, p.ISSOLD
		Product product = new Product();
		product.setProdId(rs.getInt(1));
		product.setName(rs.getString(2));
		product.setDescription(rs.getString(3));
		product.setPrice(rs.getDouble(4));
		product.setSold(rs.getBoolean(5));
		return product;
	}

}
