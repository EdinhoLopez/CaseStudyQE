package javaDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javaDatabase.MariaDBConnection;

import javaModels.Category;

public class CategoryDAO {
	
	//Connection and statement that will be used in each method
		Connection con = null;
		Statement stm = null;
		
		//Tests whether a connection to the DB is available
		public Connection testConnection() {
					
			//Creates object from MariaDBConnection so you can use its getConnection Method
			MariaDBConnection mariadbConnection = new MariaDBConnection();
					
			try {
						
				//Variable "con" is assigned a connection to the Database
				con = mariadbConnection.getConnection();
				System.out.println("Connected!!!!!");
				return con;
						
						
			}
			catch(Exception e) {
									
				System.out.println("Connection failed to MariaDb");
				return null;
						
			}
			finally {
						
				if(con!=null) {
							
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
							
				}
						
			}
					
		}
		
		//Returns an ArrayList filled with Category objects from database
		public ArrayList<Category> getAllCategory() throws SQLException {
			// Declare variables
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			Category u = null;
			ArrayList<Category> categoryList = null;

			// Assign query string to a variable
			String qString = "select * from category";

			// Create MySqlConnection class instance
			MariaDBConnection mysql = new MariaDBConnection();

			// Begin try/catch block to query the database
			try
			{
				// Connect to database
				conn = mysql.getConnection();
				// If the connection fails the application won't make it to this point
				
				// Create Statement instance/object
				stmt = conn.createStatement();
			
				// Run query and assign to the ResultSet instance
				rs = stmt.executeQuery(qString);
				//Create list to hold Category objects
				categoryList = new ArrayList<Category>();
				// Read the ResultSet instance
				while (rs.next()) {
					// Each iteration creates a new Category
					u = new Category();
					// Assign columns/fields to related fields in the Category object
					// 1,2 and 3 represent column numbers/positions
					u.setCategoryID(rs.getInt(1));
					u.setCategoryName(rs.getString(2));
					u.setCategoryDescription(rs.getString(3));
					// Add the Category to the list
					categoryList.add(u);
					// Repeat until rs.next() returns false (i.e., end of ResultSet)
				}
			}
			catch (ClassNotFoundException | IOException | SQLException e)
			{
				System.out.println("Error: " + e.getMessage());
				e.getStackTrace();
			}
			finally
			{
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
			return categoryList;
		} // End of getAllCategory method	

		//Creates a Category object into the database
		public Integer registerCategory(Category inputCategory) throws SQLException, ClassNotFoundException, IOException {
			// Declare variables
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			// Assign insert statement string to variable
			String insertString = "insert into category (categoryId, categoryName, categoryDescription) values (?,?,?)";
			
		    int ID = inputCategory.getCategoryID();
		    String[] COL = {"categoryId"};
		    
		    MariaDBConnection mysql = new MariaDBConnection();
		    
		    try
		    {
		        conn = mysql.getConnection();
		        stmt = conn.prepareStatement(insertString, COL);
		        
		        stmt.setInt(1, inputCategory.getCategoryID());
		        stmt.setString(2, inputCategory.getCategoryName());
		        stmt.setString(3, inputCategory.getCategoryDescription());
		        
		        stmt.executeUpdate();
		        
		        rs = stmt.getGeneratedKeys();
		        if(rs != null && rs.next()) {
		            ID = rs.getInt(1);
		        }
		        System.out.println(ID);
		    }
		    catch (SQLException e)
			{
				System.out.println("Error: " + e.getMessage());
			}
			finally
			{
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
		    
			return ID;
		} // End of registerCategory() method

		//Retrieves a Category object from the DB through an ID provided
		public Category getCategoryById(int categoryId) throws ClassNotFoundException, IOException, SQLException {
			// Declare variables
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			Category u = null;
			
			// Assign query string to variable
			String qString = "select * from category where categoryId = ?";
			
			// Create MySqlConnection class instance
			MariaDBConnection mysql = new MariaDBConnection();
			
			// Begin try/catch block to query the database
			try
			{
				// Connect to database and assign query string to PreparedStatement object
				conn = mysql.getConnection();
				stmt = conn.prepareStatement(qString);
				
				// Set query parameters (?)
				stmt.setInt(1, categoryId); // Category_id if from String parameter passed to method
				
				// Run query and assign to ResultSet
				rs = stmt.executeQuery();
				
				// Retrieve ResultSet and assign to new Category
				if (rs.next()) {
					u = new Category();
					u.setCategoryID(rs.getInt(1));
					u.setCategoryName(rs.getString(2));
					u.setCategoryDescription(rs.getString(3));
				}
			}
			catch (ClassNotFoundException | IOException | SQLException e)
			{
				System.out.println("Error: " + e.getMessage());
				e.getStackTrace();
			}
			finally
			{
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
			return u;
		} // End of getCategoryById() method
		
		//Retrieves a Category object from the DB through a Name provided
		public Category getCategoryByName(String categoryName) throws ClassNotFoundException, IOException, SQLException {
		 // Declare variables
		 Connection conn = null;
		 PreparedStatement stmt = null;
		 ResultSet rs = null;
		 Category u = null;
		 
		 // Assign query string to variable
		 String qString = "select * from category where categoryName = ?";
		 
		 // Create MySqlConnection class instance
		 MariaDBConnection mysql = new MariaDBConnection();
		 
		 // Begin try/catch block to query the database
		 try
		 {
		 	// Connect to database and assign query string to PreparedStatement object
		 	conn = mysql.getConnection();
		 	stmt = conn.prepareStatement(qString);
		 	
		 	// Set query parameters (?)
		 	stmt.setString(1, categoryName); 
		 	
		 	// Run query and assign to ResultSet
		 	rs = stmt.executeQuery();
		 	
		 	// Retrieve ResultSet and assign to new Category
		 	if (rs.next()) {
		 		u = new Category();
		 		u.setCategoryID(rs.getInt(1));
		 		u.setCategoryName(rs.getString(2));
		 		u.setCategoryDescription(rs.getString(3));
		 	}
		 }
		 catch (ClassNotFoundException | IOException | SQLException e)
		 {
		 	System.out.println("Error: " + e.getMessage());
		 	e.getStackTrace();
		 }
		 finally
		 {
		 	if (rs != null) {
		 		rs.close();
		 	}
		 	if (stmt != null) {
		 		stmt.close();
		 	}
		 	if (conn != null) {
		 		conn.close();
		 	}
		 }
		 return u;
			} // End of getCategoryByName() method
			
		//Updates a Category object from the DB through a given Category object
		public Boolean updateCategory(Category u) throws SQLException, ClassNotFoundException, IOException {
			// Declare variables
			Connection conn = null;
			PreparedStatement stmt = null;
			Integer updateResult = null;
			
			// Assign update string to variable
			String updateString = "update category "
					+ "set categoryName = ?, categoryDescription = ? "
					+ "where categoryId = ?";
			
			// Create MySqlConnection class instance
			MariaDBConnection mysql = new MariaDBConnection();
			// Begin try/catch block to query the database
			try
			{
				// Connect to database and assign query string to PreparedStatement object
				conn = mysql.getConnection();
				stmt = conn.prepareStatement(updateString);
				
				// Set query parameters (?)
				stmt.setString(1, u.getCategoryName());
				stmt.setString(2, u.getCategoryDescription());
				stmt.setInt(3, u.getCategoryID());
				// Run query and assign to ResultSet
				updateResult = stmt.executeUpdate();
			}
			catch (ClassNotFoundException | SQLException e)
			{
				System.out.println("Error: " + e.getMessage());
			}
			finally
			{
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
			if (updateResult > 0) {
				return true;
			}
			return false;
		} // End of updateCategory() method
		
		//Deletes a Category object from the DB through an ID provided
		public Boolean removeCategoryById(int categoryId) throws IOException, SQLException {
			// Declare variables
			Connection conn = null;
			PreparedStatement stmt = null;
			Integer updateResult = null;
			
			// Assign delete string to variable
			String deleteString = "delete from category where categoryId = ?";
			
			// Create MySqlConnection class instance
			MariaDBConnection mysql = new MariaDBConnection();
			// Begin try/catch block to query the database
			try
			{
				// Connect to database and assign query string to PreparedStatement object
				conn = mysql.getConnection();
				stmt = conn.prepareStatement(deleteString);
				
				// Set query parameters (?)
				stmt.setInt(1, categoryId);
				// Run query and assign to ResultSet
				updateResult = stmt.executeUpdate();
			}
			catch (ClassNotFoundException | SQLException e)
			{
				System.out.println("Error: " + e.getMessage());
			}
			finally
			{
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
			if (updateResult > 0) {
				return true;
			}
			return false;
		} // End of removeCategory() method

		//Deletes a Category object from the DB through a name provided
		public Boolean removeCategoryByName(String categoryName) {
			
			
			try {
				//Uses existing method to get Category to be deleted
				Category deletedCategory = getCategoryByName(categoryName);
				
				//Uses existing method to delete Category and returns outcome.
				return removeCategoryById(deletedCategory.getCategoryID());
				
			}
			catch(Exception e) {
				
				System.out.println("Unable to delete Category");
				return false;
				
			}
			
		}
	
}
