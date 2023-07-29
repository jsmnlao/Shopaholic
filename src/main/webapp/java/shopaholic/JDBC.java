package main.webapp.java.shopaholic;
import java.sql.*;

public class JDBC {
	private static final String url = "jdbc:mysql://localhost:3306/";
	private static final String username = "root";
	private static final String password = "gael7865";
//	private static final String database = "JournalDB";


    private static Connection getConnection(String database) throws SQLException {
        return DriverManager.getConnection(url+database, username, password);
    }
	
	
    public static void createProduct(String product_name, int price, String description, String category) throws SQLException {
        try (Connection connection = getConnection("shopaholicldb")) {
            String insertValues = "INSERT INTO journal (product_name, price, description, category) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(insertValues)) {
                statement.setString(1, product_name);
                statement.setInt(2, price);
                statement.setString(3, description);
                statement.setString(4, category);
                statement.executeUpdate();
            }
        }
    }	
    
    
	
  @SuppressWarnings("unused")
private static void deleteProduct(int id) throws SQLException {
      try (Connection connection = getConnection("shopaholicdb")) {
          String deleteFrom = "DELETE FROM product WHERE id = ?";
          try (PreparedStatement statement = connection.prepareStatement(deleteFrom)) {
              statement.setInt(1, id);
              System.out.println("Deleted Product");
          }
      }
  }
  
  
  @SuppressWarnings("unused")
private static void updateProduct(int id, String newName, Double newPrice, String newDescription, String newCategory) throws SQLException {
      try (Connection connection = getConnection("shopaholicdb")) {
          String updateDataSql = "UPDATE journal SET product_name=?, price=?, description=?, category=? WHERE id = ?";
          try (PreparedStatement statement = connection.prepareStatement(updateDataSql)) {
              statement.setString(1, newName);
              statement.setDouble(2, newPrice);
              statement.setString(3, newDescription);
              statement.setString(4, newCategory);
              statement.setInt(5, id);              
              int rowsAffected = statement.executeUpdate();
              System.out.println(rowsAffected + " row(s) updated");
          }
      }
  }
    
    
//    public static List<String> getProductData() throws SQLException {
//        List<String> dataList = new ArrayList<>();
//        String query = "SELECT title, date FROM journal";
//        try (Connection connection = getConnection("journaldb");
//                Statement statement = connection.createStatement();
//                ResultSet resultSet = statement.executeQuery(query)) {
//
//                   String title = resultSet.getString("title");
//                   dataList.add(title);
//           }
//        return dataList;
//    	
//    }	    
    

    
    public static void createDatabase() throws SQLException {
        try (Connection connection = getConnection("")){
             Statement statement = connection.createStatement(); 
            String createDatabaseSql = "CREATE DATABASE IF NOT EXISTS shopaholicdb";
            statement.executeUpdate(createDatabaseSql);
        }
    }
    
    
    public static void createTable() throws SQLException {
        String createTableSql = "CREATE TABLE IF NOT EXISTS product (" +
                "product_id INT PRIMARY KEY AUTO_INCREMENT," +
                "product_name VARCHAR(50) NOT NULL," +
                "price DECIMAL(10, 2) NOT NULL," +
                "description TEXT," +
                "category VARCHAR(50),"+
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP" +
//                "merchant_id INT NOT NULL," +
//                "FOREIGN KEY (merchant_id) REFERENCES merchants (merchant_id)" +
                ")";
        try (Connection connection = getConnection("shopaholicdb");
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableSql);
        }
    }   
    

    
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("success");

        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        createDatabase();
        createTable();
      
	}

}
    