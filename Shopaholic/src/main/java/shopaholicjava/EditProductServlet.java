package shopaholicjava;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class EditProductServlet
 */
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProductServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/editproduct.jsp");
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
    		String PID = request.getParameter("PID");
    		String ProductName = request.getParameter("ProductName");
    		String Price = request.getParameter("Price");
    		try {
    			Class.forName("com.mysql.cj.jdbc.Driver");
//    			System.out.println("Connected!");
    		} catch (ClassNotFoundException e) {
    			System.out.println(e.getMessage());
    		}
    		
    		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopaholic", "root",
    				"Thisis4mySQL");){
    			PreparedStatement pst = con.prepareStatement("UPDATE Products SET ProductName = ?, Price = ? WHERE PID = ?;");
    			pst.setString(1, ProductName);
    			pst.setString(2, Price);
    			pst.setString(3, PID);
    			pst.executeUpdate();
//    			System.out.println("Executed update query");
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
