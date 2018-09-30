package domain.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import db.DbManager;

public class PaintingDAO {

	public PaintingDAO() {
		super();
	}

	static Connection conn;
	static PreparedStatement ps;

	private static final String PRODUCT_QUERY = "SELECT PRODUCT.PRODID,NAME,DESCRIPTION, PRICE, ISSOLD, CANVASTYPE, PAINTTYPE, LENGTH, WIDTH FROM PRODUCT,PAINTING WHERE PRODUCT.PRODID = ?";

	public static Painting getPainting(String prodId) {
		DbManager db = new DbManager();
		Painting painting = new Painting();
		
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(PRODUCT_QUERY);
			ps.setInt(1, Integer.parseInt(prodId));
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				painting.setProdId(rs.getInt("PRODID"));
				painting.setName(rs.getString("NAME"));
				painting.setDescription(rs.getString("DESCRIPTION"));
				painting.setPrice(rs.getInt("PRICE"));
				//painting.setSeller(rs.getObject("SELLERID"));
				painting.setCanvasType(rs.getString("CANVASTYPE"));
				painting.setPaintType(rs.getString("PAINTTYPE"));
				painting.setLength(rs.getInt("LENGTH"));
				painting.setWidth(rs.getInt("WIDTH"));
					
					
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return painting;
	}

}
