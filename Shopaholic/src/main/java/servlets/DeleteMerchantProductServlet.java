package servlets;
import shopaholicjava.*;

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
 * Servlet implementation class DeleteMerchantProduct
 */
public class DeleteMerchantProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMerchantProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String PID = request.getParameter("PID");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
//			System.out.println("Connected!");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopaholic", "root",
				"Thisis4mySQL");){
			PreparedStatement pst = con.prepareStatement("DELETE FROM Products WHERE PID =?");
			pst.setString(1, PID);
			pst.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("MerchantServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
