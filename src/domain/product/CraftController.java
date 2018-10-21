package domain.product;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.services.CraftPersistenceService;

/**
 * Servlet implementation class CraftController
 */
@WebServlet("/CraftController")
public class CraftController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CraftPersistenceService craftService = null;//new CraftPersistenceServiceImpl();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sess = request.getSession(true);
		Integer invnId = (Integer) sess.getAttribute("invnId");
		
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		Double price = Double.parseDouble(request.getParameter("price"));
		String usage = request.getParameter("usage");
		

		Craft craft = new Craft();
		craft.setName(name);
		craft.setDescription(description);
		craft.setPrice(price);
		craft.setSold(false);
		craft.setUsage(usage);

		try {
			craftService.create(craft, invnId);
		} catch (Exception ex) {
			System.out.println(ex);
			// TODO return failure message
		}
		
		RequestDispatcher rs = request.getRequestDispatcher("inventory.jsp");
		rs.forward(request, response);
	}
}
