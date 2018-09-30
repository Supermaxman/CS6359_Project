package domain.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import db.DbManager;



public class UserDaoImpl implements UserDao {

	private static final String validateQuery = 
			"SELECT USERID, USERNAME, PASSWORD, NAME, ADDRESS FROM USER WHERE USERNAME=? AND PASSWORD=?";
	
	private static final String registerQuery = 
			"INSERT INTO USER (USERNAME, PASSWORD, NAME, ADDRESS) VALUES (?, ?, ?, ?)";
	
	static Connection conn;
	static PreparedStatement ps;
	DbManager db = new DbManager();
	
	@Override
	public int register(User c) {
		int status = 0;
		try{
			conn = db.getConnection();
			ps = conn.prepareStatement(registerQuery, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, c.getUsername());
			ps.setString(2, c.getPassword());
			ps.setString(3, c.getName());
			ps.setString(4, c.getAddress());
			status = ps.executeUpdate();

			if (status == 1) {
				try {
					ResultSet key = ps.getGeneratedKeys();
					key.next();
					c.setUserId(key.getInt(1));
				} catch (SQLException e) {
					System.out.println(e);
				}
			}

			conn.close();
			
		}catch(Exception e){
			System.out.println(e);
		}
		
		return status;
	}

	@Override
	public User validate(Login login) {
		User c = new User();
		try{
			conn = db.getConnection();
			ps =conn.prepareStatement(validateQuery);
			ps.setString(1, login.getUsername());
			ps.setString(2, login.getPassword());

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				c.setUserId(Integer.parseInt(rs.getString(1)));
				c.setUsername(rs.getString(2));
				c.setPassword(rs.getString(3));
				c.setName(rs.getString(4));
				c.setAddress(rs.getString(5));
			}
			conn.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return c;
	}
	
	public User getUser(String userId) {
		
		return new User();
	}

}
