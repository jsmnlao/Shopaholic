package servlets;
import shopaholicjava.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class EditCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	getCartItems(request, response);
    	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/cart.jsp");
		rd.forward(request, response);
//        String action = request.getParameter("action");
//
//        if ("delete".equals(action)) {
//            String cartId = request.getParameter("cartId");
//            deleteProductFromCart(cartId);
//            response.sendRedirect(request.getContextPath() + "/CartServlet");
//            return;
//        } else if ("increase".equals(action)) {
//            String cartId = request.getParameter("cartId");
//            increaseProductQuantity(cartId);
//            response.sendRedirect(request.getContextPath() + "/CartServlet");
//            return;
//        }

//        List<Cart> cartItems = getCartItems();
//
//        request.setAttribute("cartItems", cartItems);
//        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    private void deleteProductFromCart(String cartId) {
        String deleteQuery = "DELETE FROM Cart WHERE cartId = ?";
        executeUpdateQuery(deleteQuery, cartId);
    }

    private void increaseProductQuantity(String cartId) {
        String updateQuery = "UPDATE Cart SET quantity = quantity + 1 WHERE cartId = ?";
        executeUpdateQuery(updateQuery, cartId);
    }

    /*
    private List<Cart> getCartItems(HttpServletRequest request, HttpServletResponse response) {
    	String UID = (String) request.getSession().getAttribute("UID");
    	String CID = (String) request.getSession().getAttribute("CID");
    	List<Cart> cartItems = new ArrayList<Cart>();

        String selectQuery = "SELECT * FROM Cart WHERE CID = ? AND UID = ?";
//        String cartId, String userId, String productId, int quantity

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement statement = conn.prepareStatement(selectQuery);) {
			statement.setString(1, CID);
			statement.setString(2, UID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
//                String CID = resultSet.getString("CID");
//                String UID = resultSet.getString("UID");
//                String PID = resultSet.getString("PID");
//                int quantity = resultSet.getInt("Quantity");
            	Cart cart = new Cart();
            	cart.setCartId(CID);
            	cart.setUserId(UID);
                cartItems.add(cart);
            }

            HttpSession session = request.getSession(true);
            session.setAttribute("cartdata", cartItems);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartItems;
    }
    */
    
    private void getCartItems(HttpServletRequest request, HttpServletResponse response) {
    	String CID = (String) request.getSession().getAttribute("CID");
    	List<Product> cartItems = new ArrayList<Product>();
    	
    	String selectQuery = "SELECT PID FROM CartProducts WHERE CID = ?";
    	try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement statement = conn.prepareStatement(selectQuery);) {
			statement.setString(1, CID);
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
            	Product product = new Product();
            	product.setPID(resultSet.getString("PID"));
            	System.out.println(product.toString());
            	cartItems.add(product);
            }
            
          //RETRIEVE PRODUCT INFO
            String retreiveinfo = "SELECT * FROM Products WHERE PID = ?";
            for(Product p : cartItems) {
            	PreparedStatement productpst = conn.prepareStatement(retreiveinfo);
    	        productpst.setString(1, p.getPID());
    	        ResultSet rs = productpst.executeQuery();
    	        while(rs.next()) {
    	        	p.setProductName(rs.getString("ProductName"));
    	        	p.setPrice(rs.getString("Price"));
    	        	p.setProductType(rs.getString("ProductType"));
    	        	p.toString();
    	        }
            }
            HttpSession session = request.getSession(true);
            session.setAttribute("cartdata", cartItems);
    		}
	    	catch (SQLException e) {
	            e.printStackTrace();
	        } 
    	} 
 
    private void executeUpdateQuery(String query, String cartId) {
		try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, cartId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
