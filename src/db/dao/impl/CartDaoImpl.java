package db.dao.impl;

import java.sql.ResultSet;

import db.dao.CartDao;
import domain.user.Cart;

public class CartDaoImpl implements CartDao {

	@Override
	public Cart getUserCart(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateCart(Cart cart) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public Cart buildCart(ResultSet rs) {
		Cart cart;
		try {
			cart = new Cart();
			cart.setCartId(Integer.parseInt(rs.getString(1)));
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		return cart;
	}

}
