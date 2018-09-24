package db.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import db.dao.DaoException;
import db.dao.TransactionDao;
import src.domain.transaction.Transaction;

public class TransactionDaoImpl implements TransactionDao {

	@Override
	public void create(Connection connection, Transaction transaction, Integer userId) throws SQLException, DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Transaction retrieve(Connection connection, Integer id) throws SQLException, DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Connection connection, Transaction transaction, Integer userId) throws SQLException, DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Transaction> retrieveByUser(Connection connection, Integer userId) throws SQLException, DaoException {
		// TODO Auto-generated method stub
		return null;
	}

}
