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
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class SignUpServlet
 */
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/webapp/createaccount.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String ID = request.getParameter("ID");
		String FirstName = request.getParameter("FirstName");
		String LastName = request.getParameter("LastName");
		String UserName = request.getParameter("UserName");
		String UserPassword = request.getParameter("UserPassword");
		String UserType = request.getParameter("UserType");
		
		User user = new User();
		Merchant merchant = new Merchant();
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
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
//			System.out.println("Connected!");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		boolean approved = true;
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopaholic", "root",
				"Thisis4mySQL");
				PreparedStatement userpst = con.prepareStatement("INSERT INTO MemberUsers (UID, FirstName, LastName, UserName, UserPassword) VALUES (?, ?, ?, ?, ?);");
				PreparedStatement cartpst = con.prepareStatement("INSERT INTO Cart(CID, UID) VALUES(?, ?)");
				PreparedStatement merchantpst = con.prepareStatement("INSERT INTO Merchants (MID, FirstName, LastName, UserName, UserPassword) VALUES (?, ?, ?, ?, ?);")){
			
			int resultSet = 0;
			if(UserType.equals("User")) {
				userpst.setString(1, user.getUID());
				userpst.setString(2, user.getFirstName());
				userpst.setString(3, user.getLastName());
				userpst.setString(4, user.getUserName());
				userpst.setString(5, user.getUserPassword());
				
				cartpst.setString(1, "CC" + user.getUID().substring(2));
				cartpst.setString(2, user.getUID());
				
				HttpSession session = request.getSession();
				session.setAttribute(ID, user.getUID());
				session.setAttribute(FirstName, user.getFirstName());
				session.setAttribute(LastName, user.getLastName());
				session.setAttribute(UserName, user.getUserName());
				session.setAttribute(UserPassword, user.getUserPassword());
				
				//Check if information is empty
				if(user.getUID().isEmpty() || user.getFirstName().isEmpty() || user.getLastName().isEmpty()
						|| user.getUserName().isEmpty() || user.getUserPassword().isEmpty()) {
					approved = false;
				}
				else {
					//Check for duplicate user account information
					try {
						resultSet = userpst.executeUpdate();
						cartpst.executeUpdate();
						request.setAttribute("CID", "CC" + user.getUID().substring(2));
					}
					catch(SQLException e) {
//						System.out.println("Duplicate keys!");
						approved = false;
					}
				}
			}
			else if(UserType.equals("Merchant")) {
				merchantpst.setString(1, merchant.getMID());
				merchantpst.setString(2, merchant.getFirstName());
				merchantpst.setString(3, merchant.getLastName());
				merchantpst.setString(4, merchant.getUserName());
				merchantpst.setString(5, merchant.getUserPassword());
				
				HttpSession session = request.getSession();
				session.setAttribute(ID, merchant.getMID());
				session.setAttribute(FirstName, merchant.getFirstName());
				session.setAttribute(LastName, merchant.getLastName());
				session.setAttribute(UserName, merchant.getUserName());
				session.setAttribute(UserPassword, merchant.getUserPassword());
								
				//Check if information is empty
				if(merchant.getMID().isEmpty() || merchant.getFirstName().isEmpty() || merchant.getLastName().isEmpty()
						|| merchant.getUserName().isEmpty() || merchant.getUserPassword().isEmpty()) {
					approved = false;
				}
				else {
					//Check for duplicate user account information
					try {
						resultSet = merchantpst.executeUpdate();
					}
					catch(SQLException e) {
//						System.out.println("Duplicate keys!");
						approved = false;
					}
				}
			} 
			
			//Determine if new account information is approved or not
			if(approved == false) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/createaccounterror.jsp");
				dispatcher.forward(request, response);
			}
			else if(approved == true) {
//				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/userhomepage.jsp");
//				dispatcher.forward(request, response);
				response.sendRedirect("UserServlet");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
