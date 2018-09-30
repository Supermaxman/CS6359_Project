package db.dao;

import java.sql.Connection;
import java.sql.SQLException;

import domain.user.Login;
import domain.user.User;

public interface UserDao {
	
	public void register(Connection conn, User user) throws SQLException, DaoException;
	
	public User validate(Connection conn, Login login) throws SQLException, DaoException;
	
	public User retrieve(Connection conn, Integer id) throws SQLException, DaoException;
	
	public int update(Connection conn, User user) throws SQLException, DaoException;
	
	
}

