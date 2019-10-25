package javaDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javaDatabase.MariaDBConnection;
import javaModels.Inventory;


public class InventoryDAO {
	
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
			
			//Returns an ArrayList filled with Inventory objects from database
			public ArrayList<Inventory> getAllInventory() throws SQLException {
				// Declare variables
				Connection conn = null;
				Statement stmt = null;
				ResultSet rs = null;
				Inventory u = null;
				ArrayList<Inventory> inventoryList = null;

				// Assign query string to a variable
				String qString = "select * from inventory";

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
					//Create list to hold Inventory objects
					inventoryList = new ArrayList<Inventory>();
					// Read the ResultSet instance
					while (rs.next()) {
						// Each iteration creates a new Inventory
						u = new Inventory();
						// Assign columns/fields to related fields in the Inventory object
						// 1,2 and 3 represent column numbers/positions
						u.setItemID(rs.getInt(1));
						u.setItemQuantity(rs.getInt(2));
						// Add the Inventory to the list
						inventoryList.add(u);
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
				return inventoryList;
			} // End of getAllInventory method	

			//Creates a Inventory object into the database
			public Integer registerInventoryIncludeId(Inventory inputInventory) throws SQLException, ClassNotFoundException, IOException {
				// Declare variables
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
				
				// Assign insert statement string to variable
				String insertString = "insert into inventory (itemID, itemQuantity) values (?,?)";
				
			    int ID = inputInventory.getItemID();
			    String[] COL = {"itemID"};
			    
			    MariaDBConnection mysql = new MariaDBConnection();
			    
			    try
			    {
			        conn = mysql.getConnection();
			        stmt = conn.prepareStatement(insertString, COL);
			        
			        stmt.setInt(1, inputInventory.getItemID());
			        stmt.setInt(2, inputInventory.getItemQuantity());
			        		        
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
			} // End of registerInventory() method
				
			//Retrieves a Inventory object from the DB through an ID provided
			public Inventory getInventoryById(int inventoryId) throws ClassNotFoundException, IOException, SQLException {
				// Declare variables
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
				Inventory u = null;
				
				// Assign query string to variable
				String qString = "select * from inventory where itemId = ?";
				
				// Create MySqlConnection class instance
				MariaDBConnection mysql = new MariaDBConnection();
				
				// Begin try/catch block to query the database
				try
				{
					// Connect to database and assign query string to PreparedStatement object
					conn = mysql.getConnection();
					stmt = conn.prepareStatement(qString);
					
					// Set query parameters (?)
					stmt.setInt(1, inventoryId); // InventoryId if from String parameter passed to method
					
					// Run query and assign to ResultSet
					rs = stmt.executeQuery();
					
					// Retrieve ResultSet and assign to new Inventory
					if (rs.next()) {
						u = new Inventory();
						u.setItemID(rs.getInt(1));
						u.setItemQuantity(rs.getInt(2));
						
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
			} // End of getInventoryById() method
					
			//Updates a Inventory object from the DB through a given Inventory object
			public Boolean updateInventory(Inventory u) throws SQLException, ClassNotFoundException, IOException {
				// Declare variables
				Connection conn = null;
				PreparedStatement stmt = null;
				Integer updateResult = null;
				
				// Assign update string to variable
				String updateString = "update inventory "
						+ "set itemQuantity = ? "
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
					stmt.setInt(1, u.getItemQuantity());
					stmt.setInt(2, u.getItemID());
					
					
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
			} // End of updateInventory() method
			
			//Deletes a Inventorys object from the DB through an ID provided
			public Boolean removeInventoryById(int inventoryId) throws IOException, SQLException {
				// Declare variables
				Connection conn = null;
				PreparedStatement stmt = null;
				Integer updateResult = null;
				
				// Assign delete string to variable
				String deleteString = "delete from inventory where itemId = ?";
				
				// Create MySqlConnection class instance
				MariaDBConnection mysql = new MariaDBConnection();
				// Begin try/catch block to query the database
				try
				{
					// Connect to database and assign query string to PreparedStatement object
					conn = mysql.getConnection();
					stmt = conn.prepareStatement(deleteString);
					
					// Set query parameters (?)
					stmt.setInt(1, inventoryId);
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
			} // End of removeInventory() method

			public static void main(String[] args) {
				
				InventoryDAO b = new InventoryDAO();
				
				try {
					b.removeInventoryById(0);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
	

}
