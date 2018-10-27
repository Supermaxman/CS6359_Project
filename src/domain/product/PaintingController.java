package domain.product;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.services.CategoryPersistenceService;
import db.services.PaintingPersistenceService;
import db.services.impl.CategoryPersistenceServiceImpl;
import db.services.impl.PaintingPersistenceServiceImpl;

@WebServlet("/PaintingController")
public class PaintingController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private PaintingPersistenceService paintService = new PaintingPersistenceServiceImpl();
	private CategoryPersistenceService catService = new CategoryPersistenceServiceImpl();
	private static Integer catId = 1; 

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
		Painting painting = new Painting();
		painting.setName(name);
		painting.setDescription(description);
		painting.setPrice(price);
		painting.setSold(false);
		painting.setCanvasType(canvasType);
		painting.setPaintType(paintType);
		painting.setLength(length);
		painting.setWidth(width);

		try {
			Category cat = catService.retrieve(catId);
			painting.setCategory(cat);			
			paintService.create(painting, invnId);
		} catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace(System.out);
			// TODO return failure message
		}
		
		RequestDispatcher rs = request.getRequestDispatcher("inventory.jsp");
		rs.forward(request, response);
	}
	
}