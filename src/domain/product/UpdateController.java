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
import db.services.*;
import db.services.impl.*;

@WebServlet("/UpdateController")
public class UpdateController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private PaintingPersistenceService paintService = new PaintingPersistenceServiceImpl();
	private SculpturePersistenceService sculptureService = new SculpturePersistenceServiceImpl();
	private CraftPersistenceService craftService = new CraftPersistenceServiceImpl();
	//
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession sess = request.getSession(true);
		Integer invnId = (Integer) sess.getAttribute("invnId");
		String removeproduct = request.getParameter("removeproduct");
		
		//System.out.println(removeproduct);
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		Integer catId = Integer.parseInt(request.getParameter("catId"));
		Integer prodId = Integer.parseInt(request.getParameter("prodId"));
		Double price = null;
		if(request.getParameter("price")!=null)
		{
		price = Double.parseDouble(request.getParameter("price"));
		
		}
		
		// remove code here
		
		if(catId ==1 ) {
		Painting painting=null;
		try {
			painting = paintService.retrieve(prodId);
		} catch (SQLException | DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//painting = request.
		if(removeproduct!=null)
		{
			try {
				paintService.delete(painting);
				
			} catch (Exception ex) {
				System.out.println(ex);
				// TODO return failure message
			}
			
		}
		else {
		String canvasType = request.getParameter("canvasType");
		String paintType = request.getParameter("paintType");
		Double length = Double.parseDouble(request.getParameter("length"));
		Double width = Double.parseDouble(request.getParameter("width"));
		painting.setName(name);
		painting.setDescription(description);
		painting.setPrice(price);
		//painting.setSold(false);
		painting.setCanvasType(canvasType);
		painting.setPaintType(paintType);
		painting.setLength(length);
		painting.setWidth(width);
		

		try {
			paintService.update(painting);
		} catch (Exception ex) {
			System.out.println(ex);
			// TODO return failure message
		}}}
		
		else if(catId ==3 ) {
			Craft craft=null;
			try {
				craft = craftService.retrieve(prodId);
			} catch (SQLException | DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(removeproduct!=null)
			{
				try {
					craftService.delete(craft);
					
				} catch (Exception ex) {
					System.out.println(ex);
					// TODO return failure message
				}
				
			}
			
			//painting = request.
			else {
			String usage = request.getParameter("usage");
			
			Double length = Double.parseDouble(request.getParameter("length"));
			Double width = Double.parseDouble(request.getParameter("width"));
			Double height = Double.parseDouble(request.getParameter("height"));
			craft.setName(name);
			craft.setDescription(description);
			craft.setPrice(price);
			//painting.setSold(false);
			craft.setUsage(usage);
			craft.setLength(length);
			craft.setWidth(width);
			craft.setHeight(height);
			

			try {
				craftService.update(craft);
			} catch (Exception ex) {
				System.out.println(ex);
				// TODO return failure message
			}}}
		
		else if (catId == 2 ) {
			Sculpture sculpture=null;
			try {
				sculpture = sculptureService.retrieve(prodId);
			} catch (SQLException | DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(removeproduct!=null)
			{
				try {
					sculptureService.delete(sculpture);
					
				} catch (Exception ex) {
					System.out.println(ex);
					// TODO return failure message
				}
				
			}
			//painting = request.
			else {
			//String canvasType = request.getParameter("canvasType");
			String material = request.getParameter("material");
			//String paintType = request.getParameter("paintType");
			Double length = Double.parseDouble(request.getParameter("length"));
			Double width = Double.parseDouble(request.getParameter("width"));
			Double height = Double.parseDouble(request.getParameter("height"));
			Double weight = Double.parseDouble(request.getParameter("weight"));
			sculpture.setName(name);
			sculpture.setDescription(description);
			sculpture.setPrice(price);
			//sculpture.setSold(false);
			sculpture.setLength(length);
			sculpture.setWidth(width);
			sculpture.setHeight(height);
			sculpture.setMaterial(material);
			sculpture.setWeight(weight);

			try {
				sculptureService.update(sculpture);
			} catch (Exception ex) {
				System.out.println(ex);
				// TODO return failure message
			}}}
		RequestDispatcher rs = request.getRequestDispatcher("inventory.jsp");
		rs.forward(request, response);
	}
	
}