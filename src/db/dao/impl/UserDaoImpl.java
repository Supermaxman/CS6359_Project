package db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import db.dao.DaoException;
import db.dao.UserDao;
import domain.user.Login;
import domain.user.User;

public class UserDaoImpl implements UserDao {

	private static final String registerQuery = 
			"INSERT INTO "
			+ "USER (USERNAME, PASSWORD, NAME, ADDRESS) "
			+ "VALUES (?, ?, ?, ?) ";
	
	private static final String validateQuery = 
			"SELECT "
			+ "USERID, USERNAME, PASSWORD, NAME, ADDRESS "
			+ "FROM USER "
			+ "WHERE USERNAME = ? AND PASSWORD = ? ";

	private static final String retrieveQuery = 
			"SELECT "
			+ "USERID, USERNAME, PASSWORD, NAME, ADDRESS "
			+ "FROM USER "
			+ "WHERE USERID = ? ";
	
	private static final String updateQuery = 
			"UPDATE USER "
			+ "SET USERNAME = ?, "
			+ "PASSWORD = ?, "
			+ "NAME = ?, "
			+ "ADDRESS = ? "
			+ "WHERE USERID = ?";

	@Override
	public void register(Connection conn, User user) throws SQLException, DaoException {
		if (user.getUserId() != null) {
			throw new DaoException("UserId must be null on register!");
		}

		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement(registerQuery, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getName());
			statement.setString(4, user.getAddress());

			int result = statement.executeUpdate();
			if (result != 1) {
				throw new DaoException("User was unable to be created!");
			}

			ResultSet key = statement.getGeneratedKeys();
			key.next();
			user.setUserId(key.getInt(1));
		} finally {
			if (statement != null && !statement.isClosed()) {
				statement.close();
			}
		}
	}

	@Override
	public User validate(Connection conn, Login login) throws SQLException, DaoException {
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement(validateQuery);
			statement.setString(1, login.getUsername());
			statement.setString(2, login.getPassword());
			rs = statement.executeQuery();
			boolean found = rs.next();
			if (!found) {
				return null;
			}
			User user = buildUser(rs);
			return user;
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
	public User retrieve(Connection conn, Integer id) throws SQLException, DaoException {
		if (id == null) {
			throw new DaoException("UserId cannot be null!");
		}
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement(retrieveQuery);
			statement.setInt(1, id);
			rs = statement.executeQuery();
			boolean found = rs.next();
			if (!found) {
				return null;
			}
			User user = buildUser(rs);
			return user;
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
	public int update(Connection conn, User user) throws SQLException, DaoException {
		if (user.getUserId() == null) {
			throw new DaoException("UserId cannot be null!");
		}

		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement(updateQuery);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getName());
			statement.setString(4, user.getAddress());
			statement.setInt(5, user.getUserId());
			int result = statement.executeUpdate();
			if (result != 1) {
				throw new DaoException("Unable to update user!");
			}
			return result;
		} finally {
			if (statement != null && !statement.isClosed()) {
				statement.close();
			}
		}
	}

	private static User buildUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setUserId(rs.getInt(1));
		user.setUsername(rs.getString(2));
		user.setPassword(rs.getString(3));
		user.setName(rs.getString(4));
		user.setAddress(rs.getString(5));
		return user;
	}

}
