package javaDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javaDatabase.MariaDBConnection;

import javaModels.Items;

public class ItemsDAO {
	
	//Connection and statement that will be used in each method
		Connection con = null;
		Statement stm = null;
		

		//Determines whether a connection to the DB is possible		
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
		
		//Returns an ArrayList filled with Brands objects from database
		public ArrayList<Items> getAllItems() throws SQLException {
			// Declare variables
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			Items u = null;
			ArrayList<Items> itemsList = null;

			// Assign query string to a variable
			String qString = "select * from items";

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
				//Create list to hold User objects
				itemsList = new ArrayList<Items>();
				// Read the ResultSet instance
				while (rs.next()) {
					// Each iteration creates a new user
					u = new Items();
					// Assign columns/fields to related fields in the User object
					// 1,2 and 3 represent column numbers/positions
					u.setItemID(rs.getInt(1));
					u.setCategoryID(rs.getInt(2));
					u.setBrandID(rs.getInt(3));
					u.setItemPrice(rs.getDouble(4));
					u.setItemDescription(rs.getString(5));
					u.setItemInstrument(rs.getBoolean(6));
					// Add the user to the list
					itemsList.add(u);
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
			return itemsList;
		} // End of getAllUsers method	

		//Creates a Brand object into the database
		public Integer registerItemIncludeId(Items inputItem) throws SQLException, ClassNotFoundException, IOException {
			// Declare variables
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			// Assign insert statement string to variable
			String insertString = "insert into items (itemID, categoryID, brandID, itemPrice, itemDescription, itemInstrument) values (?,?,?,?,?,?)";
			
		    int ID = inputItem.getItemID();
		    String[] COL = {"itemID"};
		    
		    MariaDBConnection mysql = new MariaDBConnection();
		    
		    try
		    {
		        conn = mysql.getConnection();
		        stmt = conn.prepareStatement(insertString, COL);
		        
		        stmt.setInt(1, inputItem.getItemID());
		        stmt.setInt(2, inputItem.getCategoryID());
		        stmt.setInt(3, inputItem.getBrandID());
		        stmt.setDouble(4, inputItem.getItemPrice());
		        stmt.setString(5, inputItem.getItemDescription());
		        stmt.setBoolean(6, inputItem.isItemInstrument());
		        
		    	  
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
		} // End of registerBrand() method

		public Integer registerItemExcludeId(Items inputItem) throws SQLException, ClassNotFoundException, IOException {
			// Declare variables
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			// Assign insert statement string to variable
			String insertString = "insert into items (categoryID, brandID, itemPrice, itemDescription, itemInstrument) values (?,?,?,?,?)";
			
		    int ID = inputItem.getItemID();
		    String[] COL = {"itemId"};
		    
		    MariaDBConnection mysql = new MariaDBConnection();
		    
		    try
		    {
		        conn = mysql.getConnection();
		        stmt = conn.prepareStatement(insertString, COL);
		        
		        
		        stmt.setInt(1, inputItem.getCategoryID());
		        stmt.setInt(2, inputItem.getBrandID());
		        stmt.setDouble(3, inputItem.getItemPrice());
		        stmt.setString(4, inputItem.getItemDescription());
		        stmt.setBoolean(5, inputItem.isItemInstrument());
		        
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
		} // End of registerBrand() method
		
		//Retrieves a Brands object from the DB through an ID provided
		public Items getItemById(int itemId) throws ClassNotFoundException, IOException, SQLException {
			// Declare variables
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			Items u = null;
			
			// Assign query string to variable
			String qString = "select * from items where itemId = ?";
			
			// Create MySqlConnection class instance
			MariaDBConnection mysql = new MariaDBConnection();
			
			// Begin try/catch block to query the database
			try
			{
				// Connect to database and assign query string to PreparedStatement object
				conn = mysql.getConnection();
				stmt = conn.prepareStatement(qString);
				
				// Set query parameters (?)
				stmt.setInt(1, itemId); // user_id if from String parameter passed to method
				
				// Run query and assign to ResultSet
				rs = stmt.executeQuery();
				
				// Retrieve ResultSet and assign to new User
				if (rs.next()) {
					u = new Items();
					
					u.setItemID(rs.getInt(1));
					u.setCategoryID(rs.getInt(2));
					u.setBrandID(rs.getInt(3));
					u.setItemPrice(rs.getDouble(4));
					u.setItemDescription(rs.getString(5));
					u.setItemInstrument(rs.getBoolean(6));
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
		} // End of getBrandById() method
		
		//Retrieves a Brands object from the DB through a Name provided
		public Items getItemByName(String itemName) throws ClassNotFoundException, IOException, SQLException {
		 // Declare variables
		 Connection conn = null;
		 PreparedStatement stmt = null;
		 ResultSet rs = null;
		 Items u = null;
		 
		 // Assign query string to variable
		 String qString = "select * from items where itemDescription = ?";
		 
		 // Create MySqlConnection class instance
		 MariaDBConnection mysql = new MariaDBConnection();
		 
		 // Begin try/catch block to query the database
		 try
		 {
		 	// Connect to database and assign query string to PreparedStatement object
		 	conn = mysql.getConnection();
		 	stmt = conn.prepareStatement(qString);
		 	
		 	// Set query parameters (?)
		 	stmt.setString(1, itemName); 
		 	
		 	// Run query and assign to ResultSet
		 	rs = stmt.executeQuery();
		 	
		 	// Retrieve ResultSet and assign to new User
		 	if (rs.next()) {
		 		u = new Items();
		 		
		 		u.setItemID(rs.getInt(1));
				u.setCategoryID(rs.getInt(2));
				u.setBrandID(rs.getInt(3));
				u.setItemPrice(rs.getDouble(4));
				u.setItemDescription(rs.getString(5));
				u.setItemInstrument(rs.getBoolean(6));
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
			} // End of getBrandByName() method
			
		//Updates a Brands object from the DB through a given Brands object
		public Boolean updateItem(Items u) throws SQLException, ClassNotFoundException, IOException {
			// Declare variables
			Connection conn = null;
			PreparedStatement stmt = null;
			Integer updateResult = null;
			
			// Assign update string to variable
			String updateString = "update items "
					+ "set categoryID = ?, brandID = ?, itemPrice = ?, itemDescription = ?, itemInstrument = ? "
					+ "where itemId = ?";
			
			// Create MySqlConnection class instance
			MariaDBConnection mysql = new MariaDBConnection();
			// Begin try/catch block to query the database
			try
			{
				// Connect to database and assign query string to PreparedStatement object
				conn = mysql.getConnection();
				stmt = conn.prepareStatement(updateString);
				
				// Set query parameters (?)
				stmt.setInt(1, u.getCategoryID());
				stmt.setInt(2, u.getBrandID());
				stmt.setDouble(3, u.getItemPrice());
				stmt.setString(4, u.getItemDescription());
				stmt.setBoolean(5, u.isItemInstrument());
				stmt.setInt(6, u.getItemID());
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
		} // End of updateBrand() method
		
		//Deletes a Brands object from the DB through an ID provided
		public Boolean removeItemById(int itemId) throws IOException, SQLException {
			// Declare variables
			Connection conn = null;
			PreparedStatement stmt = null;
			Integer updateResult = null;
			
			// Assign delete string to variable
			String deleteString = "delete from items where itemId = ?";
			
			// Create MySqlConnection class instance
			MariaDBConnection mysql = new MariaDBConnection();
			// Begin try/catch block to query the database
			try
			{
				// Connect to database and assign query string to PreparedStatement object
				conn = mysql.getConnection();
				stmt = conn.prepareStatement(deleteString);
				
				// Set query parameters (?)
				stmt.setInt(1, itemId);
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
		} // End of removeBrand() method

		//Deletes a Brands object from the DB through a name provided
		public Boolean removeItemByName(String itemName) {
			
			
			try {
				//Uses existing method to get Brand to be deleted
				Items deletedItem = getItemByName(itemName);
				
				//Uses existing method to delete brand and returns outcome.
				return removeItemById(deletedItem.getItemID());
				
			}
			catch(Exception e) {
				
				System.out.println("Unable to delete Item");
				return false;
				
			}
			
		}
		
}
