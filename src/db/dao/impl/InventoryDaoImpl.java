package db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import db.dao.DaoException;
import db.dao.InventoryDao;
import domain.product.Product;
import domain.user.Inventory;

public class InventoryDaoImpl implements InventoryDao {

	private static final String createQuery = 
			"INSERT INTO "
			+ "INVENTORY (USERID) "
			+ "VALUES (?) ";
	
	private static final String retrieveQuery = 
			"SELECT "
			+ "INVNID, USERID "
			+ "FROM INVENTORY "
			+ "WHERE USERID = ? ";
	
	private static final String retireveProductsQuery = 
			"SELECT "
			+ "p.PRODID, p.NAME, p.DESCRIPTION, p.PRICE, p.ISSOLD "
			+ "FROM INVENTORY i "
			+ "JOIN INVENTORYPRODUCT ip ON i.INVNID = ip.INVNID "
			+ "JOIN PRODUCT p on ip.PRODID = p.PRODID "
			+ "WHERE i.USERID = ? ";
	
	private static final String addProductQuery = 
			"INSERT INTO "
			+ "INVENTORYPRODUCT (INVNID, PRODID) "
			+ "VALUES (?, ?) ";
			
	
	@Override
	public void create(Connection connection, Inventory inv, Integer userId) throws SQLException, DaoException {
		if(inv.getInvnId() != null) {
			throw new DaoException("InvnId must be null!");
		}
		if(userId == null) {
			throw new DaoException("UserId cannot be null!");
		}
		PreparedStatement statement = null;
		ResultSet rs = null;
		try 
		{	
			statement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, userId);
			statement.executeUpdate();
			rs = statement.getGeneratedKeys();
			rs.next();
			inv.setInvnId(rs.getInt(1));
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
	public Inventory retrieveByUser(Connection connection, Integer userId) throws SQLException, DaoException {
		if(userId == null)
		{
			throw new DaoException("UserId cannot be null!");
		}
		PreparedStatement statement = null;
		ResultSet rs = null;
		Inventory invn = null;
		try 
		{	
			statement = connection.prepareStatement(retrieveQuery);
			statement.setInt(1, userId);
			rs = statement.executeQuery();
			boolean found = rs.next();
			if(!found) {
				return null;
			}
			invn = new Inventory();
			invn.setInvnId(rs.getInt(1));
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
		
		statement = null;
		rs = null;
		try 
		{	
			ArrayList<Product> products = new ArrayList<Product>();
			statement = connection.prepareStatement(retireveProductsQuery);
			statement.setInt(1, userId);
			rs = statement.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setProdId(rs.getInt(1));
				product.setName(rs.getString(2));
				product.setDescription(rs.getString(3));
				product.setPrice(rs.getDouble(4));
				products.add(product);
			}
			invn.setProducts(products);
			
			return invn;
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
	public void addProduct(Connection connection, Integer prodId, Integer invnId) throws SQLException, DaoException {
		if(prodId == null) {
			throw new DaoException("ProdId cannot be null!");
		}
		if(invnId == null) {
			throw new DaoException("InvnId cannot be null!");
		}
		PreparedStatement statement = null;
		try 
		{	
			statement = connection.prepareStatement(addProductQuery);
			statement.setInt(1, invnId);
			statement.setInt(2, prodId);
			statement.executeUpdate();
		}
		finally
		{
			if (statement != null && !statement.isClosed()) {
				statement.close();
			}
		}
	}


}
