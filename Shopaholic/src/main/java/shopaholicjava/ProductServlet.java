package shopaholicjava;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

/**
 * Servlet implementation class ProductServlet
 */
//@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JDBC jdbc;
	
	public void init() {
		jdbc = new JDBC();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

//		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/registerproduct.html");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/registerproduct.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String PID = request.getParameter("PID"); 
		String ProductName = request.getParameter("ProductName"); 
		String Price = request.getParameter("Price");
		 
		Product product = new Product(); 
		product.setPID(PID);
		product.setProductName(ProductName); 
		product.setPrice(Price);
		 
		 try { 
			 jdbc.registerProduct(product); 
		 } 
		 catch (Exception e) { 
			 e.printStackTrace(); 
		 }
		
		 // RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/register.html"); 
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/registerproductsuccess.jsp");
		 dispatcher.forward(request, response);
		 response.sendRedirect("registerproductsuccess.jsp");
	}

}
