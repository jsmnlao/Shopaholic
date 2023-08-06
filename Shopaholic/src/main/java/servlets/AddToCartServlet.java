package servlets;
import shopaholicjava.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class AddToCartServlet
 */
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addCartItem(request, response);
		response.sendRedirect("UserServlet");
	}

	private void addCartItem(HttpServletRequest request, HttpServletResponse response) {
		String PID = request.getParameter("pid");
		String ProductName = request.getParameter("productname");
		String Price = request.getParameter("price");
		String CID = (String) request.getSession().getAttribute("CID");
//		System.out.println("CID from addtocart: " + CID);
//    	System.out.println("PID from addtocart: " + PID);
//    	System.out.println("ProductName from addtocart: " + ProductName);
//    	System.out.println("Price from addtocart: " + Price);

    	//INSERT INTO CART PRODUCTS
    	String selectQuery = "INSERT INTO CartProducts(CID, PID, ProductName) VALUES(?, ?, ?);";
    	try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement statement = conn.prepareStatement(selectQuery);) {
			statement.setString(1, CID);
			statement.setString(2, PID);
			statement.setString(3, ProductName);
            statement.executeUpdate();
            
            Product product = new Product();
        	product.setPID(PID);
        	product.setProductName(ProductName);
        	product.setPrice(Price);
        	System.out.println(product.toString());
    	}
    	catch (SQLException e) {
            e.printStackTrace();
        }
    	
    	//DELETE PRODUCT INFO
//    	try (Connection conn = DatabaseConnection.getConnection();){
//			PreparedStatement pst = conn.prepareStatement("DELETE FROM Products WHERE PID =?");
//			pst.setString(1, PID);
//			pst.executeUpdate();
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
    }    
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
