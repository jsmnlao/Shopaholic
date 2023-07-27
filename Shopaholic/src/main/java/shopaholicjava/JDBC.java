package shopaholicjava;

import java.sql.*;

public class JDBC {

	static Connection initializeDatabase() throws SQLException, ClassNotFoundException {
		// Initialize all the information regarding
		// Database Connection

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Connected!");

		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopaholic", "root", "Thisis4mySQL");

		return con;
	}

	/*
	 public static void main(String[] args) throws SQLException {
	  
	  try { Class.forName("com.mysql.cj.jdbc.Driver");
	  System.out.println("Connected!");
	  
	  } catch (ClassNotFoundException e) { System.out.println(e.getMessage()); }
	  
	  // String host = "dbhost.yourcompany.com"; // String dbName = "someName"; //
	  int port = 3306; // String mysqlURL = "jdbc:mysql://" + host + ":" + port +
	  "/" + dbName;
	  
	  Connection con =
	  DriverManager.getConnection("jdbc:mysql://localhost:3306/shopaholic", "root",
	  "Thisis4mySQL"); Statement st = con.createStatement(); ResultSet rs =
	  st.executeQuery("SELECT * " + "FROM products");
	  
	  rs.next(); System.out.println(rs.getString("PID") + " " +
	  rs.getString("ProductName") + " " + rs.getInt("Price")); // String name =
	  rs.getString("name"); // int age = rs.getInt("age"); //
	  System.out.println(name + " " + age); con.close(); }
	 	
	  public int registerProduct(Product product) throws ClassNotFoundException,
	  SQLException { String INSERT_PRODUCTS_SQL =
	  "INSERT INTO Products (PID, ProductName, Price)" + "VALUES (?, ?, ?);";
	  
	  int result = 0;
	  
	  try { Class.forName("com.mysql.cj.jdbc.Driver");
	  System.out.println("Connected!");
	  
	  } catch (ClassNotFoundException e) { System.out.println(e.getMessage()); }
	  
	  try (Connection con =
	  DriverManager.getConnection("jdbc:mysql://localhost:3306/shopaholic", "root",
	  "Thisis4mySQL");
	  
	  // Step 2:Create a statement using connection object PreparedStatement
	  preparedStatement = con.prepareStatement(INSERT_PRODUCTS_SQL)) {
	  preparedStatement.setString(1, product.getPID());
	  preparedStatement.setString(2, product.getProductName());
	  preparedStatement.setString(3, product.getPrice());
	  System.out.println(preparedStatement);
	  
	  // Step 3: Execute the query or update query result =
	  preparedStatement.executeUpdate();
	  
	  } catch (SQLException e) { // process sql exception e.printStackTrace(); }
	  return result; 
	  
	  }
	  */
	
}
