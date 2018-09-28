package db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.dao.DaoException;
import db.dao.PaintingDao;
import domain.product.Painting;

public class PaintingDaoImpl implements PaintingDao {

	private static final String createQuery = 
			"INSERT INTO "
			+ "PAINTING (PRODID, CANVASTYPE, PAINTTYPE, LENGTH, WIDTH) "
			+ "VALUES (?, ?, ?, ?, ?) ";
	
	private static final String retrieveQuery = 
			"SELECT "
			+ "p.PRODID, p.NAME, p.DESCRIPTION, p.PRICE, p.ISSOLD, i.CANVASTYPE, i.PAINTTYPE, i.LENGTH, i.WIDTH "
			+ "FROM PRODUCT p "
			+ "JOIN PAINTING i ON p.PRODID = i.PRODID "
			+ "WHERE p.PRODID = ? ";

	private static final String retrieveAllQuery = 
			"SELECT "
			+ "p.PRODID, p.NAME, p.DESCRIPTION, p.PRICE, p.ISSOLD, i.CANVASTYPE, i.PAINTTYPE, i.LENGTH, i.WIDTH "
			+ "FROM PRODUCT p "
			+ "JOIN PAINTING i ON p.PRODID = i.PRODID ";

	
	@Override
	public void create(Connection connection, Painting product) throws SQLException, DaoException {
		if(product.getProdId() == null) {
			throw new DaoException("ProdId cannot be null!");
		}
		
		PreparedStatement statement = null;
		try 
		{	
			statement = connection.prepareStatement(createQuery);
			statement.setInt(1, product.getProdId());
			statement.setString(2, product.getCanvasType());
			statement.setString(3, product.getPaintType());
			statement.setDouble(4, product.getLength());
			statement.setDouble(5, product.getWidth());
			statement.executeUpdate();
		}
		finally
		{
			if (statement != null && !statement.isClosed()) {
				statement.close();
			}
		}
	}

	@Override
	public Painting retrieve(Connection connection, Integer prodId) throws SQLException, DaoException {
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
			Painting painting = buildPainting(rs);
			return painting;
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
	public List<Painting> retrieveAll(Connection connection) throws SQLException, DaoException {
		PreparedStatement statement = null;
		ResultSet rs = null;
		try 
		{	
			statement = connection.prepareStatement(retrieveAllQuery);
			rs = statement.executeQuery();
			ArrayList<Painting> paintings = new ArrayList<Painting>();
			
			while(rs.next())
			{
				Painting painting = buildPainting(rs);
				paintings.add(painting);
			}
			return paintings;
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
		
	private static Painting buildPainting(ResultSet rs) throws SQLException {
		//p.PRODID, p.NAME, p.DESCRIPTION, p.PRICE, p.ISSOLD, i.CANVASTYPE, i.PAINTTYPE, i.LENGTH, i.WIDTH 
		Painting painting = new Painting();
		painting.setProdId(rs.getInt(1));
		painting.setName(rs.getString(2));
		painting.setDescription(rs.getString(3));
		painting.setPrice(rs.getDouble(4));
		painting.setSold(rs.getBoolean(5));
		painting.setCanvasType(rs.getString(6));
		painting.setPaintType(rs.getString(7));
		painting.setLength(rs.getDouble(8));
		painting.setWidth(rs.getDouble(9));
		return painting;
	}


}
