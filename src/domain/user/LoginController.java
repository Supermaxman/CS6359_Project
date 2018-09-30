package domain.user;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.services.UserPersistenceService;
import db.services.impl.UserPersistenceServiceImpl;

/**
 * Servlet implementation class Login
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserPersistenceService userService = new UserPersistenceServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String pass = request.getParameter("password");
		String submitType = request.getParameter("submit");
		Login login = new Login(username, pass);
		User user = null;
		try {
			user = userService.validate(login);
		} catch (Exception ex) {
			System.out.println(ex);
		}

		if (submitType.equals("login") && user != null && user.getName() != null) {
			request.getSession().setAttribute("name", user.getName());
			request.getSession().setAttribute("userId", user.getUserId());
			request.getSession().setAttribute("invnId", user.getInventory().getInvnId());
			request.getSession().setAttribute("cartId", user.getCart().getCartId());
			request.getRequestDispatcher("home.jsp").forward(request, response);
		} else if (submitType.equals("register")) {
			user = new User();
			user.setUsername(request.getParameter("username"));
			user.setPassword(request.getParameter("password"));
			user.setName(request.getParameter("name"));
			user.setAddress(request.getParameter("address"));
			CreditCard card = new CreditCard();
			card.setNumber(request.getParameter("number"));
			card.setExpDate(Date.valueOf(request.getParameter("expdate")));
			card.setCcv(request.getParameter("ccv"));
			user.setCreditCard(card);
			String message = "Registration done, please login!";
			try {
				userService.register(user);
			} catch (Exception ex) {
				System.out.println(ex);
				message = "Unable to register user!";
			}
			request.setAttribute("successMessage", message);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "Data Not Found! Please register!");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}

	}

}
