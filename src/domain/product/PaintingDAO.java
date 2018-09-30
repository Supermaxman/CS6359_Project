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
	static PreparedStatement addProductStatement;
	static PreparedStatement addPaintingStatement;

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
				// painting.setSeller(rs.getInt("SELLERID"));
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

	private static final String ADD_PRODUCT_QUERY = "INSERT INTO PRODUCT (NAME, DESCRIPTION, PRICE, SELLERID, ISSOLD ) VALUES (?, ?, ?, ?, 0)";
	private static final String ADD_PAINTING_QUERY = "INSERT INTO PRODUCT (NAME, DESCRIPTION, PRICE, SELLERID, ISSOLD ) VALUES (?, ?, ?, ?, 0)";

	public static boolean setPanting(Painting painting) {
		DbManager db = new DbManager();
		boolean status = true;
		try {
			conn = db.getConnection();
			addProductStatement = conn.prepareStatement(ADD_PRODUCT_QUERY, Statement.RETURN_GENERATED_KEYS);
			addProductStatement.setString(1, painting.getName());
			addProductStatement.setString(2, painting.getDescription());
			addProductStatement.setDouble(3, painting.getPrice());
			addProductStatement.setInt(4, painting.getSeller().getUserId());

			addProductStatement.executeQuery();
			ResultSet key = addProductStatement.getGeneratedKeys();
			key.next();
			int productId = key.getInt(1);

			addPaintingStatement = conn.prepareStatement(ADD_PAINTING_QUERY);
			addPaintingStatement.setInt(1, productId);
			addPaintingStatement.setString(2, painting.getCanvasType());
			addPaintingStatement.setString(3, painting.getPaintType());
			addPaintingStatement.setDouble(4, painting.getLength());
			addPaintingStatement.setDouble(5, painting.getWidth());

			addProductStatement.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
			return status;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return status;
	}
}
