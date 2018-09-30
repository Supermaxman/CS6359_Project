package db.services;

import java.sql.SQLException;

import db.dao.DaoException;
import domain.user.Cart;

public interface CartPersistenceService {

	public Cart retrieve(Integer userId) throws SQLException, DaoException;

	public int update(Cart cart) throws SQLException, DaoException;

}
