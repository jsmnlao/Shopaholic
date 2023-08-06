package servlets;
import shopaholicjava.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Servlet implementation class ViewOrdersServlet
 */
public class ViewOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewOrdersServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String CID = (String) request.getSession().getAttribute("CID");
		System.out.println("CID in vieworderservlet: " + CID);
		List<Order> orderHistory = getOrderHistory(CID);

		request.setAttribute("orderHistory", orderHistory);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/orderhistory.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	private List<Order> getOrderHistory(String CID) {
		String selectProductsFromCart = "SELECT ProductName FROM CartProducts WHERE CID = ?";
		
		
		List<Product> productsInCart = new ArrayList<Product>();
		List<Order> orderHistory = new ArrayList<>();

		try (Connection conn = DatabaseConnection.getConnection()) {
			PreparedStatement pst = conn.prepareStatement(selectProductsFromCart);
			pst.setString(1, CID);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Product product = new Product();
				product.setProductName(rs.getString("ProductName"));
				productsInCart.add(product);
//				String pid = rs.getString("PID");
//				String productName = rs.getString("ProductName");
//				String orderDate = rs.getString("OrderDate");
//				orderHistory.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String insertToOrders = "INSERT INTO Orders(OID, CID, ProductName) VALUES (?, ?, ?)";
		Random random = new Random();
		int randomNumber = random.nextInt(900) + 100;
		String OID = "OO" + randomNumber;
		for(Product p : productsInCart) {
			try (Connection conn = DatabaseConnection.getConnection()) {
				PreparedStatement pst = conn.prepareStatement(insertToOrders);
				pst.setString(1, OID);
				pst.setString(2, CID);
				pst.setString(3, p.getProductName());

				pst.executeUpdate();
				}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		String selectOrders = "SELECT * FROM Orders WHERE CID = ?";
		try (Connection conn = DatabaseConnection.getConnection()) {
			PreparedStatement pst = conn.prepareStatement(selectOrders);
			pst.setString(1, CID);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				Order order = new Order();
				order.setOID(rs.getString("OID"));
				order.setCID(rs.getString("CID"));
				order.setProductName(rs.getString("ProductName"));
				orderHistory.add(order);
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		
		return orderHistory;
	}

}
