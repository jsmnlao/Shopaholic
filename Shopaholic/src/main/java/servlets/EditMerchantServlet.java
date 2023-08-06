package servlets;
import shopaholicjava.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditMerchantServlet
 */
public class EditMerchantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditMerchantServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/editmerchant.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cancelButton = request.getParameter("Cancel");
		
		//If CANCEL button is clicked, go back to homepage
        if ("Cancel".equals(cancelButton)) {
        	this.cancel(request, response);
        }
        //Else, continue to change info
        else {
    		String MID = request.getParameter("MID");
    		String FirstName = request.getParameter("FirstName");
    		String LastName = request.getParameter("LastName");
    		String UserName = request.getParameter("UserName");
    		String UserPassword = request.getParameter("UserPassword");
    		
    		try (Connection con = DatabaseConnection.getConnection();){
    			PreparedStatement pst = con.prepareStatement("UPDATE Merchants SET FirstName = ?, LastName = ?, UserName = ?, UserPassword = ? WHERE MID = ?;");
    			pst.setString(1, FirstName);
    			pst.setString(2, LastName);
    			pst.setString(3, UserName);
    			pst.setString(4, UserPassword);
    			pst.setString(5, MID);
    			pst.executeUpdate();
    			System.out.println("Executed update query");
    		}
    		catch(Exception e) {
    			e.printStackTrace();
    		}
    		response.sendRedirect("AdminServlet");
        }

	}
	
	protected void cancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("AdminServlet");
	}

}
