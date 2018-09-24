package db.dao;

import java.sql.Connection;
import java.sql.SQLException;

import domain.user.Inventory;

public interface InventoryDao {

	public void create(Connection connection, Inventory inv, Integer userId) throws SQLException, DaoException;
	
	public Inventory retrieveByUser(Connection connection, Integer userId) throws SQLException, DaoException;
	
	public int update(Connection connection, Inventory inv) throws SQLException, DaoException;
	
}
