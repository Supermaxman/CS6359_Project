package domain.product;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProductController")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProductController() {}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String productId = req.getParameter("prodId");
		String button1 = req.getParameter("Back");
		
		
		Painting painting = domain.product.PaintingDAO.getPainting(productId);
		
		if(painting != null) {
			resp.setContentType("text/html");
		    req.setAttribute("desc", painting.getDescription());
		    req.setAttribute("canvType", painting.getCanvasType());
		    req.setAttribute("leng", painting.getLength());
		    req.setAttribute("name", painting.getName());
		    req.setAttribute("paintType", painting.getPaintType());
		    req.setAttribute("price", painting.getPrice());
		    req.setAttribute("prodId", painting.getProdId());
		   // req.setAttribute("seller", painting.getSeller());
		    req.setAttribute("width", painting.getWidth());
		    req.setAttribute("sold", painting.isSold());
			RequestDispatcher rs = req.getRequestDispatcher("productdetails.jsp"); 
            rs.forward(req, resp);
		} 
		else{
			RequestDispatcher rs = req.getRequestDispatcher("Error.jsp"); 
			rs.forward(req, resp);
		}
		
		if(button1!=null)
		{
			RequestDispatcher rs = req.getRequestDispatcher("PaintingCategory.jsp"); 
			rs.forward(req, resp);
		}
		
		
		
	}

}
