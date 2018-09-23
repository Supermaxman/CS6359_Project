package db.dao;

import java.sql.ResultSet;

import domain.user.Login;
import domain.user.User;

public interface UserDao {


	public int register(User user);
	
	public User validate(Login login);

	public User buildUser(ResultSet rs);
	
}

