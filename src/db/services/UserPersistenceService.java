package db.services;

import java.sql.SQLException;

import db.dao.DaoException;
import domain.user.Login;
import domain.user.User;

public interface UserPersistenceService {

	public void register(User user) throws SQLException, DaoException;
	
	public User retrieve(Integer id) throws SQLException, DaoException;
	
	public User validate(Login login) throws SQLException, DaoException;
}
