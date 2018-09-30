package domain.transaction;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.services.CartPersistenceService;
import db.services.TransactionPersistenceService;
import db.services.impl.CartPersistenceServiceImpl;
import db.services.impl.TransactionPersistenceServiceImpl;
import domain.product.Product;
import domain.user.Cart;

@WebServlet("/CheckoutController")
public class CheckoutController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private CartPersistenceService cartService = new CartPersistenceServiceImpl();
	private TransactionPersistenceService trxnService = new TransactionPersistenceServiceImpl();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sess = request.getSession(true);
		Integer userId = (Integer) sess.getAttribute("userId");
		
		try {
			Cart cart = cartService.retrieve(userId);
			Transaction trxn = new Transaction();
			double totalPrice = 0.0;
			List<Product> trxnProds = new ArrayList<Product>();
			for (Product prod : cart.getProducts()) {
				prod.setSold(true);
				trxnProds.add(prod);
				totalPrice += prod.getPrice();
			}
			trxn.setProducts(trxnProds);
			trxn.setPrice(totalPrice);
			trxn.setDate(new Date(System.currentTimeMillis()));
			cart.setProducts(new ArrayList<Product>());
			cartService.update(cart);
			trxnService.create(trxn, userId);
			
		} catch (Exception ex) {
			System.out.println(ex);
			// TODO return failure message
		}
		
		RequestDispatcher rs = request.getRequestDispatcher("complete.jsp");
		rs.forward(request, response);
	}

}