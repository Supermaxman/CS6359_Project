package db.dao;

import java.sql.ResultSet;

import domain.user.Cart;

public interface CartDao {
	
	public Cart getUserCart(Integer userId);
	
	public int updateCart(Cart cart);
	
	public Cart buildCart(ResultSet rs);
}
