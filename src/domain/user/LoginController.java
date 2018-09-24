package domain.user;

import java.io.IOException;
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

    public LoginController() {}
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String pass = request.getParameter("password");
		String submitType = request.getParameter("submit");
		Login login = new Login(username, pass);
		User c = null;
		try 
		{
			c = userService.validate(login);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
		if(submitType.equals("login") && c!=null && c.getName()!=null){
			request.setAttribute("message", "Hello "+ c.getName());
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
		}else if(submitType.equals("register")){
			c.setUsername(request.getParameter("username"));
			c.setPassword(request.getParameter("password"));
			c.setName(request.getParameter("name"));
			c.setAddress(request.getParameter("address"));
			String message = "Registration done, please login!";
			try 
			{
				userService.register(c);
			} catch (Exception ex) {
				System.out.println(ex);
				message = "Unable to register user!";
			}
			request.setAttribute("successMessage", message);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else{
			request.setAttribute("message", "Data Not Found! Please register!");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}

	}

}
