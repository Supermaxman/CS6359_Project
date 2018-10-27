package domain.product;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import db.services.CategoryPersistenceService;
import db.services.PaintingPersistenceService;
import db.services.SculpturePersistenceService;
import db.services.impl.CategoryPersistenceServiceImpl;
import db.services.impl.PaintingPersistenceServiceImpl;
import db.services.impl.SculpturePersistenceServiceImpl;

/**
 * Servlet implementation class SculptureController
 */
@MultipartConfig
@WebServlet("/SculptureController")
public class SculptureController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SculpturePersistenceService sculptService = new SculpturePersistenceServiceImpl();
	private CategoryPersistenceService catService = new CategoryPersistenceServiceImpl();
	private static Integer catId = 2; 
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sess = request.getSession(true);
		Integer invnId = (Integer) sess.getAttribute("invnId");
		
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		Double price = Double.parseDouble(request.getParameter("price"));
		Double length = Double.parseDouble(request.getParameter("length"));
		Double width = Double.parseDouble(request.getParameter("width"));
		Double height = Double.parseDouble(request.getParameter("height"));
		Double weight = Double.parseDouble(request.getParameter("weight"));
		String material = request.getParameter("material");
		
		// obtains the upload file part in this multipart request
        Part filePart = request.getPart("file");
        // obtains input stream of the upload file
        InputStream inputStream = filePart.getInputStream();
        BufferedImage image = ImageIO.read(inputStream);
        
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
		sculpture.setImage(image);
		
		try {
			Category cat = catService.retrieve(catId);
			sculpture.setCategory(cat);			
			sculptService.create(sculpture, invnId);
		} catch (Exception ex) {
			System.out.println(ex);
			// TODO return failure message
		}
		
		RequestDispatcher rs = request.getRequestDispatcher("inventory.jsp");
		rs.forward(request, response);
	}
}
