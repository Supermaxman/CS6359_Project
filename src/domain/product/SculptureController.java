package domain.product;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.services.PaintingPersistenceService;
import db.services.SculpturePersistenceService;
import db.services.impl.PaintingPersistenceServiceImpl;

/**
 * Servlet implementation class SculptureController
 */
@WebServlet("/SculptureController")
public class SculptureController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SculpturePersistenceService sculptService =null;// new SculpturePersistenceServiceImpl();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sess = request.getSession(true);
		Integer invnId = (Integer) sess.getAttribute("invnId");
		
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		Double price = Double.parseDouble(request.getParameter("price"));
		String canvasType = request.getParameter("canvasType");
		String paintType = request.getParameter("paintType");
		Double length = Double.parseDouble(request.getParameter("length"));
		Double width = Double.parseDouble(request.getParameter("width"));
		Double height = Double.parseDouble(request.getParameter("height"));
		Double weight = Double.parseDouble(request.getParameter("weight"));
		String material = request.getParameter("material");

		Sculpture sculpture = new Sculpture();
		sculpture.setName(name);
		sculpture.setDescription(description);
		sculpture.setPrice(price);
		sculpture.setSold(false);
		sculpture.setLength(length);
		sculpture.setWidth(width);
		sculpture.setHeight(height);
		sculpture.setMaterial(material);
		sculpture.setWeight(weight);

		try {
			sculptService.create(sculpture, invnId);
		} catch (Exception ex) {
			System.out.println(ex);
			// TODO return failure message
		}
		
		RequestDispatcher rs = request.getRequestDispatcher("inventory.jsp");
		rs.forward(request, response);
	}
}
