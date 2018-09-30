package domain.user;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InventoryController")
public class InventoryController extends HttpServlet {
	/**
	 * Servlet implementation class Inventory
	 */
	private static final long serialVersionUID = 1L;
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		
        response.setContentType("text/html;charset=UTF-8");
        String button1 = request.getParameter("Add Product");
        if(button1 != null) {
            RequestDispatcher rs = request.getRequestDispatcher("newproduct.jsp"); 
            rs.forward(request, response);
        }
	        
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