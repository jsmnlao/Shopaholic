package servlets;
import shopaholicjava.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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


//import shopaholicjava.DatabaseConnection;
//import shopaholicjava.Product;

public class MerchantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String MerchantName = (String) request.getSession().getAttribute("MerchantName");
    	System.out.println("MerchantName in MerchantServlet: " + MerchantName);
		viewProducts(request, response);
    	
//        String action = request.getParameter("action");
        	
    	/*
        	try (Connection con = DatabaseConnection.getConnection();) {
          	String MID = (String) request.getSession().getAttribute("MID");
          	System.out.println("MID from MerchantServlet: " + MID);
            List<Product> products = new ArrayList<>();
              
           // Get all products associated with the merchant from the MerchantProducts table
              String query = "SELECT * FROM Products p " +
                             "JOIN MerchantProducts mp ON p.PID = mp.PID " +
                             "WHERE mp.MID = ?";
              try (PreparedStatement statement = con.prepareStatement(query)) {
                  statement.setString(1, MID);
                  ResultSet resultSet = statement.executeQuery();
                  
                  while (resultSet.next()) {
                      Product product = new Product();
                      product.setPID(resultSet.getString("PID"));
                      product.setProductName(resultSet.getString("ProductName"));
                      product.setProductType(resultSet.getString("ProductType"));
                      product.setPrice(resultSet.getString("Price"));
                      products.add(product);
                  }
               // Set the products as a request attribute to be displayed in the JSP
                  
                  HttpSession session = request.getSession(true);
                  session.setAttribute("merchantdata",  products);
                  System.out.println("Set attribute merchantdata");
                  System.out.println("merchantdata: " + request.getSession().getAttribute("merchantdata"));
                  RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/merchanthomepage.jsp");
                  rd.forward(request, response);
              }
              
          } catch (SQLException e) {
              e.printStackTrace();
              response.getWriter().println("Error occurred while fetching data from the database.");
          }
        	*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.isEmpty()) {
            // Default action: view all products associated with the merchant
//            viewProducts(request, response);
        } else if (action.equals("add")) {
            addProduct(request, response);
        } else {
            // Invalid action, redirect to the main page
            response.sendRedirect("MerchantServlet");
        }
    }

    
    private void viewProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	try (Connection con = DatabaseConnection.getConnection();) {
          	String MID = (String) request.getSession().getAttribute("MID");
          	System.out.println("MID from MerchantServlet: " + MID);
            List<Product> products = new ArrayList<>();
              
           // Get all products associated with the merchant from the MerchantProducts table
              String query = "SELECT * FROM Products p " +
                             "JOIN MerchantProducts mp ON p.PID = mp.PID " +
                             "WHERE mp.MID = ?";
              try (PreparedStatement statement = con.prepareStatement(query)) {
                  statement.setString(1, MID);
                  ResultSet resultSet = statement.executeQuery();
                  
                  while (resultSet.next()) {
                      Product product = new Product();
                      product.setPID(resultSet.getString("PID"));
                      product.setProductName(resultSet.getString("ProductName"));
                      product.setProductType(resultSet.getString("ProductType"));
                      product.setPrice(resultSet.getString("Price"));
                      products.add(product);
                  }
                  
               // Set the products as a request attribute to be displayed in the JSP
                  HttpSession session = request.getSession(true);
                  session.setAttribute("merchantdata",  products);
                  RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/merchanthomepage.jsp");
                  rd.forward(request, response);
              }
              
          } catch (SQLException e) {
              e.printStackTrace();
              response.getWriter().println("Error occurred while fetching data from the database.");
          }
   
    }
    

    private void addProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      	String MID = (String) request.getSession().getAttribute("MID");
      	String PID = request.getParameter("PID");
        String ProductName = request.getParameter("productName");
        String ProductType = request.getParameter("ProductType");
        String Price = request.getParameter("price");

        try (Connection con = DatabaseConnection.getConnection();) {
            // Insert the new product into the Products table
            String insertQuery = "INSERT INTO Products (PID, ProductName, ProductType, Price) VALUES (?, ?,  ?, ?)";
            try (PreparedStatement insertStatement = con.prepareStatement(insertQuery)) {
            	insertStatement.setString(1, PID);
                insertStatement.setString(2, ProductName);
                insertStatement.setString(3, ProductType);
                insertStatement.setString(4, Price);
                insertStatement.executeUpdate();

                // Get the auto-generated product ID
//                ResultSet generatedKeys = insertStatement.getGeneratedKeys();
//                int productId = -1;
//                if (generatedKeys.next()) {
//                    productId = generatedKeys.getInt(1);
//                }

                // Insert the product into the MerchantProducts table to associate it with the merchant
                String linkQuery = "INSERT INTO MerchantProducts (MID, PID) VALUES (?, ?)";
                try (PreparedStatement linkStatement = con.prepareStatement(linkQuery)) {
                    linkStatement.setString(1, MID);
                    linkStatement.setString(2, PID);
                    linkStatement.executeUpdate();
                }
            }

            // Redirect back to the main page to view all products
            response.sendRedirect("MerchantServlet");
            
           
        } catch (SQLException e) {
//          e.printStackTrace();
//          response.getWriter().println("Error occurred while adding the product to the database.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/addproducterror.jsp");
			dispatcher.forward(request, response);
        }
    }
}
