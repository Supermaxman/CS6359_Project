package db.dao;

import java.sql.Connection;
import java.sql.SQLException;

import domain.user.CreditCard;

public interface CreditCardDao {
	
	CreditCard create(Connection connection, CreditCard creditCard, Integer userId) throws SQLException, DaoException;
	
	CreditCard retrieveByUser(Connection connection, Integer userId) throws SQLException, DaoException;
	
	public int update(Connection connection, CreditCard creditCard) throws SQLException, DaoException;
	
}
