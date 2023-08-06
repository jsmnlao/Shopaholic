package servlets;
import shopaholicjava.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	} 

	/**
	 * @see HttpServlet#getPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//Retrieve login info
		String ID = request.getParameter("ID");
		String FirstName = request.getParameter("FirstName");
		String LastName = request.getParameter("LastName");
		String UserName = request.getParameter("UserName");
		String UserPassword = request.getParameter("UserPassword");
		String UserType = request.getParameter("UserType");

		//Create new user/merchant/admin
		User user = new User();
		Merchant merchant = new Merchant();
		Admin admin = new Admin();
		if(UserType.equals("User")) {
			user.setUID(ID);
			user.setFirstName(FirstName);
			user.setLastName(LastName);
			user.setUserName(UserName);
			user.setUserPassword(UserPassword);
		}
		else if(UserType.equals("Merchant")) {
			merchant.setMID(ID);
			merchant.setFirstName(FirstName);
			merchant.setLastName(LastName);
			merchant.setUserName(UserName);
			merchant.setUserPassword(UserPassword);
		}
		else if(UserType.equals("Admin")) {
			admin.setAID(ID);
			admin.setFirstName(FirstName);
			admin.setLastName(LastName);
			admin.setUserName(UserName);
			admin.setUserPassword(UserPassword);
		}
		
		
		try (Connection con = DatabaseConnection.getConnection();
				PreparedStatement userpst = con.prepareStatement("SELECT * FROM MemberUsers WHERE UID =? AND FirstName =? AND LastName =? AND UserName = ? AND UserPassword =?");
				PreparedStatement merchantpst = con.prepareStatement("SELECT * FROM Merchants WHERE MID =? AND FirstName =? AND LastName =? AND UserName = ? AND UserPassword =?");
				PreparedStatement adminpst = con.prepareStatement("SELECT * FROM Admin WHERE AID =? AND FirstName =? AND LastName =? AND UserName = ? AND UserPassword =?")) {
			
			ResultSet resultSet = null;
			if(UserType.equals("User")) {
				userpst.setString(1, user.getUID());
				userpst.setString(2, user.getFirstName());
				userpst.setString(3, user.getLastName());
				userpst.setString(4, user.getUserName());
				userpst.setString(5, user.getUserPassword());
				
				
				resultSet = userpst.executeQuery();
			}
			else if(UserType.equals("Merchant")) {
				merchantpst.setString(1, merchant.getMID());
				merchantpst.setString(2, merchant.getFirstName());
				merchantpst.setString(3, merchant.getLastName());
				merchantpst.setString(4, merchant.getUserName());
				merchantpst.setString(5, merchant.getUserPassword());
				
				resultSet = merchantpst.executeQuery();
				
				
			}
			else if(UserType.equals("Admin")) {
				adminpst.setString(1, admin.getAID());
				adminpst.setString(2, admin.getFirstName());
				adminpst.setString(3, admin.getLastName());
				adminpst.setString(4, admin.getUserName());
				adminpst.setString(5, admin.getUserPassword());
				
//				HttpSession session = request.getSession();
//				session.setAttribute(ID, admin.getAID());
//				session.setAttribute(FirstName, admin.getFirstName());
//				session.setAttribute(LastName, admin.getLastName());
//				session.setAttribute(UserName, admin.getUserName());
//				session.setAttribute(UserPassword, admin.getUserPassword());
				
				resultSet = adminpst.executeQuery();
			}
			
			//If user/merchant/admin matches the info in the database, then redirect to corresponding pages
			if(resultSet.next() == true) {
				RequestDispatcher dispatcher = null;
				if(UserType.equals("User")) {
					HttpSession session = request.getSession(true);
					session.setAttribute("UID", user.getUID());
					session.setAttribute("UserName", user.getUserName());
					session.setAttribute("CID", "CC" + user.getUID().substring(2));
					session.setAttribute("User", user);
					response.sendRedirect("UserServlet");
				}
				else if(UserType.equals("Merchant")) {
					HttpSession session = request.getSession(true);
					session.setAttribute("MerchantName",  merchant.getUserName());
					session.setAttribute("MID", merchant.getMID());
					response.sendRedirect("MerchantServlet");
				}
				else if(UserType.equals("Admin")) {
					response.sendRedirect("AdminServlet");
				}
				resultSet.close();
			}
			//If user/merchant/admin does NOT match the info in the database, go to error page and try again
			else {
				RequestDispatcher dispatcher = null;
				dispatcher = request.getRequestDispatcher("/WEB-INF/views/loginerror.jsp");
				dispatcher.forward(request, response);
				resultSet.close();
			}	
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

	}

}