package db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.dao.DaoException;
import db.dao.SculptureDao;
import domain.product.Sculpture;

public class SculptureDaoImpl implements SculptureDao {

	private static final String createQuery = 
			"INSERT INTO "
			+ "SCULPTURE (PRODID, MATERIAL, WEIGHT, LENGTH, WIDTH, HEIGHT) "
			+ "VALUES (?, ?, ?, ?, ?, ?) ";

	private static final String retrieveQuery = 
			"SELECT "
			+ "p.PRODID, p.NAME, p.DESCRIPTION, p.PRICE, p.ISSOLD, i.MATERIAL, i.WEIGHT, i.LENGTH, i.WIDTH, i.HEIGHT "
			+ "FROM PRODUCT p "
			+ "JOIN SCULPTURE i ON p.PRODID = i.PRODID "
			+ "WHERE p.PRODID = ? ";

	private static final String retrieveAllQuery = 
			"SELECT "
			+ "p.PRODID, p.NAME, p.DESCRIPTION, p.PRICE, p.ISSOLD, i.MATERIAL, i.WEIGHT, i.LENGTH, i.WIDTH, i.HEIGHT "
			+ "FROM PRODUCT p "
			+ "JOIN SCULPTURE i ON p.PRODID = i.PRODID ";

	private static final String updateQuery = 
			"UPDATE "
			+ "SCULPTURE "
			+ "SET MATERIAL = ?, WEIGHT = ?, LENGTH = ?, WIDTH = ?, HEIGHT = ? "
			+ "WHERE PRODID = ? ";

	private static final String deleteQuery = 
			"DELETE FROM "
			+ "SCULPTURE "
			+ "WHERE PRODID = ? ";
	
	@Override
	public void create(Connection connection, Sculpture product) throws SQLException, DaoException {
		if (product.getProdId() == null) {
			throw new DaoException("ProdId cannot be null!");
		}

		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(createQuery);
			statement.setInt(1, product.getProdId());
			statement.setString(2, product.getMaterial());
			statement.setDouble(3, product.getWeight());
			statement.setDouble(4, product.getLength());
			statement.setDouble(5, product.getWidth());
			statement.setDouble(6, product.getHeight());
			statement.executeUpdate();
		} finally {
			if (statement != null && !statement.isClosed()) {
				statement.close();
			}
		}
	}

	@Override
	public Sculpture retrieve(Connection connection, Integer prodId) throws SQLException, DaoException {
		if (prodId == null) {
			throw new DaoException("ProdId cannot be null!");
		}
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.prepareStatement(retrieveQuery);
			statement.setInt(1, prodId);
			rs = statement.executeQuery();
			boolean found = rs.next();
			if (!found) {
				return null;
			}
			Sculpture painting = buildSculpture(rs);
			return painting;
		} finally {
			if (statement != null && !statement.isClosed()) {
				statement.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		}
	}

	@Override
	public List<Sculpture> retrieveAll(Connection connection) throws SQLException, DaoException {
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.prepareStatement(retrieveAllQuery);
			rs = statement.executeQuery();
			ArrayList<Sculpture> paintings = new ArrayList<Sculpture>();

			while (rs.next()) {
				Sculpture painting = buildSculpture(rs);
				paintings.add(painting);
			}
			return paintings;
		} finally {
			if (statement != null && !statement.isClosed()) {
				statement.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		}
	}

	@Override
	public int update(Connection connection, Sculpture product) throws SQLException, DaoException {
		if (product.getProdId() == null) {
			throw new DaoException("ProdId cannot be null!");
		}

		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(updateQuery);
			statement.setString(1, product.getMaterial());
			statement.setDouble(2, product.getWeight());
			statement.setDouble(3, product.getLength());
			statement.setDouble(4, product.getWidth());
			statement.setDouble(5, product.getHeight());
			statement.setInt(6, product.getProdId());
			int result = statement.executeUpdate();
			if (result != 1) {
				throw new DaoException("Unable to update product!");
			}
			return result;
		} finally {
			if (statement != null && !statement.isClosed()) {
				statement.close();
			}
		}
	}

	@Override
	public int delete(Connection connection, Sculpture product) throws SQLException, DaoException {
		if (product.getProdId() == null) {
			throw new DaoException("ProdId cannot be null!");
		}

		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(deleteQuery);
			statement.setInt(1, product.getProdId());
			int result = statement.executeUpdate();
			if (result != 1) {
				throw new DaoException("Unable to delete product!");
			}
			return result;
		} finally {
			if (statement != null && !statement.isClosed()) {
				statement.close();
			}
		}
	}

	private static Sculpture buildSculpture(ResultSet rs) throws SQLException {		
		Sculpture sculpture = new Sculpture();
		sculpture.setProdId(rs.getInt(1));
		sculpture.setName(rs.getString(2));
		sculpture.setDescription(rs.getString(3));
		sculpture.setPrice(rs.getDouble(4));
		sculpture.setSold(rs.getBoolean(5));
		sculpture.setMaterial(rs.getString(6));
		sculpture.setWeight(rs.getDouble(7));
		sculpture.setLength(rs.getDouble(8));
		sculpture.setWidth(rs.getDouble(9));
		sculpture.setHeight(rs.getDouble(10));
		return sculpture;
	}
	
}
