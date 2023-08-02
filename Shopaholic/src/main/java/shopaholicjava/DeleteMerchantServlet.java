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
 * Servlet implementation class DeleteMerchantServlet
 */
public class DeleteMerchantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMerchantServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/adminhomepage.jsp");
//		dispatcher.forward(request, response);
		
		String MID = request.getParameter("MID");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
//			System.out.println("Connected!");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopaholic", "root",
				"Thisis4mySQL");){
			PreparedStatement pst = con.prepareStatement("DELETE FROM Merchants WHERE MID =?");
			pst.setString(1, MID);
			pst.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
//		response.sendRedirect("/WEB-INF/views/loginsuccess.jsp");
//		request.getRequestDispatcher("/WEB-INF/views/adminhomepage.jsp");
//		dispatcher.forward(request, response);
		
		response.sendRedirect("AdminServlet");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
	}

}
