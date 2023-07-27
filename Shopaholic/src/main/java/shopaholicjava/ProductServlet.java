package shopaholicjava;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;

import shopaholicjava.JDBC;

/**
 * Servlet implementation class ProductServlet
 */
//@WebServlet("_ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	private JDBC jdbc = new JDBC();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductServlet() {
		super();
		// TODO Auto-generated constructor stub
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

		try {
			Connection con = JDBC.initializeDatabase();

			PreparedStatement preparedStatement = con
					.prepareStatement("INSERT INTO Products (PID, ProductName, Price) VALUES (?, ?, ?)");
			preparedStatement.setString(1, request.getParameter("PID"));
			preparedStatement.setString(2, request.getParameter("ProductName"));
			preparedStatement.setInt(3, Integer.valueOf(request.getParameter("Price")));
			preparedStatement.executeUpdate();			  
			preparedStatement.close();
            con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		/*
		 * String PID = request.getParameter("PID"); String ProductName =
		 * request.getParameter("ProductName"); String Price =
		 * request.getParameter("Price");
		 * 
		 * Product product = new Product(); product.setPID(PID);
		 * product.setProductName(ProductName); product.setPrice(Price);
		 * 
		 * try { jdbc.registerProduct(product); } catch (ClassNotFoundException |
		 * SQLException e) { e.printStackTrace(); }
		 * 
		 * // response.sendRedirect(""); // RequestDispatcher dispatcher =
		 * request.getRequestDispatcher("/WEB-INF/register.html"); RequestDispatcher
		 * dispatcher =
		 * request.getRequestDispatcher("/WEB-INF/views/registerproductsuccess.jsp");
		 * dispatcher.forward(request, response);
		 */
	}

}
