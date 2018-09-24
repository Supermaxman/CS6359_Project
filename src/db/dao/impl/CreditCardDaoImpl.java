package db.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import db.dao.CreditCardDao;
import db.dao.DaoException;
import domain.user.CreditCard;

public class CreditCardDaoImpl implements CreditCardDao {

	@Override
	public CreditCard create(Connection connection, CreditCard creditCard, Integer userId) throws SQLException, DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreditCard retrieveByUser(Connection connection, Integer userId) throws SQLException, DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Connection connection, CreditCard creditCard) throws SQLException, DaoException {
		// TODO Auto-generated method stub
		return 0;
	}


}
