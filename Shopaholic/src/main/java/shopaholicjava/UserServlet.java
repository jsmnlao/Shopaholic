package shopaholicjava;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().getAttribute("UID");
		String UID = (String) request.getSession(false).getAttribute("UID");
		String CID = (String) request.getSession(false).getAttribute("CID");
		
		try {
			ArrayList<Product> products = new ArrayList<Product>();
			try (Connection con = DatabaseConnection.getConnection();
					Statement s = con.createStatement();
					ResultSet resultSet = s.executeQuery("SELECT * FROM Products");){
				
				if(resultSet.equals(null)) {
					System.out.println("resultSet is null");
				}
				
				while(resultSet.next()) {
					Product product = new Product();
					String PID = resultSet.getString("PID");
					String ProductName = resultSet.getString("ProductName");
					String Price = resultSet.getString("Price");
					String ProductType = resultSet.getString("ProductType");
					product.setPID(PID);
					product.setProductName(ProductName);
					product.setProductType(ProductType);
					product.setPrice(Price);
					products.add(product);
				} 
				
				request.setAttribute("UID", UID);
				request.setAttribute("CID", CID);
				request.setAttribute("productdata", products);
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/userhomepage.jsp");
				rd.forward(request, response);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
