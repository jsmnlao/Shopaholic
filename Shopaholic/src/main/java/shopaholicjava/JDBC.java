package shopaholicjava;

import java.sql.*;

public class JDBC {

	public static void main(String[] args) throws SQLException {
	  
	  try { 
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  System.out.println("Connected!");
	  } 
	  catch (ClassNotFoundException e) { 
		  System.out.println(e.getMessage()); 
	  }
	  
	  
	  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopaholic", "root","Thisis4mySQL"); 
	  Statement st = con.createStatement(); 
	  ResultSet rs = st.executeQuery("SELECT * " + "FROM products");
	  
	  rs.next(); System.out.println(rs.getString("PID") + " " +
	  rs.getString("ProductName") + " " + rs.getInt("Price")); 
	  // String name = rs.getString("name"); // int age = rs.getInt("age"); 
	  //System.out.println(name + " " + age); 
	  con.close(); 
	 
	}

	public int registerProduct(Product product) throws ClassNotFoundException, SQLException { 
	  String INSERT_PRODUCTS_SQL = "INSERT INTO Products (PID, ProductName, Price)" + "VALUES (?, ?, ?);";
	  System.out.println("Testing");
	  int result = 0;
	  
	  //Step 1: Connect to database
	  try { 
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  System.out.println("Connected!");
	  } 
	  catch (ClassNotFoundException e) { 
		  System.out.println(e.getMessage()); 
	  }
	  
	  try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopaholic", "root", "Thisis4mySQL");
	  
	  // Step 2: Create a statement using connection object PreparedStatement
	  PreparedStatement preparedStatement = con.prepareStatement(INSERT_PRODUCTS_SQL)) {
	  preparedStatement.setString(1, product.getPID());
	  preparedStatement.setString(2, product.getProductName());
	  preparedStatement.setString(3, product.getPrice());
	  System.out.println(preparedStatement);
	  
	  // Step 3: Execute the query or update query
	  result = preparedStatement.executeUpdate();
	  
	  if (!con.getAutoCommit()) {
          con.commit();
       }
	  } 
	  
	  catch (SQLException e) { // process sql exception 
		  printSQLException(e);
	  }
	  return result; 
	}
	
	public int addProducttoCart(Product product) throws ClassNotFoundException, SQLException { 
		  String INSERT_PRODUCTS_SQL = "INSERT INTO Products (PID, ProductName, Price)" + "VALUES (?, ?, ?);";
		  System.out.println("Testing");
		  int result = 0;
		  
		  //Step 1: Connect to database
		  try { 
			  Class.forName("com.mysql.cj.jdbc.Driver");
			  System.out.println("Connected!");
		  } 
		  catch (ClassNotFoundException e) { 
			  System.out.println(e.getMessage()); 
		  }
		  
		  try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopaholic", "root", "Thisis4mySQL");
		  
		  // Step 2: Create a statement using connection object PreparedStatement
		  PreparedStatement preparedStatement = con.prepareStatement(INSERT_PRODUCTS_SQL)) {
		  preparedStatement.setString(1, product.getPID());
		  preparedStatement.setString(2, product.getProductName());
		  preparedStatement.setString(3, product.getPrice());
		  System.out.println(preparedStatement);
		  
		  // Step 3: Execute the query or update query
		  result = preparedStatement.executeUpdate();
		  
		  if (!con.getAutoCommit()) {
	          con.commit();
	       }
		  } 
		  
		  catch (SQLException e) { // process sql exception 
			  printSQLException(e);
		  }
		  return result; 
		} 
	
	private void printSQLException(SQLException ex) {
	    for (Throwable e: ex) {
	        if (e instanceof SQLException) {
	            e.printStackTrace(System.err);
	            System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	            System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	            System.err.println("Message: " + e.getMessage());
	            Throwable t = ex.getCause();
	            while (t != null) {
	                System.out.println("Cause: " + t);
	                t = t.getCause();
	            }
	        }
	    }
	}
}
