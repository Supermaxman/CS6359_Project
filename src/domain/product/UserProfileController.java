package domain.product;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.dao.DaoException;
import db.services.CartPersistenceService;
import db.services.CraftPersistenceService;
import db.services.PaintingPersistenceService;
import db.services.SculpturePersistenceService;
import db.services.UserPersistenceService;
import db.services.impl.CartPersistenceServiceImpl;
import db.services.impl.CraftPersistenceServiceImpl;
import db.services.impl.PaintingPersistenceServiceImpl;
import db.services.impl.SculpturePersistenceServiceImpl;
import db.services.impl.UserPersistenceServiceImpl;
import domain.user.Cart;
import domain.user.User;

@WebServlet("/UserProfileController")
public class UserProfileController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CartPersistenceService cartService = new CartPersistenceServiceImpl();
	private PaintingPersistenceService paintService = new PaintingPersistenceServiceImpl();
	private SculpturePersistenceService sculptService = new SculpturePersistenceServiceImpl();
	private CraftPersistenceService craftService = new CraftPersistenceServiceImpl();

	UserPersistenceService userService = new UserPersistenceServiceImpl();
	User user = null;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sess = request.getSession(true);
		//Integer userId = (Integer) sess.getAttribute("userId");
		Integer prodId = Integer.parseInt(request.getParameter("prodId"));
		try {
			user = userService.retrieveByProduct(prodId);
		} catch (SQLException | DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//userService.create(user);
		//Integer catId = Integer.parseInt(request.getParameter("catId"));
		
		if(null != request.getParameter("UserProfile"))
		{
			//request.setAttribute("userDescription", user.getDescription());
			request.setAttribute("userId", user.getUserId());
			//request.setAttribute("catId", catId);
			RequestDispatcher rs = request.getRequestDispatcher("userprofile.jsp");
			rs.forward(request, response);
		}
		

		}

}