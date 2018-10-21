package domain.product;

import java.io.IOException;
import java.util.List;

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
import domain.user.Cart;

@WebServlet("/DetailsController")
public class DetailsController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CartPersistenceService cartService = new CartPersistenceServiceImpl();
	private PaintingPersistenceService paintService = new PaintingPersistenceServiceImpl();


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sess = request.getSession(true);
		Integer userId = (Integer) sess.getAttribute("userId");
		Integer prodId = Integer.parseInt(request.getParameter("prodId"));
		if(null != request.getParameter("ViewDetails"))
		{
			request.setAttribute("prodId", prodId);
			RequestDispatcher rs = request.getRequestDispatcher("productdetails.jsp");
			rs.forward(request, response);
		}
		else if(null!=request.getParameter("Remove")) {
		
			try {
				Cart cart = cartService.retrieve(userId);
				System.out.println(prodId);
				Painting prod = paintService.retrieve(prodId);
				cart.removeProduct(prodId);
				System.out.println(cart.getProducts());
				System.out.println(cartService.update(cart));
				// TODO determine if count added new product.
			} catch (Exception e) {
				System.out.println(e);
				// TODO return error msg.
			}

			//request.("prodId");
			RequestDispatcher rs = request.getRequestDispatcher("cart.jsp");
			rs.forward(request, response);
		}
		}

}