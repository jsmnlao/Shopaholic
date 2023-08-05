package shopaholicjava;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
 * Servlet implementation class ReviewServlet
 */
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ArrayList<Review> reviews = new ArrayList<Review>();
			try (Connection con = DatabaseConnection.getConnection();
					Statement s = con.createStatement();
					ResultSet resultSet = s.executeQuery("SELECT * FROM Reviews");){
			
				
				while(resultSet.next()) {
					Review review = new Review();
					String RID = resultSet.getString("RID");
					String PID = resultSet.getString("PID");
					String Author = resultSet.getString("Author");
					String ProductName = resultSet.getString("ProductName");
					String ReviewDescription = resultSet.getString("ReviewDescription");
					int Stars = resultSet.getInt("Stars");

					review.setRID(RID);
					review.setPID(PID);
					review.setAuthor(Author);
					review.setProductName(ProductName);
					review.setReviewDescription(ReviewDescription);
					review.setStars(Stars);
					System.out.println(review.toString());
					reviews.add(review);
					
				} 
				
				request.setAttribute("reviewdata", reviews);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/review.jsp");
				dispatcher.forward(request, response);
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
		
		String RID = request.getParameter("RID");
      	String PID = request.getParameter("PID");
      	String Author = request.getParameter("Author");
        String ProductName = request.getParameter("ProductName");
        String Description = request.getParameter("Description");
        String Star = request.getParameter("Star");

        try (Connection con = DatabaseConnection.getConnection();) {
            // Insert the new product into the Products table
            String insertQuery = "INSERT INTO Reviews (RID, PID, Author, ProductName, ReviewDescription, Stars) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement insertStatement = con.prepareStatement(insertQuery)) {
            	insertStatement.setString(1, RID);
                insertStatement.setString(2, PID);
                insertStatement.setString(3, Author);
                insertStatement.setString(4, ProductName);
                insertStatement.setString(5, Description);
                insertStatement.setString(6, Star);
                insertStatement.executeUpdate();

                // Get the auto-generated product ID
//                ResultSet generatedKeys = insertStatement.getGeneratedKeys();
//                int productId = -1;
//                if (generatedKeys.next()) {
//                    productId = generatedKeys.getInt(1);
//                }
            }
		
		response.sendRedirect("ReviewServlet");
        } catch (SQLException e) {
          e.printStackTrace();
      }
	}

}
