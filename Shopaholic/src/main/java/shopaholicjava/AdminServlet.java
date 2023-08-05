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
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/adminhomepage.jsp");
//		dispatcher.forward(request, response);
		
//		System.out.println("In doGet method");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
//			System.out.println("Connected!");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		/*********************DISPLAY USER INFORMATION*******************/
		ArrayList<User> users = new ArrayList<User>();
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopaholic", "root",
				"Thisis4mySQL");
				Statement s = con.createStatement();
				ResultSet resultSet = s.executeQuery("SELECT * FROM MemberUsers;");){
			
			if(resultSet.equals(null)) {
				System.out.println("resultSet is null");
			}
			
			while(resultSet.next()) {
				User user = new User();
				String UID = resultSet.getString("UID");
				String FirstName = resultSet.getString("FirstName");
				String LastName = resultSet.getString("LastName");
				String UserName = resultSet.getString("UserName");
				user.setUID(UID);
				user.setFirstName(FirstName);
				user.setLastName(LastName);
				user.setUserName(UserName);
				users.add(user);
			} 
			
			request.setAttribute("userdata", users); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		/*********************DISPLAY MERCHANT INFORMATION*******************/
		ArrayList<Merchant> merchants = new ArrayList<Merchant>();
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopaholic", "root",
				"Thisis4mySQL");
				Statement s = con.createStatement();
				ResultSet resultSet = s.executeQuery("SELECT * FROM Merchants;");){
			
			if(resultSet.equals(null)) {
				System.out.println("resultSet is null");
			}
			
			while(resultSet.next()) {
				Merchant merchant = new Merchant();
				String MID = resultSet.getString("MID");
				String FirstName = resultSet.getString("FirstName");
				String LastName = resultSet.getString("LastName");
				String UserName = resultSet.getString("UserName");
				merchant.setMID(MID);
				merchant.setFirstName(FirstName);
				merchant.setLastName(LastName);
				merchant.setUserName(UserName);
				merchants.add(merchant);
//				merchant.toString();
			} 
			request.setAttribute("merchantdata", merchants); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		/*********************DISPLAY PRODUCT INFORMATION*******************/
		ArrayList<Product> products = new ArrayList<Product>();
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopaholic", "root",
				"Thisis4mySQL");
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
			request.setAttribute("productdata", products); 
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/adminhomepage.jsp");
			rd.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
		
	}

}
