package domain.product;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.user.User;
import domain.user.UserDaoImpl;

@WebServlet("/AddProductController")
public class AddProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public AddProductController() {}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String name = req.getParameter("name");
		String desc = req.getParameter("description");
		String price = req.getParameter("price");
		String canvas = req.getParameter("canvastype");
		String paint = req.getParameter("painttype");
		String len = req.getParameter("length");
		String wid = req.getParameter("width");
		String userId = req.getParameter("userId");
		
		User user = new UserDaoImpl().getUser(userId);
		
		Painting prod = new Painting();
		prod.setName(name);
		prod.setDescription(desc);
		prod.setPrice(price); 
		prod.setCanvasType(canvas);
		prod.setPaintType(paint);
		prod.setLength(len);
		prod.setWidth(wid);
		prod.setSeller(user);
	
		boolean result = PaintingDAO.setPanting(prod);
		
		if(result) {
			RequestDispatcher rs = req.getRequestDispatcher("SuccessAdded.jsp");
			rs.forward(req, resp);
		} else {
			RequestDispatcher rs = req.getRequestDispatcher("Error.jsp");
			rs.forward(req, resp);
		}
	
	}
	

}
