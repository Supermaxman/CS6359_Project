package domain.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.services.CartPersistenceService;
import db.services.PaintingPersistenceService;
import db.services.impl.CartPersistenceServiceImpl;
import db.services.impl.PaintingPersistenceServiceImpl;
import domain.product.Painting;

@WebServlet("/CartController")
public class CartController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CartPersistenceService cartService = new CartPersistenceServiceImpl();
	private PaintingPersistenceService paintService = new PaintingPersistenceServiceImpl();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO determine best way to handle products other than paintings
		HttpSession sess = request.getSession(true);
		Integer userId = (Integer) sess.getAttribute("userId");
		Integer prodId = Integer.parseInt(request.getParameter("prodId"));
		try {
			Cart cart = cartService.retrieve(userId);
			Painting prod = paintService.retrieve(prodId);
			cart.addProduct(prod);
			cartService.update(cart);
			// TODO determine if count added new product.
		} catch (Exception e) {
			System.out.println(e);
			// TODO return error msg.
		}

		request.setAttribute("prodId", prodId);
		RequestDispatcher rs = request.getRequestDispatcher("cart.jsp");
		rs.forward(request, response);
	}

}