package shopaholicjava;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

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
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/adminhomepage.jsp");
//		dispatcher.forward(request, response);
		
//		System.out.println("In doGet method");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
//			System.out.println("Connected!");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
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
//			for(User u: users) {
//				System.out.println(u.toString());
//			}
			
//			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/adminhomepage.jsp");
//			rd.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
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
//			for(Merchant m: merchants) {
//				System.out.println(m.toString());
//			}
			
//			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/adminhomepage.jsp");
//			rd.forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
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
				product.setPID(PID);
				product.setProductName(ProductName);
				product.setPrice(Price);
				products.add(product);
//				product.toString();
			} 
			
			request.setAttribute("productdata", products); 
//			for(Product p: products) {
//				System.out.println(p.toString());
//			}
			
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
