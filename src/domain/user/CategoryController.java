package domain.user;


import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CategoryController extends HttpServlet {
	/**
	 * Servlet implementation class Painting
	 */
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		
	        response.setContentType("text/html;charset=UTF-8");
	        String button1 = request.getParameter("View Details");
	        String button2 = request.getParameter("View Cart");
	        
	        if(button1 != null) {
	            RequestDispatcher rs = request.getRequestDispatcher("ProductDetails.jsp"); 
	            rs.forward(request, response);
	        }
	        else if(button2 != null) { 
	            RequestDispatcher rs = request.getRequestDispatcher("Cart.jsp"); 
	            rs.forward(request, response);
	        }
	        
	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	               processRequest(request, response);
	          }

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	               processRequest(request, response);
	    }
	}