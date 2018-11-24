package domain.product;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DbManager;
import db.dao.DaoException;
import db.dao.impl.ProductDaoImpl;

@WebServlet("/SearchController")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDaoImpl prodAll = new ProductDaoImpl();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sess = request.getSession(true);
		Integer userId = (Integer) sess.getAttribute("userId");
		String searchCriteria = String.valueOf(sess.getAttribute("searchCriteria"));

		Connection connection;
		List<Product> products;
		ArrayList<Product> results = new ArrayList<>();
		
		try {
			connection = new DbManager().getConnection();
			products = prodAll.retrieveAll(connection);
			for (Product product : products) {
				if (!product.isSold() && (product.getName().contains(searchCriteria) || product.getDescription().contains(searchCriteria))) {
					results.add(product);
				}
			}
		} catch (SQLException | DaoException e) {
			e.printStackTrace();
		}

		request.setAttribute("searchResults", results);
		RequestDispatcher rs = request.getRequestDispatcher("searchresults.jsp");
		rs.forward(request, response);
	}

}